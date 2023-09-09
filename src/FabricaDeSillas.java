import java.util.*;
public class FabricaDeSillas {

	public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Inventario inventario = new Inventario();
        Proceso proceso = new Proceso();
        int opcion = 0;
        int salir = 0;
        Producto producto1 = new Producto("1", "Silla Pino", 30.00);
        Producto producto2 = new Producto("2", "Silla Cedro", 40.00);
        Producto producto3 = new Producto("3", "Silla Caoba", 40.00);
        System.out.println(producto1.toString());
        
        while (salir != 1){
            Menu();
            //System.out.println("Ingrese una opción");
            try{
                opcion = entrada.nextInt();               
            }catch(Exception e){
                System.out.println("Opcion no valida");
            }//tryCatch
            switch(opcion){
            	case 1:
                    System.out.println("\tRegistrar Compras\n");
                    entrada.nextLine();
                    solicitarCantidad(entrada, inventario, "Pino");
                    solicitarCantidad(entrada, inventario, "Cedro");
                    solicitarCantidad(entrada, inventario, "Caoba");
                    solicitarCantidad(entrada, inventario, "Clavos");
                    solicitarCantidad(entrada, inventario, "Barniz");
                    break;
                case 2:
                    System.out.println("\tInventario De Materiales\n");
                    //Solo se llama el metodo de VerInventario que se encuentra en la clase inventario
                    inventario.verMateriales();
                    
                    break;
                case 3:
                    System.out.println("\tRegistrar Orden De Compra\n");
                    entrada.nextLine();
                    System.out.println("Ingrese el numero de orden");
                    String orden = entrada.nextLine();
                    System.out.println("Ingrese la fecha de registro");
                    String fecha = entrada.nextLine();

                    Orden nuevaOrden = new Orden(orden, fecha);
                    System.out.println("NIT del cliente: ");
                    nuevaOrden.cliente.nit = entrada.nextLine();
                    System.out.println("Nombre del cliente: ");
                    nuevaOrden.cliente.nombre = entrada.nextLine();
                    System.out.println("Ingrese la cantidad de sillas de Pino:");
                    int cantidadPino = entrada.nextInt();
                    nuevaOrden.agregarTipoSilla("Pino", cantidadPino);
                    System.out.println("Ingrese la cantidad de sillas de Cedro:");
                    int cantidadCedro = entrada.nextInt();
                    nuevaOrden.agregarTipoSilla("Cedro", cantidadCedro);
                    System.out.println("Ingrese la cantidad de sillas de Caoba:");
                    int cantidadCaoba = entrada.nextInt();
                    nuevaOrden.agregarTipoSilla("Caoba", cantidadCaoba);

                    // Registrar la orden en la clase Proceso (implementa GestionProcesos)
                    proceso.registrarOrden(nuevaOrden);

                    // Registrar la orden en la clase RegistroVentas (como lo hacías antes)
           

                    break;
                case 4:
                    System.out.println("\tOrdenes De Compra Registradas\n");
                    //Solo se llama el metdodo verOrdenes de GestionProcesos
                    proceso.verOrdenes();
                    
                    break;
                case 5:
                    System.out.println("\tIniciar Producción\n");
                    entrada.nextLine();
                    System.out.println("Ingrese el número de orden para iniciar el proceso:");
                    String numeroOrden = entrada.nextLine();
                    // Buscar la orden en la lista de ordenes registradas
                    Orden ordenEnProceso = null;
                    for (Orden ord : proceso.getOrdenes()) {
                        if (ord.getNumeroOrden().equals(numeroOrden)) {
                            ordenEnProceso = ord;
                            break;
                        }
                    }

                    if (ordenEnProceso != null) {
                        proceso.iniciarProceso(ordenEnProceso, inventario);
                    } else {
                        System.out.println("La orden no existe.");
                    }
                    break;
                case 6:
                    System.out.println("\tOrdenes En Proceso\n");
                    proceso.verOrdenesEnProceso();
                    break;
                case 7:
                    System.out.println("\tTerminar Producción\n");
                    proceso.terminarProceso();
                    
                    break;
                case 8:
                    System.out.println("\tVer Ordenes Terminadas\n");
                    proceso.verOrdenesTerminadas();
                    break;
                case 9:
                    System.out.println("\tVentas\n");
                    //Entregar producto terminado
                    proceso.entregar();
                    break;
                case 10:
                    salir = 1;
                    break;
            }
        }
    }
        
    public static void Menu() {
        System.out.println("\n\tMENU PRINCIPAL\n");
        System.out.println("1. Registrar Compras");
        System.out.println("2. Ver Inventario De Materiales");
        System.out.println("3. Registrar Orden De Compra");
        System.out.println("4. Ver Ordenes De Compra En Cola");
        System.out.println("5. Iniciar Producción");
        System.out.println("6. Ver Productos En Proceso");
        System.out.println("7. Terminar Producción");
        System.out.println("8. Ver Productos Terminados");
        System.out.println("9. Ventas");
        System.out.println("10. Salir");
        System.out.println("\nIngresa Una Opción...\n");

    }
    public static void solicitarCantidad(Scanner entrada, Inventario inventario, String material) {
        System.out.println("Ingrese la cantidad de " + material + ":");
        int cantidad = entrada.nextInt();
        inventario.Agregar(new Material(material, cantidad));

	}

}
