/**Proyecto 1 - Gestion De Fabrica De Sillas
 *
 *Clase invenatario
 *
 * @author Roberto Ramírez 7690-22-12700
 */

import java.util.*;
public class Inventario {
    private List<Material> materiales = new ArrayList<>();
    public void Agregar(Material material){
        boolean materialAgregado = false;
        
        for (Material mat : materiales) {
            if (mat.getCodigo().equals(material.getCodigo())) {
                mat.setCantidad(mat.getCantidad() + material.getCantidad());
                materialAgregado = true;
                break;
            }
        }
        
        if (!materialAgregado) {
            materiales.add(material);
        }
    }
    public void verMateriales(){
        System.out.println("Materiales en el inventario:");
        for (Material mat : materiales) {
            System.out.println(mat.toString());
        }  
    }
    
    public boolean verificarDisponibilidadMateriales(Orden orden) {
        // Variables para llevar un registro de la cantidad necesaria de cada material
        int cantidadNecesariaPino = 0;
        int cantidadNecesariaCedro = 0;
        int cantidadNecesariaCaoba = 0;
        int cantidadNecesariaClavos = 0;
        int cantidadNecesariaBarniz = 0;

        // Calcular la cantidad total necesaria de cada material
        for (int i = 0; i < orden.getTiposSillas().length; i++) {
            String tipoSilla = orden.getTiposSillas()[i];
            int cantidadSillas = orden.getCantidades()[i];

            if (tipoSilla.equalsIgnoreCase("Pino")) {
                cantidadNecesariaPino += 2 * cantidadSillas;
                cantidadNecesariaClavos += 20 * cantidadSillas;
                cantidadNecesariaBarniz += cantidadSillas;
            } else if (tipoSilla.equalsIgnoreCase("Cedro")) {
                cantidadNecesariaCedro += 2 * cantidadSillas;
                cantidadNecesariaClavos += 20 * cantidadSillas;
                cantidadNecesariaBarniz += cantidadSillas;
            } else if (tipoSilla.equalsIgnoreCase("Caoba")) {
                cantidadNecesariaCaoba += 2 * cantidadSillas;
                cantidadNecesariaClavos += 20 * cantidadSillas;
                cantidadNecesariaBarniz += cantidadSillas;
            }
        }

        // Verificar si hay suficiente cantidad de cada material en el inventario
        for (Material mat : materiales) {
            String material = mat.getCodigo();
            int cantidadDisponible = mat.getCantidad();

            if (material.equalsIgnoreCase("Pino") && cantidadDisponible < cantidadNecesariaPino) {
                return false; // Si No hay suficiente madera de pino
            } else if (material.equalsIgnoreCase("Cedro") && cantidadDisponible < cantidadNecesariaCedro) {
                return false; // Si No hay suficiente madera de cedro
            } else if (material.equalsIgnoreCase("Caoba") && cantidadDisponible < cantidadNecesariaCaoba) {
                return false; // Si No hay suficiente madera de caoba
            } else if (material.equalsIgnoreCase("Clavos") && cantidadDisponible < cantidadNecesariaClavos) {
                return false; // Si No hay suficientes clavos
            } else if (material.equalsIgnoreCase("Barniz") && cantidadDisponible < cantidadNecesariaBarniz) {
                return false; // Si No hay suficiente barniz
            }
        }

        return true; // Todos los materiales están disponibles
    }


    public List<Material> getMateriales() {
            return materiales;
    }
	

}
