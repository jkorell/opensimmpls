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
package simMPLS.utils;

import java.util.*;

/** Esta clase es la superclase de la que partiran todos los eventos del simulador
 * openSimMPLS. Est� creada para permitir el polimorfismo.
 * @version 1.0
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 */
public abstract class TOpenSimMPLSEvent extends EventObject implements Comparable {
    
    /**
     * Este el el constructor de la clase, que permitir� crear instancias de tipo
     * TEventoSimMPLS. En realidad no se puede porque es una clase abstracta, pero debe
     * ser inicializada por las superclases y por ello se proporciona este constructor.
     * @since 1.0
     * @param inst Instante de tiempo. Todos los eventos de Open SimMPLS est�n marcados con el
     * instante en que se han generado.
     * @param emisor El objeto que genera el evento.
     * @param id El identificador �nico del evento, para que el recolector de eventos pueda
     * ordenarlo.
     */
    public TOpenSimMPLSEvent(Object emisor, long id, long inst) {
        super(emisor);
        identificador = id;
        instante = inst;
    }
    
    /**
     * Este m�todo permite obtener el instante de tiempo en que se gener� el evento.
     * @return El instante de tiempo en que se gener� el evento.
     * @since 1.0
     */
    public long obtenerInstante() {
        return this.instante;
    }
    
    /** Este m�todo obtiene el identificador del evento.
     * @return El identificador del evento.
     * @since 1.0
     */
    public long obtenerIdentificador() {
        return identificador;
    }
    
    /** Este m�todo permite cambiar el identificador del objeto colocado en el momento
     * de su instanciaci�n.
     * @since 1.0
     * @param id El nuevo identificador del objeto.
     */
    public void ponerIdentificador(long id) {
        identificador = id;
    }
    
    /** Este m�todo compara este evento con otro de la misma clase para determinar el
     * orden de cada uno y poder insertarlo de forma correcta en un arbol binario
     * ordenado.
     * @param o El otro evento con el que se compara.
     * @return -1, 0 � 1, dependiendo de si los eventos son distintos, mayor o menor en cuanto
     * a orden.
     * @since 1.0
     */
    public int compareTo(Object o) {
        TOpenSimMPLSEvent e = (TOpenSimMPLSEvent) o;
        if (this.instante < e.obtenerInstante())
            return -1;
        else if (this.instante > e.obtenerInstante())
            return 1;
        else {
            if (obtenerIdentificador() < e.obtenerIdentificador())
                return -1;
            else if (obtenerIdentificador() == e.obtenerIdentificador()) {
                return 0;
            }
            return 1;
        }
    }
    
    /** Este m�todo obtiene el tipo del evento, que ser� una de las constantes definidas
     * en esta clase.
     * @return La constante que indica de qu� tipo es el evento.
     * @since 1.0
     */
    public abstract int getType();
    
    /** Constante que indica que el evento es de tipo ESTADISTICA.
     * @since 1.0
     */
    public static final int ESTADISTICA = 0;
    /** Constante que indica que el evento es de tipo SIMULACION.
     * @since 1.0
     */
    public static final int SIMULACION = 1;
    /** Constante que indica que el evento es de tipo TIMER.
     * @since 1.0
     */
    public static final int TIMER = 2;
    /** Constante que indica que el evento es de tipo PROGRESS.
     * @since 1.0
     */
    public static final int PROGRESS = 3;
    
    /** Atributo que contiene el identificador del evento.
     * @since 1.0
     */
    private long identificador;
    private long instante;
}
