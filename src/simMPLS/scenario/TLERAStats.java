/* 
 * Copyright (C) 2014 Manuel Domínguez-Dorado <ingeniero@manolodominguez.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package simMPLS.scenario;

import simMPLS.protocols.TGPSRPPDU;
import simMPLS.protocols.TGPSRPPayload;
import simMPLS.protocols.TAbstractPDU;
import org.jfree.chart.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.data.*;

/**
 * Esta clase implementa las estad�sticas para un nodo LERA.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TLERAStats extends TStats {
    
    /**
     * Crea una nueva instancia de TEstadisticasLERA
     * @since 1.0
     */
    public TLERAStats() {
    	paquetesEntrantes = new XYSeriesCollection();
    	paquetesSalientes = new XYSeriesCollection();
    	paquetesDescartados = new XYSeriesCollection();
    	salientesIPv4 = new XYSeries(TStats.IPV4);
    	salientesIPv4_GOS1 = new XYSeries(TStats.IPV4_GOS1);
    	salientesIPv4_GOS2 = new XYSeries(TStats.IPV4_GOS2);
    	salientesIPv4_GOS3 = new XYSeries(TStats.IPV4_GOS3);
    	salientesMPLS = new XYSeries(TStats.MPLS);
    	salientesMPLS_GOS1 = new XYSeries(TStats.MPLS_GOS1);
    	salientesMPLS_GOS2 = new XYSeries(TStats.MPLS_GOS2);
    	salientesMPLS_GOS3 = new XYSeries(TStats.MPLS_GOS3);
    	salientesTLDP = new XYSeries(TStats.TLDP);
    	salientesGPSRP = new XYSeries(TStats.GPSRP);
    	entrantesIPv4 = new XYSeries(TStats.IPV4);
    	entrantesIPv4_GOS1 = new XYSeries(TStats.IPV4_GOS1);
    	entrantesIPv4_GOS2 = new XYSeries(TStats.IPV4_GOS2);
    	entrantesIPv4_GOS3 = new XYSeries(TStats.IPV4_GOS3);
    	entrantesMPLS = new XYSeries(TStats.MPLS);
    	entrantesMPLS_GOS1 = new XYSeries(TStats.MPLS_GOS1);
    	entrantesMPLS_GOS2 = new XYSeries(TStats.MPLS_GOS2);
    	entrantesMPLS_GOS3 = new XYSeries(TStats.MPLS_GOS3);
    	entrantesTLDP = new XYSeries(TStats.TLDP);
    	entrantesGPSRP = new XYSeries(TStats.GPSRP);
        descartadosIPv4 = new XYSeries(TStats.IPV4);
    	descartadosIPv4_GOS1 = new XYSeries(TStats.IPV4_GOS1);
    	descartadosIPv4_GOS2 = new XYSeries(TStats.IPV4_GOS2);
    	descartadosIPv4_GOS3 = new XYSeries(TStats.IPV4_GOS3);
	descartadosMPLS = new XYSeries(TStats.MPLS);
	descartadosMPLS_GOS1 = new XYSeries(TStats.MPLS_GOS1);
	descartadosMPLS_GOS2 = new XYSeries(TStats.MPLS_GOS2);
	descartadosMPLS_GOS3 = new XYSeries(TStats.MPLS_GOS3);
    	descartadosTLDP = new XYSeries(TStats.TLDP);
    	descartadosGPSRP = new XYSeries(TStats.GPSRP);
        tEIPV4 = 0;
        tEIPV4_GOS1 = 0;
        tEIPV4_GOS2 = 0;
        tEIPV4_GOS3 = 0;
        tEMPLS = 0;
        tEMPLS_GOS1 = 0;
        tEMPLS_GOS2 = 0;
        tEMPLS_GOS3 = 0;
        tETLDP = 0;
        tEGPSRP = 0;
        tSIPV4 = 0;
        tSIPV4_GOS1 = 0;
        tSIPV4_GOS2 = 0;
        tSIPV4_GOS3 = 0;
        tSMPLS = 0;
        tSMPLS_GOS1 = 0;
        tSMPLS_GOS2 = 0;
        tSMPLS_GOS3 = 0;
        tSTLDP = 0;
        tSGPSRP = 0;
        tDIPV4 = 0;
        tDIPV4_GOS1 = 0;
        tDIPV4_GOS2 = 0;
        tDIPV4_GOS3 = 0;
        tDMPLS = 0;
        tDMPLS_GOS1 = 0;
        tDMPLS_GOS2 = 0;
        tDMPLS_GOS3 = 0;
        tDTLDP = 0;
        tDGPSRP = 0;
        retransmisionesAtendidas = new DefaultCategoryDataset();
        solicitudesRecibidas = 0;
        retransmisionesRealizadas = 0;
        retransmisionesNoRealizadas = 0;
        recuperacionesLocales = new DefaultCategoryDataset();
        paquetesGoSPerdido = 0;
        solicitudesEmitidas = 0;
        paquetesGoSRecuperados = 0;
        paquetesGoSNoRecuperados = 0;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 1.
     * @return Datos para la gr�fica 1.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica1() {
        return this.paquetesEntrantes;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 2.
     * @return Datos para la gr�fica 2.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica2() {
        return this.paquetesSalientes;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 3.
     * @return Datos para la gr�fica 3.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica3() {
        return this.paquetesDescartados;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 4.
     * @return Datos para la gr�fica 4.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica4() {
        return this.retransmisionesAtendidas;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 5.
     * @return Datos para la gr�fica 5.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica5() {
        return this.recuperacionesLocales;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 6.
     * @return Datos para la gr�fica 6.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica6() {
        return null;
    }

    /**
     * Este metodo permite aumentar las estad�sticas a�adiendo para ello las del
     * paquete especificado.
     * @param paquete Paquete a contabilizar.
     * @param entrada ENTRADA, SALIDA o DESCARTE, dependiendo de si el paquete entra en el nodod, sale
     * de el o es descartado.
     * @since 1.0
     */    
    public void addStatsEntry(TAbstractPDU paquete, int entrada) {
        if (this.estadisticasActivas) {
            int tipoPaquete = paquete.getSubtype();
            int GoS = 0;
            if (tipoPaquete == TAbstractPDU.TLDP) {
                if (entrada == TStats.SALIDA) {
                    this.tSTLDP++;
                } else if (entrada == TStats.DESCARTE) {
                    this.tDTLDP++;
                } else if (entrada == TStats.ENTRADA) {
                    this.tETLDP++;
                }
            } else if (tipoPaquete == TAbstractPDU.GPSRP) {
                TGPSRPPDU pGPSRP = (TGPSRPPDU) paquete;
                int mensaje = pGPSRP.getGPSRPPayload().getGPSRPMessageType();
                if (mensaje == TGPSRPPayload.RETRANSMISSION_REQUEST) {
                    if (entrada == TStats.SALIDA) {
                        this.tSGPSRP++;
                        solicitudesEmitidas++;
                    } else if (entrada == TStats.DESCARTE) {
                        this.tDGPSRP++;
                    } else if (entrada == TStats.ENTRADA) {
                        this.tEGPSRP++;
                        solicitudesRecibidas++;
                    }
                } else if (mensaje == TGPSRPPayload.RETRANSMISION_NOT_POSSIBLE) {
                    if (entrada == TStats.SALIDA) {
                        this.tSGPSRP++;
                        retransmisionesNoRealizadas++;
                    } else if (entrada == TStats.DESCARTE) {
                        this.tDGPSRP++;
                    } else if (entrada == TStats.ENTRADA) {
                        this.tEGPSRP++;
                        paquetesGoSNoRecuperados++;
                    }
                } else if (mensaje == TGPSRPPayload.RETRANSMISION_OK) {
                    if (entrada == TStats.SALIDA) {
                        this.tSGPSRP++;
                        retransmisionesRealizadas++;
                    } else if (entrada == TStats.DESCARTE) {
                        this.tDGPSRP++;
                    } else if (entrada == TStats.ENTRADA) {
                        this.tEGPSRP++;
                        paquetesGoSRecuperados++;
                    }
                }
            } else if (tipoPaquete == TAbstractPDU.MPLS) {
                if (entrada == TStats.SALIDA) {
                    this.tSMPLS++;
                } else if (entrada == TStats.DESCARTE) {
                    this.tDMPLS++;
                } else if (entrada == TStats.ENTRADA) {
                    this.tEMPLS++;
                }
            } else if (tipoPaquete == TAbstractPDU.MPLS_GOS) {
                GoS = paquete.getIPv4Header().getOptionsField().getRequestedGoSLevel();
                if (entrada == TStats.SALIDA) {
                    if ((GoS == TAbstractPDU.EXP_LEVEL0_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL0_WITH_BACKUP_LSP)) {
                        this.tSMPLS++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL1_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL1_WITH_BACKUP_LSP)) {
                        this.tSMPLS_GOS1++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL2_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL2_WITH_BACKUP_LSP)) {
                        this.tSMPLS_GOS2++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL3_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL3_WITH_BACKUP_LSP)) {
                        this.tSMPLS_GOS3++;
                    }
                } else if (entrada == TStats.DESCARTE) {
                    paquetesGoSPerdido++;
                    if ((GoS == TAbstractPDU.EXP_LEVEL0_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL0_WITH_BACKUP_LSP)) {
                        this.tDMPLS++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL1_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL1_WITH_BACKUP_LSP)) {
                        this.tDMPLS_GOS1++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL2_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL2_WITH_BACKUP_LSP)) {
                        this.tDMPLS_GOS2++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL3_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL3_WITH_BACKUP_LSP)) {
                        this.tDMPLS_GOS3++;
                    }
                } else if (entrada == TStats.ENTRADA) {
                    if ((GoS == TAbstractPDU.EXP_LEVEL0_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL0_WITH_BACKUP_LSP)) {
                        this.tEMPLS++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL1_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL1_WITH_BACKUP_LSP)) {
                        this.tEMPLS_GOS1++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL2_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL2_WITH_BACKUP_LSP)) {
                        this.tEMPLS_GOS2++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL3_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL3_WITH_BACKUP_LSP)) {
                        this.tEMPLS_GOS3++;
                    }
                }
            } else if (tipoPaquete == TAbstractPDU.IPV4) {
                if (entrada == TStats.SALIDA) {
                    this.tSIPV4++;
                } else if (entrada == TStats.DESCARTE) {
                    this.tDIPV4++;
                } else if (entrada == TStats.ENTRADA) {
                    this.tEIPV4++;
                }
            } else if (tipoPaquete == TAbstractPDU.IPV4_GOS) {
                GoS = paquete.getIPv4Header().getOptionsField().getRequestedGoSLevel();
                if (entrada == TStats.SALIDA) {
                    if ((GoS == TAbstractPDU.EXP_LEVEL0_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL0_WITH_BACKUP_LSP)) {
                        this.tSIPV4++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL1_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL1_WITH_BACKUP_LSP)) {
                        this.tSIPV4_GOS1++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL2_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL2_WITH_BACKUP_LSP)) {
                        this.tSIPV4_GOS2++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL3_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL3_WITH_BACKUP_LSP)) {
                        this.tSIPV4_GOS3++;
                    }
                } else if (entrada == TStats.DESCARTE) {
                    if ((GoS == TAbstractPDU.EXP_LEVEL0_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL0_WITH_BACKUP_LSP)) {
                        this.tDIPV4++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL1_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL1_WITH_BACKUP_LSP)) {
                        this.tDIPV4_GOS1++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL2_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL2_WITH_BACKUP_LSP)) {
                        this.tDIPV4_GOS2++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL3_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL3_WITH_BACKUP_LSP)) {
                        this.tDIPV4_GOS3++;
                    }
                } else if (entrada == TStats.ENTRADA) {
                    if ((GoS == TAbstractPDU.EXP_LEVEL0_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL0_WITH_BACKUP_LSP)) {
                        this.tEIPV4++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL1_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL1_WITH_BACKUP_LSP)) {
                        this.tEIPV4_GOS1++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL2_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL2_WITH_BACKUP_LSP)) {
                        this.tEIPV4_GOS2++;
                    } else if ((GoS == TAbstractPDU.EXP_LEVEL3_WITHOUT_BACKUP_LSP) || (GoS == TAbstractPDU.EXP_LEVEL3_WITH_BACKUP_LSP)) {
                        this.tEIPV4_GOS3++;
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo permite obtener el n�mero de gr�ficas que genera el nodo LERA.
     * @return El n�mero de gr�ficas que genera el LERA.
     * @since 1.0
     */    
    public int obtenerNumeroGraficas() {
        return 5;
    }
    
    /**
     * Este m�todo reinicia los valores de los atributos de la clase, dejando la
     * instancia como si acabase de ser creada por el constructor.
     * @since 1.0
     */    
    public void reset() {
    	paquetesEntrantes = new XYSeriesCollection();
    	paquetesSalientes = new XYSeriesCollection();
    	paquetesDescartados = new XYSeriesCollection();
    	salientesIPv4 = new XYSeries(TStats.IPV4);
    	salientesIPv4_GOS1 = new XYSeries(TStats.IPV4_GOS1);
    	salientesIPv4_GOS2 = new XYSeries(TStats.IPV4_GOS2);
    	salientesIPv4_GOS3 = new XYSeries(TStats.IPV4_GOS3);
    	salientesMPLS = new XYSeries(TStats.MPLS);
    	salientesMPLS_GOS1 = new XYSeries(TStats.MPLS_GOS1);
    	salientesMPLS_GOS2 = new XYSeries(TStats.MPLS_GOS2);
    	salientesMPLS_GOS3 = new XYSeries(TStats.MPLS_GOS3);
    	salientesTLDP = new XYSeries(TStats.TLDP);
    	salientesGPSRP = new XYSeries(TStats.GPSRP);
    	entrantesIPv4 = new XYSeries(TStats.IPV4);
    	entrantesIPv4_GOS1 = new XYSeries(TStats.IPV4_GOS1);
    	entrantesIPv4_GOS2 = new XYSeries(TStats.IPV4_GOS2);
    	entrantesIPv4_GOS3 = new XYSeries(TStats.IPV4_GOS3);
    	entrantesMPLS = new XYSeries(TStats.MPLS);
    	entrantesMPLS_GOS1 = new XYSeries(TStats.MPLS_GOS1);
    	entrantesMPLS_GOS2 = new XYSeries(TStats.MPLS_GOS2);
    	entrantesMPLS_GOS3 = new XYSeries(TStats.MPLS_GOS3);
    	entrantesTLDP = new XYSeries(TStats.TLDP);
    	entrantesGPSRP = new XYSeries(TStats.GPSRP);
        descartadosIPv4 = new XYSeries(TStats.IPV4);
    	descartadosIPv4_GOS1 = new XYSeries(TStats.IPV4_GOS1);
    	descartadosIPv4_GOS2 = new XYSeries(TStats.IPV4_GOS2);
    	descartadosIPv4_GOS3 = new XYSeries(TStats.IPV4_GOS3);
	descartadosMPLS = new XYSeries(TStats.MPLS);
	descartadosMPLS_GOS1 = new XYSeries(TStats.MPLS_GOS1);
	descartadosMPLS_GOS2 = new XYSeries(TStats.MPLS_GOS2);
	descartadosMPLS_GOS3 = new XYSeries(TStats.MPLS_GOS3);
    	descartadosTLDP = new XYSeries(TStats.TLDP);
    	descartadosGPSRP = new XYSeries(TStats.GPSRP);
        tEIPV4 = 0;
        tEIPV4_GOS1 = 0;
        tEIPV4_GOS2 = 0;
        tEIPV4_GOS3 = 0;
        tEMPLS = 0;
        tEMPLS_GOS1 = 0;
        tEMPLS_GOS2 = 0;
        tEMPLS_GOS3 = 0;
        tETLDP = 0;
        tEGPSRP = 0;
        tSIPV4 = 0;
        tSIPV4_GOS1 = 0;
        tSIPV4_GOS2 = 0;
        tSIPV4_GOS3 = 0;
        tSMPLS = 0;
        tSMPLS_GOS1 = 0;
        tSMPLS_GOS2 = 0;
        tSMPLS_GOS3 = 0;
        tSTLDP = 0;
        tSGPSRP = 0;
        tDIPV4 = 0;
        tDIPV4_GOS1 = 0;
        tDIPV4_GOS2 = 0;
        tDIPV4_GOS3 = 0;
        tDMPLS = 0;
        tDMPLS_GOS1 = 0;
        tDMPLS_GOS2 = 0;
        tDMPLS_GOS3 = 0;
        tDTLDP = 0;
        tDGPSRP = 0;
        retransmisionesAtendidas = new DefaultCategoryDataset();
        solicitudesRecibidas = 0;
        retransmisionesRealizadas = 0;
        retransmisionesNoRealizadas = 0;
        recuperacionesLocales = new DefaultCategoryDataset();
        paquetesGoSPerdido = 0;
        solicitudesEmitidas = 0;
        paquetesGoSRecuperados = 0;
        paquetesGoSNoRecuperados = 0;
    }
    
    /**
     * Este m�todo actualiza las estad�sticas con los �ltimos datos estad�sticos
     * existentes desde la �ltima vez que se llam� a este m�todo.
     * @param instante Instante de tiempo al que se atribuir�n los ultimos datos existentes.
     * @since 1.0
     */    
    public void asentarDatos(long instante) {
        if (this.estadisticasActivas) {
            if (tEIPV4 > 0) {
                if (entrantesIPv4.getItemCount() == 0) {
                    this.entrantesIPv4.add(instante-1, 0 );
                    this.entrantesIPv4.add(instante, tEIPV4);
                    this.paquetesEntrantes.addSeries(entrantesIPv4);
                } else {
                    this.entrantesIPv4.add(instante, tEIPV4);
                }
            }

            if (tEIPV4_GOS1 > 0) {
                if (entrantesIPv4_GOS1.getItemCount() == 0) {
                    this.entrantesIPv4_GOS1.add(instante-1, 0 );
                    this.entrantesIPv4_GOS1.add(instante, tEIPV4_GOS1);
                    this.paquetesEntrantes.addSeries(entrantesIPv4_GOS1);
                } else {
                    this.entrantesIPv4_GOS1.add(instante, tEIPV4_GOS1);
                }
            }

            if (tEIPV4_GOS2 > 0) {
                if (entrantesIPv4_GOS2.getItemCount() == 0) {
                    this.entrantesIPv4_GOS2.add(instante-1, 0 );
                    this.entrantesIPv4_GOS2.add(instante, tEIPV4_GOS2);
                    this.paquetesEntrantes.addSeries(entrantesIPv4_GOS2);
                } else {
                    this.entrantesIPv4_GOS2.add(instante, tEIPV4_GOS2);
                }
            }

            if (tEIPV4_GOS3 > 0) {
                if (entrantesIPv4_GOS3.getItemCount() == 0) {
                    this.entrantesIPv4_GOS3.add(instante-1, 0 );
                    this.entrantesIPv4_GOS3.add(instante, tEIPV4_GOS3);
                    this.paquetesEntrantes.addSeries(entrantesIPv4_GOS3);
                } else {
                    this.entrantesIPv4_GOS3.add(instante, tEIPV4_GOS3);
                }
            }

            if (tEMPLS > 0) {
                if (entrantesMPLS.getItemCount() == 0) {
                    this.entrantesMPLS.add(instante-1, 0 );
                    this.entrantesMPLS.add(instante, tEMPLS);
                    this.paquetesEntrantes.addSeries(entrantesMPLS);
                } else {
                    this.entrantesMPLS.add(instante, tEMPLS);
                }
            }

            if (tEMPLS_GOS1 > 0) {
                if (entrantesMPLS_GOS1.getItemCount() == 0) {
                    this.entrantesMPLS_GOS1.add(instante-1, 0 );
                    this.entrantesMPLS_GOS1.add(instante, tEMPLS_GOS1);
                    this.paquetesEntrantes.addSeries(entrantesMPLS_GOS1);
                } else {
                    this.entrantesMPLS_GOS1.add(instante, tEMPLS_GOS1);
                }
            }

            if (tEMPLS_GOS2 > 0) {
                if (entrantesMPLS_GOS2.getItemCount() == 0) {
                    this.entrantesMPLS_GOS2.add(instante-1, 0 );
                    this.entrantesMPLS_GOS2.add(instante, tEMPLS_GOS2);
                    this.paquetesEntrantes.addSeries(entrantesMPLS_GOS2);
                } else {
                    this.entrantesMPLS_GOS2.add(instante, tEMPLS_GOS2);
                }
            }

            if (tEMPLS_GOS3 > 0) {
                if (entrantesMPLS_GOS3.getItemCount() == 0) {
                    this.entrantesMPLS_GOS3.add(instante-1, 0 );
                    this.entrantesMPLS_GOS3.add(instante, tEMPLS_GOS3);
                    this.paquetesEntrantes.addSeries(entrantesMPLS_GOS3);
                } else {
                    this.entrantesMPLS_GOS3.add(instante, tEMPLS_GOS3);
                }
            }

            if (tETLDP > 0) {
                if (entrantesTLDP.getItemCount() == 0) {
                    this.entrantesTLDP.add(instante-1, 0 );
                    this.entrantesTLDP.add(instante, tETLDP);
                    this.paquetesEntrantes.addSeries(entrantesTLDP);
                } else {
                    this.entrantesTLDP.add(instante, tETLDP);
                }
            }

            if (tEGPSRP > 0) {
                if (entrantesGPSRP.getItemCount() == 0) {
                    this.entrantesGPSRP.add(instante-1, 0 );
                    this.entrantesGPSRP.add(instante, tEGPSRP);
                    this.paquetesEntrantes.addSeries(entrantesGPSRP);
                } else {
                    this.entrantesGPSRP.add(instante, tEGPSRP);
                }
            }

            if (tSIPV4 > 0) {
                if (salientesIPv4.getItemCount() == 0) {
                    this.salientesIPv4.add(instante-1, 0 );
                    this.salientesIPv4.add(instante, tSIPV4);
                    this.paquetesSalientes.addSeries(salientesIPv4);
                } else {
                    this.salientesIPv4.add(instante, tSIPV4);
                }
            }
            
            if (tSIPV4_GOS1 > 0) {
                if (salientesIPv4_GOS1.getItemCount() == 0) {
                    this.salientesIPv4_GOS1.add(instante-1, 0 );
                    this.salientesIPv4_GOS1.add(instante, tSIPV4_GOS1);
                    this.paquetesSalientes.addSeries(salientesIPv4_GOS1);
                } else {
                    this.salientesIPv4_GOS1.add(instante, tSIPV4_GOS1);
                }
            }
            
            if (tSIPV4_GOS2 > 0) {
                if (salientesIPv4_GOS2.getItemCount() == 0) {
                    this.salientesIPv4_GOS2.add(instante-1, 0 );
                    this.salientesIPv4_GOS2.add(instante, tSIPV4_GOS2);
                    this.paquetesSalientes.addSeries(salientesIPv4_GOS2);
                } else {
                    this.salientesIPv4_GOS2.add(instante, tSIPV4_GOS2);
                }
            }
            
            if (tSIPV4_GOS3 > 0) {
                if (salientesIPv4_GOS3.getItemCount() == 0) {
                    this.salientesIPv4_GOS3.add(instante-1, 0 );
                    this.salientesIPv4_GOS3.add(instante, tSIPV4_GOS3);
                    this.paquetesSalientes.addSeries(salientesIPv4_GOS3);
                } else {
                    this.salientesIPv4_GOS3.add(instante, tSIPV4_GOS3);
                }
            }
            
            if (tSMPLS > 0) {
                if (salientesMPLS.getItemCount() == 0) {
                    this.salientesMPLS.add(instante-1, 0 );
                    this.salientesMPLS.add(instante, tSMPLS);
                    this.paquetesSalientes.addSeries(salientesMPLS);
                } else {
                    this.salientesMPLS.add(instante, tSMPLS);
                }
            }
            
            if (tSMPLS_GOS1 > 0) {
                if (salientesMPLS_GOS1.getItemCount() == 0) {
                    this.salientesMPLS_GOS1.add(instante-1, 0 );
                    this.salientesMPLS_GOS1.add(instante, tSMPLS_GOS1);
                    this.paquetesSalientes.addSeries(salientesMPLS_GOS1);
                } else {
                    this.salientesMPLS_GOS1.add(instante, tSMPLS_GOS1);
                }
            }
            
            if (tSMPLS_GOS2 > 0) {
                if (salientesMPLS_GOS2.getItemCount() == 0) {
                    this.salientesMPLS_GOS2.add(instante-1, 0 );
                    this.salientesMPLS_GOS2.add(instante, tSMPLS_GOS2);
                    this.paquetesSalientes.addSeries(salientesMPLS_GOS2);
                } else {
                    this.salientesMPLS_GOS2.add(instante, tSMPLS_GOS2);
                }
            }
            
            if (tSMPLS_GOS3 > 0) {
                if (salientesMPLS_GOS3.getItemCount() == 0) {
                    this.salientesMPLS_GOS3.add(instante-1, 0 );
                    this.salientesMPLS_GOS3.add(instante, tSMPLS_GOS3);
                    this.paquetesSalientes.addSeries(salientesMPLS_GOS3);
                } else {
                    this.salientesMPLS_GOS3.add(instante, tSMPLS_GOS3);
                }
            }
            
            if (tSTLDP > 0) {
                if (salientesTLDP.getItemCount() == 0) {
                    this.salientesTLDP.add(instante-1, 0 );
                    this.salientesTLDP.add(instante, tSTLDP);
                    this.paquetesSalientes.addSeries(salientesTLDP);
                } else {
                    this.salientesTLDP.add(instante, tSTLDP);
                }
            }
            
            if (tSGPSRP > 0) {
                if (salientesGPSRP.getItemCount() == 0) {
                    this.salientesGPSRP.add(instante-1, 0 );
                    this.salientesGPSRP.add(instante, tSGPSRP);
                    this.paquetesSalientes.addSeries(salientesGPSRP);
                } else {
                    this.salientesGPSRP.add(instante, tSGPSRP);
                }
            }
                    
            if (tDIPV4 > 0) {
                if (descartadosIPv4.getItemCount() == 0) {
                    this.descartadosIPv4.add(instante-1, 0 );
                    this.descartadosIPv4.add(instante, tDIPV4);
                    this.paquetesDescartados.addSeries(descartadosIPv4);
                } else {
                    this.descartadosIPv4.add(instante, tDIPV4);
                }
            }
                    
            if (tDIPV4_GOS1 > 0) {
                if (descartadosIPv4_GOS1.getItemCount() == 0) {
                    this.descartadosIPv4_GOS1.add(instante-1, 0 );
                    this.descartadosIPv4_GOS1.add(instante, tDIPV4_GOS1);
                    this.paquetesDescartados.addSeries(descartadosIPv4_GOS1);
                } else {
                    this.descartadosIPv4_GOS1.add(instante, tDIPV4_GOS1);
                }
            }

            if (tDIPV4_GOS2 > 0) {
                if (descartadosIPv4_GOS2.getItemCount() == 0) {
                    this.descartadosIPv4_GOS2.add(instante-1, 0 );
                    this.descartadosIPv4_GOS2.add(instante, tDIPV4_GOS2);
                    this.paquetesDescartados.addSeries(descartadosIPv4_GOS2);
                } else {
                    this.descartadosIPv4_GOS2.add(instante, tDIPV4_GOS2);
                }
            }

            if (tDIPV4_GOS3 > 0) {
                if (descartadosIPv4_GOS3.getItemCount() == 0) {
                    this.descartadosIPv4_GOS3.add(instante-1, 0 );
                    this.descartadosIPv4_GOS3.add(instante, tDIPV4_GOS3);
                    this.paquetesDescartados.addSeries(descartadosIPv4_GOS3);
                } else {
                    this.descartadosIPv4_GOS3.add(instante, tDIPV4_GOS3);
                }
            }

            if (tDMPLS > 0) {
                if (descartadosMPLS.getItemCount() == 0) {
                    this.descartadosMPLS.add(instante-1, 0 );
                    this.descartadosMPLS.add(instante, tDMPLS);
                    this.paquetesDescartados.addSeries(descartadosMPLS);
                } else {
                    this.descartadosMPLS.add(instante, tDMPLS);
                }
            }

            if (tDMPLS_GOS1 > 0) {
                if (descartadosMPLS_GOS1.getItemCount() == 0) {
                    this.descartadosMPLS_GOS1.add(instante-1, 0 );
                    this.descartadosMPLS_GOS1.add(instante, tDMPLS_GOS1);
                    this.paquetesDescartados.addSeries(descartadosMPLS_GOS1);
                } else {
                    this.descartadosMPLS_GOS1.add(instante, tDMPLS_GOS1);
                }
            }

            if (tDMPLS_GOS2 > 0) {
                if (descartadosMPLS_GOS2.getItemCount() == 0) {
                    this.descartadosMPLS_GOS2.add(instante-1, 0 );
                    this.descartadosMPLS_GOS2.add(instante, tDMPLS_GOS2);
                    this.paquetesDescartados.addSeries(descartadosMPLS_GOS2);
                } else {
                    this.descartadosMPLS_GOS2.add(instante, tDMPLS_GOS2);
                }
            }

            if (tDMPLS_GOS3 > 0) {
                if (descartadosMPLS_GOS3.getItemCount() == 0) {
                    this.descartadosMPLS_GOS3.add(instante-1, 0 );
                    this.descartadosMPLS_GOS3.add(instante, tDMPLS_GOS3);
                    this.paquetesDescartados.addSeries(descartadosMPLS_GOS3);
                } else {
                    this.descartadosMPLS_GOS3.add(instante, tDMPLS_GOS3);
                }
            }

            if (tDTLDP > 0) {
                if (descartadosTLDP.getItemCount() == 0) {
                    this.descartadosTLDP.add(instante-1, 0 );
                    this.descartadosTLDP.add(instante, tDTLDP);
                    this.paquetesDescartados.addSeries(descartadosTLDP);
                } else {
                    this.descartadosTLDP.add(instante, tDTLDP);
                }
            }

            if (tDGPSRP > 0) {
                if (descartadosGPSRP.getItemCount() == 0) {
                    this.descartadosGPSRP.add(instante-1, 0 );
                    this.descartadosGPSRP.add(instante, tDGPSRP);
                    this.paquetesDescartados.addSeries(descartadosGPSRP);
                } else {
                    this.descartadosGPSRP.add(instante, tDGPSRP);
                }
            }
            
            this.retransmisionesAtendidas.addValue(this.solicitudesRecibidas, TStats.SOLICITUDES_RECIBIDAS, "");
            this.retransmisionesAtendidas.addValue(this.retransmisionesRealizadas, TStats.RETRANSMISIONES_REALIZADAS, "");
            this.retransmisionesAtendidas.addValue(this.retransmisionesNoRealizadas, TStats.RETRANSMISIONES_NO_REALIZADAS, "");
            this.recuperacionesLocales.addValue(this.paquetesGoSPerdido, TStats.PAQUETES_GOS_PERDIDOS, "");
            this.recuperacionesLocales.addValue(this.solicitudesEmitidas, TStats.SOLICITUDES_EMITIDAS, "");
            this.recuperacionesLocales.addValue(this.paquetesGoSRecuperados, TStats.PAQUETES_GOS_RECUPERADOS, "");
            this.recuperacionesLocales.addValue(this.paquetesGoSNoRecuperados, TStats.PAQUETES_GOS_NO_RECUPERADOS, "");
            int sinRespuesta = (solicitudesEmitidas - paquetesGoSRecuperados - paquetesGoSNoRecuperados);
            if (sinRespuesta < 0) {
                sinRespuesta = 0;
            }
            this.recuperacionesLocales.addValue(sinRespuesta, TStats.SOLICITUDES_SIN_RESPUESTA_AUN, "");
        }
    }    
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 1.
     * @return T�tulo de la gr�fica 1.
     * @since 1.0
     */    
    public String obtenerTitulo1() {
        return TStats.PAQUETES_ENTRANTES;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 2.
     * @return T�tulo de la gr�fica 2.
     * @since 1.0
     */    
    public String obtenerTitulo2() {
        return TStats.PAQUETES_SALIENTES;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 3.
     * @return T�tulo de la gr�fica 3.
     * @since 1.0
     */    
    public String obtenerTitulo3() {
        return TStats.PAQUETES_DESCARTADOS;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 4.
     * @return T�tulo de la gr�fica 4.
     * @since 1.0
     */    
    public String obtenerTitulo4() {
        return TStats.RETRANSMISIONES_ATENDIDAS;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 5.
     * @return T�tulo de la gr�fica 5.
     * @since 1.0
     */    
    public String obtenerTitulo5() {
        return TStats.RECUPERACIONES_LOCALES;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 6.
     * @return T�tulo de la gr�fica 6.
     * @since 1.0
     */    
    public String obtenerTitulo6() {
        return null;
    }
    
    private int tEIPV4;
    private int tEIPV4_GOS1;
    private int tEIPV4_GOS2;
    private int tEIPV4_GOS3;
    private int tEMPLS;
    private int tEMPLS_GOS1;
    private int tEMPLS_GOS2;
    private int tEMPLS_GOS3;
    private int tETLDP;
    private int tEGPSRP;
    private int tSIPV4;
    private int tSIPV4_GOS1;
    private int tSIPV4_GOS2;
    private int tSIPV4_GOS3;
    private int tSMPLS;
    private int tSMPLS_GOS1;
    private int tSMPLS_GOS2;
    private int tSMPLS_GOS3;
    private int tSTLDP;
    private int tSGPSRP;
    private int tDIPV4;
    private int tDIPV4_GOS1;
    private int tDIPV4_GOS2;
    private int tDIPV4_GOS3;
    private int tDMPLS;
    private int tDMPLS_GOS1;
    private int tDMPLS_GOS2;
    private int tDMPLS_GOS3;
    private int tDTLDP;
    private int tDGPSRP;
    private XYSeriesCollection paquetesEntrantes;
    private XYSeriesCollection paquetesSalientes;
    private XYSeriesCollection paquetesDescartados;
    private XYSeries entrantesIPv4;
    private XYSeries entrantesIPv4_GOS1;
    private XYSeries entrantesIPv4_GOS2;
    private XYSeries entrantesIPv4_GOS3;
    private XYSeries entrantesMPLS;
    private XYSeries entrantesMPLS_GOS1;
    private XYSeries entrantesMPLS_GOS2;
    private XYSeries entrantesMPLS_GOS3;
    private XYSeries entrantesTLDP;
    private XYSeries entrantesGPSRP;
    private XYSeries salientesIPv4;
    private XYSeries salientesIPv4_GOS1;
    private XYSeries salientesIPv4_GOS2;
    private XYSeries salientesIPv4_GOS3;
    private XYSeries salientesMPLS;
    private XYSeries salientesMPLS_GOS1;
    private XYSeries salientesMPLS_GOS2;
    private XYSeries salientesMPLS_GOS3;
    private XYSeries salientesTLDP;
    private XYSeries salientesGPSRP;
    private XYSeries descartadosIPv4;
    private XYSeries descartadosIPv4_GOS1;
    private XYSeries descartadosIPv4_GOS2;
    private XYSeries descartadosIPv4_GOS3;
    private XYSeries descartadosMPLS;
    private XYSeries descartadosMPLS_GOS1;
    private XYSeries descartadosMPLS_GOS2;
    private XYSeries descartadosMPLS_GOS3;
    private XYSeries descartadosTLDP;
    private XYSeries descartadosGPSRP;
    private DefaultCategoryDataset retransmisionesAtendidas;
    private int solicitudesRecibidas;
    private int retransmisionesRealizadas;
    private int retransmisionesNoRealizadas;
    private DefaultCategoryDataset recuperacionesLocales;
    private int paquetesGoSPerdido;
    private int solicitudesEmitidas;
    private int paquetesGoSRecuperados;
    private int paquetesGoSNoRecuperados;
}
