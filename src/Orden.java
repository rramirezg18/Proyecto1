/**Proyecto 1 - Gestion De Fabrica De Sillas
 *
 *Clase orden
 *
 * @author Roberto Ramírez 7690-22-12700
 */


import java.util.*;
public class Orden {
    private String numeroOrden;
    private String fecha;
    public Cliente cliente = new Cliente();
    private String[] tiposSillas = new String[3];
    private int[] cantidades = new int[3];

    public Orden(String numeroOrden, String fecha) {
        this.numeroOrden = numeroOrden;
        this.fecha = fecha;
    }
    public void agregarTipoSilla(String tipoSilla, int cantidad) {
        for (int i = 0; i < tiposSillas.length; i++) {
            if (tiposSillas[i] == null) {
                tiposSillas[i] = tipoSilla;
                cantidades[i] = cantidad;
                break;
            }
        }
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTiposSilla(String[] tiposSilla) {
        this.tiposSillas = tiposSilla;
    }

    public void setCantidad(int[] cantidades) {
        this.cantidades = cantidades;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public String getFecha() {
        return fecha;
    }

    public String[] getTiposSillas() {
        return tiposSillas;
    }
        public String getNitCliente() {
        return cliente.nit;
    }

    public String getNombreCliente() {
        return cliente.nombre;
    }


    public int[] getCantidades() {
        return cantidades;
    }




    @Override
    public String toString() {
        String result = "Numero de orden: " + numeroOrden + "\n" +
                        "Fecha de registro: " + fecha + "\n";

        for (int i = 0; i < tiposSillas.length; i++) {
            if (tiposSillas[i] != null && cantidades[i] > 0) {
                String tipoSilla = tiposSillas[i];
                int cantidad = cantidades[i];
                result += "Sillas de " + tipoSilla.toLowerCase() + ": " + cantidad + "\n";
            }
        }

        result += "-------------------------------------";

        return result;
    }
	
}
