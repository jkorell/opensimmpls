package simMPLS.hardware.dmgp;

import java.util.Iterator;
import java.util.TreeSet;
import simMPLS.protocols.TPDU;
import simMPLS.protocols.TPDUMPLS;
import simMPLS.utils.TIdentificadorRotativo;
import simMPLS.utils.TLock;

/**
 * This class implements a DMGP memory to save GoS-aware PDUs temporarily.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 * @version 1.1
 */
public class TDMGP {

    /**
     * This method is the class constructor. It creates a new instance of TDMGP
     * and initialize its attributes.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 1.0
     */
    public TDMGP() {
        this.monitor = new TLock();
        this.idGenerator = new TIdentificadorRotativo();
        this.flows = new TreeSet();
        this.totalAvailablePercentage = 100;
        this.totalDMGPSizeInKB = 1;
        this.totalAssignedOctects = 0;
    }

    /**
     * This method establish the global size of DMGP in kilobytes.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 1.0
     * @param size Size in kilobytes.
     */
    public void setDMGPSizeInKB(int size) {
        this.totalDMGPSizeInKB = size;
        this.reset();
    }

    /**
     * This method obtains the globals soze of DMGP in kilobites.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @return Size in kilobites.
     * @since 1.0
     */
    public int getDMGPSizeInKB() {
        return this.totalDMGPSizeInKB;
    }

    /**
     * This method look for a packet tagged as GoS within the DMGP memory.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param flowID Identifier of the flow the packet belongs to.
     * @param packetID Identifier of the packet.
     * @return The packet, if in the DMGP. NULL on the contrary.
     * @since 1.0
     */
    public TPDUMPLS getPacket(int flowID, int packetID) {
        TPDUMPLS wantedPacket = null;
        TDMGPFlowEntry dmgpFlowEntry = this.getFlow(flowID);
        if (dmgpFlowEntry != null) {
            this.monitor.bloquear();
            Iterator it = dmgpFlowEntry.obtenerEntradas().iterator();
            TDMGPEntry dmgpEntry = null;
            while (it.hasNext()) {
                dmgpEntry = (TDMGPEntry) it.next();
                if (dmgpEntry.obtenerIdPaquete() == packetID) {
                    wantedPacket = dmgpEntry.obtenerPaquete();
                    this.monitor.liberar();
                    return wantedPacket;
                }
            }
            this.monitor.liberar();
        }
        return null;
    }

    /**
     * This method insert a new GoS packet into the DMGP memory.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param packet The packet to be inserted into the DMGP memory.
     * @since 1.0
     */
    public void addPacket(TPDUMPLS packet) {
        TDMGPFlowEntry dmgpFlowEntry = this.getFlow(packet);
        if (dmgpFlowEntry == null) {
            dmgpFlowEntry = this.createFlow(packet);
        }
        if (dmgpFlowEntry != null) {
            dmgpFlowEntry.insertarPaquete(packet);
        } else {
            packet = null;
        }
    }

    /**
     * This method restores the value of all attributes as when created by the
     * constructor.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 1.0
     */
    public void reset() {
        this.monitor = null;
        this.idGenerator = null;
        this.flows = null;
        this.monitor = new TLock();
        this.idGenerator = new TIdentificadorRotativo();
        this.flows = new TreeSet();
        this.totalAvailablePercentage = 100;
        this.totalAssignedOctects = 0;
    }

    private int getDMGPSizeInOctects() {
        return (this.totalDMGPSizeInKB * 1024);
    }

    private TDMGPFlowEntry getFlow(TPDU packet) {
        TDMGPFlowEntry dmgpFlowEntry = null;
        int flowID = packet.obtenerCabecera().obtenerIPOrigen().hashCode();
        dmgpFlowEntry = getFlow(flowID);
        return dmgpFlowEntry;
    }

