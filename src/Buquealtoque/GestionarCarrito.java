package Buquealtoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionarCarrito extends GestorReserva {
    public static List<Object> carritoCompras = new ArrayList<>();
    public static int agregarCarrito = 1;
    public static Menu menuCarrito = new Menu();
    

    public static void gestionarCarrito() {
        
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
                // Volver al menú principal
                menuCarrito.mostrarMenu();
                return;
            case 6:
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

        GestionarCompras.detalleCompras(reservas, productos,false);
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // Espera a que el usuario presione Enter y no salir repentinamente
        
    }

    private static void pagarCarrito() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. Tarjeta de crédito");
        System.out.println("2. MercadoPago");
        List<Object> compraPaga = new ArrayList<>();
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
        compraPaga.addAll(carritoCompras);
        GestionarCompras.compras.add(new Compras(1, compraPaga));

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
