package Buquealtoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;
import java.util.stream.Collectors;

public class GestionarCompras extends GestorReserva {
    public static List<Object> carritoCompras = new ArrayList<>();
    public static List<Compras> compras = new ArrayList<>();
    public static int agregarCarrito = 1;
    public static Menu menuCompras = new Menu();
    


    public static void verCompras() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------Mis Compras------------------\n");

        List<Reserva> reservas = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        int id = 0;
        LocalDate fechaCompra = LocalDate.now();

        // Separar las reservas de los productos
        for (Compras listaCompra : compras) {
            List<Object>  compra = listaCompra.getCompras();
            id = listaCompra.getId();
            fechaCompra = listaCompra.getFechaCompra();

            for (Object item : compra) {
                if (item instanceof Reserva) {
                    reservas.add((Reserva) item);
                } else if (item instanceof Producto) {
                    productos.add((Producto) item);
                }
            }
            detalleCompras(reservas, productos,id,fechaCompra, true);
            reservas.clear();
            productos.clear();
        }        
    
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); 
    }




    public static void detalleCompras(List<Reserva> reservas,List<Producto> productos,int id, LocalDate fecha, boolean isCompra) {
        double totalReservas = 0.0;
        double totalProductos = 0.0;
        Scanner scanner = new Scanner(System.in);

        if (isCompra) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\nCompra n° "+ id);
            System.out.println("Fecha de compra: " + fecha);
            System.out.println("--------------------------\n");
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

    public static void generarReporteComprasMes() {
        Scanner scanner = new Scanner(System.in);
        
        // Obtener el año y mes actual
        YearMonth currentYearMonth = YearMonth.now();

        // Filtrar las compras realizadas en el mes actual
        List<Compras> comprasMesActual = compras.stream()
                .filter(compra -> {
                    LocalDate fechaCompra = compra.getFechaCompra(); // agregar la fecha de la compra en la clase Compras
                    YearMonth yearMonthCompra = YearMonth.from(fechaCompra);
                    return yearMonthCompra.equals(currentYearMonth);
                })
                .collect(Collectors.toList());

        if (comprasMesActual.isEmpty()) {
            System.out.println("No hay compras realizadas en el mes actual.");
            return;
        }

        // Imprimir el reporte de compras del mes
        System.out.println("Reporte de compras del mes actual:");
        for (Compras compra : comprasMesActual) {
            List<Object> items = compra.getCompras();
            for (Object item : items) {
                if (item instanceof Reserva) {
                    Reserva reserva = (Reserva) item;
                    System.out.println("Reserva - Cliente: " + reserva.getClienteDni() + ", Buque: " + reserva.getBuqueId() + ", Destino: " + reserva.getDestino() + ", Asiento: " + reserva.getFila() + reserva.getColumna());
                } else if (item instanceof Producto) {
                    Producto producto = (Producto) item;
                    System.out.println("Producto - Descripción: " + producto.getDescripcion() + ", Valor: " + producto.getValor());
                }
            }
        }
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); 
    }
}
