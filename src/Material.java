
/**Proyecto 1 - Gestion De Fabrica De Sillas
 *
 *Clase Material
 *
 * @author Roberto Ramírez 7690-22-12700
 */

public class Material {
    private String codigo;
    private int cantidad;

    public Material(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }



    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Material{" + "codigo=" + codigo + ", cantidad=" + cantidad + '}';
    }
    public void restarCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }

}
