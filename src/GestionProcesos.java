
public interface GestionProcesos {
    void registrarOrden(Orden orden);
    void verOrdenes();
    void iniciarProceso(Orden orden, Inventario inventario);
    void verOrdenesEnProceso();
    void terminarProceso();
    void verOrdenesTerminadas();
    void entregar();

}
