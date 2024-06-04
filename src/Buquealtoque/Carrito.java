package Buquealtoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carrito extends GestorReserva {
    public static List<Object> carritoCompras = new ArrayList<>();

    public static void gestionarCarrito() {
        Menu menuCarrito = new Menu();
        menuCarrito.mostrarMenuCarrito();

        int opcion = menuCarrito.leerOpcion();

        switch (opcion) {
            case 1:
                // Lógica para alta de reserva
                GestorReserva.gestionarReserva();
                break;
            case 2:
                // Lógica para alta de Experiencias
                GestorPaquetes.mostrarPaquetes();
                GestorPaquetes.seleccionarPaquete();
                break;
            case 3:
                // Lógica para ver carrito
                verCarrito();
                break;
            case 4:
                // Lógica para pagar
                pagarCarrito();
                break;
            case 5:
                // Limpiar carrito y Salir
                System.out.println("Saliendo del carrito...");
                carritoCompras.clear();
                return;
            default:
                System.out.println("Opción inválida");
        }
    }

    private static void verCarrito() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Carrito de compras:");

        List<Reserva> reservas = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();

        // Separar las reservas de los productos
        for (Object item : carritoCompras) {
            if (item instanceof Reserva) {
                reservas.add((Reserva) item);
            } else if (item instanceof Producto) {
                productos.add((Producto) item);
            }
        }

        // Mostrar las reservas de viaje
        if (!reservas.isEmpty()) {
            System.out.println("Reservas de Viaje:");
            System.out.printf("%-12s %-10s %-10s %-10s %-10s %-10s\n", "ID", "Buque", "Destino", "Asiento", "Pagado", "Monto");
            System.out.println("-------------------------------------------------------------------------");
            for (Reserva reserva : reservas) {
                String destino = reserva.getDestino() == 1 ? "Argentina" : "Uruguay";
                String asiento = GestorReserva.convertirAsiento(reserva.getFila(), reserva.getColumna());
                String pagado = reserva.isPagada() ? "Sí" : "No";
                Buque buque = GestorReserva.encontrarBuque(reserva.getBuqueId());
                double montoBuque = buque != null ? buque.getMonto() : 0.0;
                System.out.printf("%-12d %-10s %-10s %-10s %-10s %-10.2f\n", reserva.getId(), reserva.getBuqueId(), destino, asiento, pagado, montoBuque);
            }
        } else {
            System.out.println("No hay reservas de viaje en el carrito.");
        }

        // Mostrar los productos de experiencias
        if (!productos.isEmpty()) {
            System.out.println("\nProductos de Experiencias:");
            System.out.printf("%-12s %-30s %-10s\n", "ID", "Descripción", "Valor");
            System.out.println("------------------------------------------------------------");
            for (Producto producto : productos) {
                System.out.printf("%-12d %-30s %-10.2f\n", producto.getId(), producto.getDescripcion(), producto.getValor());
            }
        } else {
            System.out.println("\nNo hay productos de experiencias en el carrito.");
        }
    }

    private static void pagarCarrito() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. Tarjeta de crédito");
        System.out.println("2. MercadoPago");

        int opcionPago = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        Pago metodoPago;

        switch (opcionPago) {
            case 1:
                System.out.print("Ingrese el número de tarjeta: ");
                String numeroTarjeta = scanner.nextLine();
                System.out.print("Ingrese el nombre del titular: ");
                String nombreTitular = scanner.nextLine();
                System.out.print("Ingrese la fecha de expiración (MM/AA): ");
                String fechaExpiracion = scanner.nextLine();
                System.out.print("Ingrese el CVV: ");
                String cvv = scanner.nextLine();
                metodoPago = new PagoTarjeta(numeroTarjeta, nombreTitular, fechaExpiracion, cvv);
                break;
            case 2:
                System.out.print("Ingrese su correo de MercadoPago: ");
                String email = scanner.nextLine();
                metodoPago = new PagoMercadoPago(email);
                break;
            default:
                System.out.println("Opción inválida");
                return;
        }

        double totalMonto = calcularTotalCarrito();
        metodoPago.realizarPago(totalMonto);

        // Marcar las reservas como pagadas
        for (Object item : carritoCompras) {
            if (item instanceof Reserva) {
                ((Reserva) item).setPagada(true);
            }
        }

        System.out.println("Pago realizado con éxito. Gracias por su compra.");
        carritoCompras.clear();
    }

    private static double calcularTotalCarrito() {
        double total = 0.0;

        for (Object item : carritoCompras) {
            if (item instanceof Reserva) {
                Reserva reserva = (Reserva) item;
                Buque buque = GestorReserva.encontrarBuque(reserva.getBuqueId());
                total += buque != null ? buque.getMonto() : 0.0;
            } else if (item instanceof Producto) {
                Producto producto = (Producto) item;
                total += producto.getValor();
            }
        }

        return total;
    }
}
