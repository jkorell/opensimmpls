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
package simMPLS.hardware.ports;

import simMPLS.protocols.TAbstractPDU;

/**
 * This class implement an active port buffer entry. It is needed to prioritize
 * some packets, because of their embedded priority, within the buffer.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 * @version 1.1
 */
public class TActivePortBufferEntry implements Comparable {

    /**
     * This method is the constructor of the class. It creates a new instance of
     * TActivePortBufferEntry.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 1.0
     * @param priority packet priority. Embedded on it as defined by "Guarante
     * of Service (GoS)Support over MPLS using Active Techniques". Read this
     * proposal to know more about GoS priorities.
     * @param incomingOrder The incoming ordet to the buffer. To be used when
     * following a FIFO packet dispatching.
     * @param packet The packet itself.
     */
    public TActivePortBufferEntry(int priority, int incomingOrder, TAbstractPDU packet) {
        this.priority = priority;
        this.incomingOrder = incomingOrder;
        this.packet = packet;
    }

    /**
     * This method compares the current instance with other specified as an
     * argument. It is used to store TActivePortBufferEntry objects in a
     * collection.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param anotherActivePortBufferEntry Otra entrada de un puerto activo con
     * la que se quiere comparar la instancia actual.
     * @return -1, 0 or 1, depending on whether the current instance incoming
     * order is lower, equal or greater than the one specified as an argument.
     * @since 1.0
     */
    @Override
    public int compareTo(Object anotherActivePortBufferEntry) {
        TActivePortBufferEntry activePortBufferEntryAux = (TActivePortBufferEntry) anotherActivePortBufferEntry;
        if (this.incomingOrder < activePortBufferEntryAux.getIncomingOrder()) {
            return TActivePortBufferEntry.THIS_IS_LOWER;
        }
        if (this.incomingOrder > activePortBufferEntryAux.getIncomingOrder()) {
            return TActivePortBufferEntry.THIS_IS_GREATER;
        }
        return TActivePortBufferEntry.BOTH_ARE_EQUAL;
    }

    /**
     * This method returns the priority of the packets stored in this active
     * port buffer entry. This priority has to be interpreted as defined by
     * "Guarante of Service (GoS)Support over MPLS using Active Techniques".
     * Read this proposal to know more about GoS priorities.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 1.0
     * @return Priority of the packet embedded in this active port buffer entry.
     * A number between 0 (no priority) and 10 (maximum priority).
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * This method returns the incoming order of the packet embedded in this
     * active port buffer entry. It is the incoming order of the packet in the
     * parent buffer.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 1.0
     * @return The incoming order of the embedded packet to the active port
     * parent buffer.
     */
    public int getIncomingOrder() {
        return this.incomingOrder;
    }

    /**
     * This method returns the packet embedded in this active port buffer entry.
     *
     * @return The packet itself.
     * @since 1.0
     */
    public TAbstractPDU getPacket() {
        return this.packet;
    }

    private static final int THIS_IS_LOWER = -1;
    private static final int THIS_IS_GREATER = 1;
    private static final int BOTH_ARE_EQUAL = 0;

    private int priority;
    private int incomingOrder;
    private TAbstractPDU packet;
}
