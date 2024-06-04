package Buquealtoque;

import java.util.Scanner;

public class Principal {
    public static Persona usuarioAutenticado = null;

    public static void main(String[] args) {
        while (true) {
            // Iniciar sesión
            usuarioAutenticado = Login.iniciarSesion();

            // Mostrar el menú principal
            mostrarMenuPrincipal(usuarioAutenticado);
        }
    }

    private static void mostrarMenuPrincipal(Persona usuarioAutenticado) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        while (true) {
            menu.mostrarMenu();
            int opcion = menu.leerOpcion();

            switch (opcion) {
                case 1:
                    // Lógica para alta de reserva
                    GestionarCarrito.gestionarCarrito();
                    break;
                case 2:
                    // Lógica para alta de cliente
               	 if (usuarioAutenticado.getTipo().equalsIgnoreCase("Cliente")) {
                     System.out.println("El usuario no tiene permisos para esta acción.");
                 } else {
                	 GestorPersona.registrarNuevoUsuario();
                 }
                    //GestorPersona.registrarNuevoUsuario();
                    break;
                case 3:
                    // Lógica para pagar reserva (implementación futura)
                    //System.out.println("Pagar reserva (implementación futura)");
                    GestionarCarrito.pagarCarrito();
                    break;
                case 4:
                    // Mostrar todos los paquetes
                    GestorPaquetes.mostrarPaquetes();
                    break;
                case 5:
                    // Buscar paquete por ID
                    GestorPaquetes.mostrarPaquetePorId();
                    break;
                case 6:
                    // Ver mis reservas
                    //GestorReserva.verMisReservas();
                    GestionarCompras.verCompras();
                    break;
                case 7:
                    GestorTicketIncidencia.crearTicketIncidencia(usuarioAutenticado.getEmail());
                    break;
    
                case 8:
                if(usuarioAutenticado.getTipo().equalsIgnoreCase("Soporte")||usuarioAutenticado.getTipo().equalsIgnoreCase("Administrador")){
                    GestorTicketIncidencia.mostrarTickets();
                    // Resolver ticket
                   System.out.println("Seleccione el ticket a resolver (Ingrese ID), elija 0 para salir:");
                   int idTicket = scanner.nextInt();
                   scanner.nextLine(); // Limpiar buffer
                   if (idTicket == 0) {
                       break;
                   }
                   System.out.println("Ingrese la descripción de la resolución:");
                   String resolucion = scanner.nextLine();
                   GestorTicketIncidencia.resolverTicket(idTicket, resolucion);
                   break;
                }
                else{
                    System.out.println("El usuario no tiene permisos para esta accion");
                }
                break;
                case 9:
                    // Reporte de compras
                    GestionarCompras.generarReporteComprasMes();
                    break;
                case 0:
                    System.out.println("Cerrando sesión...");
                    return; // Volver al inicio de sesión
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }
}
