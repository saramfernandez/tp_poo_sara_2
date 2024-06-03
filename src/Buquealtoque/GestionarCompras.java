package Buquealtoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionarCompras extends GestorReserva {
    public static List<Object> carritoCompras = new ArrayList<>();
    public static List<Compras> compras = new ArrayList<>();
    public static int agregarCarrito = 1;
    public static Menu menuCompras = new Menu();
    

    public static void gestionarCompras() {
        
        menuCompras.mostrarMenuCompras();

        int opcion = menuCompras.leerOpcion();

        switch (opcion) {
            case 1:
                // Ver reservas
                GestorReserva.verMisReservas();
                break;
            case 2:
                // Ver productos
                System.out.println("A desarrollar...");
                break;
            case 3:
                // Ver compras
                verCompras();
                break;
            case 4:
                 // Volver al menú principal
                menuCompras.mostrarMenu();
                return;
            default:
                System.out.println("Opción inválida");
        }
    }

    public static void verCompras() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Compras:");

        List<Reserva> reservas = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();

        // Separar las reservas de los productos
        for (Compras listaCompra : compras) {
            List<Object>  compra = listaCompra.getCompras();
            
            for (Object item : compra) {
                if (item instanceof Reserva) {
                    reservas.add((Reserva) item);
                } else if (item instanceof Producto) {
                    productos.add((Producto) item);
                }
                detalleCompras(reservas,productos,true);
            }
            
        }
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // Espera a que el usuario presione Enter y no salir repentinamente
        
    }

    public static void detalleCompras(List<Reserva> reservas,List<Producto> productos, boolean isCompra) {
        double totalReservas = 0.0;
        double totalProductos = 0.0;
        Scanner scanner = new Scanner(System.in);

        // Mostrar las reservas de viaje
        if (!reservas.isEmpty()) {
            System.out.println("Reservas de Viaje:");
            System.out.printf("%-12s %-10s %-10s %-10s %-10s %-10s\n", "ID", "Buque", "Destino", "Asiento", "Pagado", "Monto");
            System.out.println("-------------------------------------------------------------------------");
            for (Reserva reserva : reservas) {
                String destino = reserva.getDestino() == 1 ? "Argentina" : "Uruguay";
                String asiento = GestorReserva.convertirAsiento(reserva.getFila(), reserva.getColumna());
                String pagado = reserva.isPagada() ? "Sí" : "No";
               /* Buque buque = GestorReserva.encontrarBuque(reserva.getBuqueId());
                double montoBuque = buque != null ? buque.getMonto() : 0.0;
                System.out.printf("%-12d %-10s %-10s %-10s %-10s %-10.2f\n", reserva.getId(), reserva.getBuqueId(), destino, asiento, pagado, montoBuque);
                */
                double montoReserva = reserva.calcularMonto();
                System.out.printf("%-12d %-10s %-10s %-10s %-10s %-10.2f\n", reserva.getId(), reserva.getBuqueId(), destino, asiento, pagado, montoReserva);
                totalReservas += montoReserva; // Sumar el monto de la reserva al total
            }
        } else {
            System.out.println("No hay reservas de viaje.");
        }
        
        // Mostrar los productos de experiencias
        if (!productos.isEmpty()) {
            System.out.println("\nProductos de Experiencias:");
            System.out.printf("%-12s %-30s %-10s\n", "ID", "Descripción", "Valor");
            System.out.println("------------------------------------------------------------");
            for (Producto producto : productos) {
                System.out.printf("%-12d %-30s %-10.2f\n", producto.getId(), producto.getDescripcion(), producto.getValor());
                totalProductos += producto.getValor();
            }
        } else {
            System.out.println("\nNo hay productos de experiencias en el carrito.");
        }
        // Mostrar el total general
        double totalGeneral = totalReservas + totalProductos;
        System.out.println("Total Reservas: " + totalReservas);
        System.out.println("Total Productos: " + totalProductos);
        System.out.println("Total General: " + totalGeneral);
        
        
    }
}
