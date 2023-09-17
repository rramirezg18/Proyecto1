
/**Proyecto 1 - Gestion De Fabrica De Sillas
 *
 *Clase proceso
 *
 * @author Roberto Ramírez 7690-22-12700
 */

import java.util.*;
public class Proceso implements GestionProcesos {
    private List<Orden> ordenesEnProceso = new ArrayList<>();
    private List<Orden> ordenesTerminadas = new ArrayList<>(); // Lista de órdenes terminadas
    float precioTotal = 0.0f;
    
    private List<Orden> ordenes = new ArrayList<>();
    public void registrarOrden(Orden orden) {
        boolean ordenExistente = false;

        for (Orden existingOrden : ordenes) {
            if (existingOrden.getNumeroOrden().equals(orden.getNumeroOrden())) {
                ordenExistente = true;
                break;
            }
        }

        if (!ordenExistente) {
            ordenes.add(orden);
            System.out.println("......");
            System.out.println("\tOrden registrada exitosamente.");
        } else {
        	System.out.println("......");
            System.out.println("\tEl número de orden ya existe. Ingrese uno nuevo.");
        }
    }
    public void verOrdenes() {
    float precioPino = 30.00f;
    float precioCedro = 40.00f;
    float precioCaoba = 50.00f;
    
    //System.out.println("Órdenes almacenadas:");
    
    for (Orden ord : ordenes) {
        System.out.println("Número de orden: " + ord.getNumeroOrden());
        System.out.println("Fecha de registro: " + ord.getFecha());
        System.out.println("NIT del cliente: " + ord.getNitCliente());
        System.out.println("Nombre del cliente: " + ord.getNombreCliente());
        
        //float precioTotal = 0.0f; // Inicializar el precio total de la orden
        
        for (int i = 0; i < ord.getTiposSillas().length; i++) {
            if (ord.getTiposSillas()[i] != null && ord.getCantidades()[i] > 0) {
                String tipoSilla = ord.getTiposSillas()[i];
                int cantidad = ord.getCantidades()[i];

                // Calcular el precio total para cada tipo de silla
                if (tipoSilla.equalsIgnoreCase("Pino")) {
                    precioTotal += precioPino * cantidad;
                } else if (tipoSilla.equalsIgnoreCase("Cedro")) {
                    precioTotal += precioCedro * cantidad;
                } else if (tipoSilla.equalsIgnoreCase("Caoba")) {
                    precioTotal += precioCaoba * cantidad;
                }

                // Mostrar la cantidad y el precio total en la misma línea separados por guiones
                System.out.println("Sillas de " + tipoSilla.toLowerCase() + ": " + cantidad + " - Precio: $" + (cantidad * getPrecioSilla(tipoSilla)));
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("El precio total de la orden es: $" + precioTotal);
        System.out.println("-------------------------------------");
    }
}

// Función para obtener el precio de una silla dado su tipo
private float getPrecioSilla(String tipoSilla) {
    if (tipoSilla.equalsIgnoreCase("Pino")) {
        return 30.00f;
    } else if (tipoSilla.equalsIgnoreCase("Cedro")) {
        return 40.00f;
    } else if (tipoSilla.equalsIgnoreCase("Caoba")) {
        return 50.00f;
    } else {
        return 0.00f; // Si el tipo de silla no se reconoce, retorna 0
    }
}



    
    
    
    
    
    

    public List<Orden> getOrdenes() {
        return ordenes;
    }
    
    
    
    
    
    
    
    
    public void iniciarProceso(Orden orden, Inventario inventario) {
        boolean materialesDisponibles = inventario.verificarDisponibilidadMateriales(orden);
        if (materialesDisponibles) {
            // Crear un mapa para realizar un seguimiento de la cantidad necesaria de cada material
            Map<String, Integer> cantidadNecesariaMateriales = new HashMap<>();

            // Inicializar el mapa con valores iniciales de 0
            cantidadNecesariaMateriales.put("Pino", 0);
            cantidadNecesariaMateriales.put("Cedro", 0);
            cantidadNecesariaMateriales.put("Caoba", 0);
            cantidadNecesariaMateriales.put("Clavos", 0);
            cantidadNecesariaMateriales.put("Barniz", 0);

            // Calcular la cantidad necesaria de cada material
            for (int i = 0; i < orden.getTiposSillas().length; i++) {
                String tipoSilla = orden.getTiposSillas()[i];
                int cantidadSillas = orden.getCantidades()[i];

                if (tipoSilla.equalsIgnoreCase("Pino")) {
                    cantidadNecesariaMateriales.put("Pino", cantidadNecesariaMateriales.get("Pino") + 2 * cantidadSillas);
                    cantidadNecesariaMateriales.put("Clavos", cantidadNecesariaMateriales.get("Clavos") + 20 * cantidadSillas);
                    cantidadNecesariaMateriales.put("Barniz", cantidadNecesariaMateriales.get("Barniz") + cantidadSillas);
                } else if (tipoSilla.equalsIgnoreCase("Cedro")) {
                    cantidadNecesariaMateriales.put("Cedro", cantidadNecesariaMateriales.get("Cedro") + 2 * cantidadSillas);
                    cantidadNecesariaMateriales.put("Clavos", cantidadNecesariaMateriales.get("Clavos") + 20 * cantidadSillas);
                    cantidadNecesariaMateriales.put("Barniz", cantidadNecesariaMateriales.get("Barniz") + cantidadSillas);
                } else if (tipoSilla.equalsIgnoreCase("Caoba")) {
                    cantidadNecesariaMateriales.put("Caoba", cantidadNecesariaMateriales.get("Caoba") + 2 * cantidadSillas);
                    cantidadNecesariaMateriales.put("Clavos", cantidadNecesariaMateriales.get("Clavos") + 20 * cantidadSillas);
                    cantidadNecesariaMateriales.put("Barniz", cantidadNecesariaMateriales.get("Barniz") + cantidadSillas);
                }
            }
            ordenesEnProceso.add(orden);

            // Restar la cantidad necesaria de cada material del inventario
            for (Material material : inventario.getMateriales()) {
                String codigo = material.getCodigo();
                int cantidadNecesaria = cantidadNecesariaMateriales.getOrDefault(codigo, 0);
                material.restarCantidad(cantidadNecesaria);
            }
            System.out.println(".....\n");
            System.out.println("La orden " + orden.getNumeroOrden() + " se encuentra en proceso de producción");
        } else {
            System.out.println("No cuentas con los materiales necesarios para iniciar el proceso");
        }
    }
    public void verOrdenesEnProceso(){
        if (ordenesEnProceso.isEmpty()) {
            System.out.println("No hay órdenes en proceso.");
        } else {
            //System.out.println("Órdenes en proceso:");
            for (Orden orden : ordenesEnProceso) {
                System.out.println(orden.toString());
            }
        }
    }
    public void terminarProceso(){
        Scanner entrada = new Scanner(System.in);
        
        // Mostrar las órdenes en proceso
        //verOrdenesEnProceso();
        
        // Solicitar al usuario que ingrese el número de orden que desea terminar
        System.out.println("Ingrese el número de orden que desea terminar:");
        String numeroOrden = entrada.nextLine();
        
        // Buscar la orden en proceso correspondiente al número de orden ingresado
        Orden ordenTerminada = null;
        for (Orden orden : ordenesEnProceso) {
            if (orden.getNumeroOrden().equals(numeroOrden)) {
                ordenTerminada = orden;
                break;
            }
        }
        
        if (ordenTerminada != null) {
            // Eliminar la orden de la lista de órdenes en proceso
            ordenesEnProceso.remove(ordenTerminada);
            
            // Agregar la orden a la lista de órdenes terminadas
            ordenesTerminadas.add(ordenTerminada);
            System.out.println(".....\n");
            System.out.println("!La orden " + numeroOrden + " ha sido fabricada correctamente!");
        } else {
            System.out.println("No se encontró una orden en proceso con el   número de orden ingresado.");
        }
    }
    
    public void verOrdenesTerminadas(){
        if (ordenesTerminadas.isEmpty()) {
            System.out.println("No hay órdenes terminadas.");
        } else {
            //System.out.println("Órdenes Terminadas:");
            for (Orden orden : ordenesTerminadas) {
                System.out.println(orden.toString());
            }
        }
    }
    public void entregar() {
        Scanner entrada = new Scanner(System.in);

        // Mostrar las órdenes terminadas
        //verOrdenesTerminadas();

        // Solicitar al usuario que ingrese el número de   a entregar
        System.out.println("Ingrese el número de orden que desea FACTURAR Y ENTREGAR:");
        String numeroOrden = entrada.nextLine();

        // Buscar la orden en productos terminados correspondiente al número de orden ingresado
        Orden ordenEntregada = null;
        for (Orden orden : ordenesTerminadas) {
            if (orden.getNumeroOrden().equals(numeroOrden)) {
                ordenEntregada = orden;
                break;
            }
        }

        if (ordenEntregada != null) {
            // Eliminar la orden de la lista de órdenes terminadas
            ordenesTerminadas.remove(ordenEntregada);

            // Obtener el NIT y el nombre del cliente
            String nitCliente = ordenEntregada.getNitCliente();
            String nombreCliente = ordenEntregada.getNombreCliente();

            System.out.println("La orden de compra " + numeroOrden + " fue ENTREGADA y FACTURADA al Cliente " + nombreCliente + " " + nitCliente);
            System.out.println("El cliente pago un total de: $" + precioTotal);
        } else {
            System.out.println("No se encontró una orden de compra en productos terminados con el número de orden ingresado.");
        }
    }

}
