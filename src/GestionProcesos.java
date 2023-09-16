/**Proyecto 1 - Gestion De Fabrica De Sillas
 *
 *Clase Interface GestionProcesos
 *
 * @author Roberto Ramírez 7690-22-12700
 */
public interface GestionProcesos {
    void registrarOrden(Orden orden);
    void verOrdenes();
    void iniciarProceso(Orden orden, Inventario inventario);
    void verOrdenesEnProceso();
    void terminarProceso();
    void verOrdenesTerminadas();
    void entregar();

}