    private TDMGPFlowEntry getFlow(int idf) {
        TDMGPFlowEntry dmgpFlowEntry = null;
        this.monitor.bloquear();
        Iterator ite = flows.iterator();
        while (ite.hasNext()) {
            dmgpFlowEntry = (TDMGPFlowEntry) ite.next();
            if (dmgpFlowEntry.obtenerIdFlujo() == idf) {
                this.monitor.liberar();
                return dmgpFlowEntry;
            }
        }
        this.monitor.liberar();
        return null;
    }

    private TDMGPFlowEntry createFlow(TPDU packet) {
        this.monitor.bloquear();
        TDMGPFlowEntry dmgpFlowEntry = null;
        int flowID = packet.obtenerCabecera().obtenerIPOrigen().hashCode();
        int percentageToBeAssigned = 0;
        int octectsToBeAssigned = 0;
        if (this.totalAssignedOctects < this.getDMGPSizeInOctects()) {
            percentageToBeAssigned = this.getPercentageToBeAssigned(packet);
            octectsToBeAssigned = this.getOctectsToBeAssigned(packet);
            if (octectsToBeAssigned > 0) {
                this.totalAssignedOctects += octectsToBeAssigned;
                this.totalAvailablePercentage -= percentageToBeAssigned;
                dmgpFlowEntry = new TDMGPFlowEntry(this.idGenerator.obtenerNuevo());
                dmgpFlowEntry.ponerIdFlujo(flowID);
                dmgpFlowEntry.ponerPorcentajeAsignado(percentageToBeAssigned);
                dmgpFlowEntry.ponerOctetosAsignados(octectsToBeAssigned);
                flows.add(dmgpFlowEntry);
                this.monitor.liberar();
                return dmgpFlowEntry;
            }
        }
        this.monitor.liberar();
        return null;
    }

    private int getOctectsToBeAssigned(TPDU packet) {
        int reservedPercentage = getRequestedPercentage(packet);
        int reservedOctects = 0;
        if (this.totalAvailablePercentage > 0) {
            if (this.totalAvailablePercentage > reservedPercentage) {
                reservedOctects = ((this.getDMGPSizeInOctects() * reservedPercentage) / 100);
                return reservedOctects;
            } else {
                reservedOctects = this.getDMGPSizeInOctects() - this.totalAssignedOctects;
                return reservedOctects;
            }
        }
        return 0;
    }

    private int getPercentageToBeAssigned(TPDU packet) {
        int reservedPercentage = getRequestedPercentage(packet);
        if (this.totalAvailablePercentage > 0) {
            if (this.totalAvailablePercentage > reservedPercentage) {
                return reservedPercentage;
            } else {
                return this.totalAvailablePercentage;
            }
        }
        return 0;
    }

    private int getRequestedPercentage(TPDU packet) {
        int packetGoSLevel = 0;
        if (packet.obtenerCabecera().obtenerCampoOpciones().estaUsado()) {
            packetGoSLevel = packet.obtenerCabecera().obtenerCampoOpciones().obtenerNivelGoS();
        } else {
            return 0;
        }
        if (packetGoSLevel == TPDU.EXP_NIVEL3_CONLSP) {
            return 12;
        }
        if (packetGoSLevel == TPDU.EXP_NIVEL3_SINLSP) {
            return 12;
        }
        if (packetGoSLevel == TPDU.EXP_NIVEL2_CONLSP) {
            return 8;
        }
        if (packetGoSLevel == TPDU.EXP_NIVEL2_SINLSP) {
            return 8;
        }
        if (packetGoSLevel == TPDU.EXP_NIVEL1_CONLSP) {
            return 4;
        }
        if (packetGoSLevel == TPDU.EXP_NIVEL1_SINLSP) {
            return 4;
        }
        if (packetGoSLevel == TPDU.EXP_NIVEL0_CONLSP) {
            return 0;
        }
        if (packetGoSLevel == TPDU.EXP_NIVEL0_SINLSP) {
            return 0;
        }
        return 1;
    }

    private TLock monitor;
    private TIdentificadorRotativo idGenerator;
    private TreeSet flows;
    private int totalAvailablePercentage;
    private int totalDMGPSizeInKB;
    private int totalAssignedOctects;
}