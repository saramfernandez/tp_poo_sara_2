package Buquealtoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorReserva {
    protected static List<Buque> buques = new ArrayList<>();
    private static List<Reserva> reservas = new ArrayList<>();
    
    static {
        // Crear algunos buques
        buques.add(new Buque("B001", 1,45000)); // 1 es Argentina
        buques.add(new Buque("B002", 2,65000)); // 2 es Uruguay
    }


    public static void gestionarReserva() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el DNI del cliente:");
        String dni = scanner.nextLine();

        System.out.println("¿A qué destino desea viajar?\n");
        System.out.println("[1] Argentina\n");
        System.out.println("[2] Uruguay\n");
        System.out.println("Opcion: ");
        int destino = scanner.nextInt();
        scanner.nextLine(); 

        Buque buque = encontrarBuquePorDestino(destino);
        if (buque != null) {
            
            buque.mostrarAsientos();
            System.out.println("Ingrese el asiento (por ejemplo, 1A, 2B):");
            String asientoId = scanner.nextLine().toUpperCase();;

            int[] filaColumna = buque.getFilaColumnaFromId(asientoId);
            if (filaColumna == null) {
                System.out.println("Asiento incorrecto. Seleccione un asiento correcto.");
                return;
            }

            int fila = filaColumna[0];
            int columna = filaColumna[1];
            
            if (!buque.getAsientos()[fila][columna]) {
                buque.getAsientos()[fila][columna] = true;
                reservas.add(new Reserva(dni, buque.getId(), destino, fila, columna));
                GestionarCarrito.carritoCompras.add(new Reserva(dni, buque.getId(), destino, fila, columna));
                System.out.println("Su Reserva fue  realizada exitosamente.");
                System.out.println("¿Desea agregar algo más al carrito? 1. Si 2. No");
                GestionarCarrito.agregarCarrito = scanner.nextInt();

                if (GestionarCarrito.agregarCarrito == 1) {
                    //gestionarReserva();
                	GestionarCarrito.gestionarCarrito();
                } else {
                    System.out.println("Gracias por su compra");
                }
            } else {
                System.out.println("Asiento ya está ocupado.");
            }
        } else {
            System.out.println("Destino no disponible.");
        }
    }


    //Esto ya quedo Viejo, no se usa mas

    /*public static void verMisReservas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su DNI para ver sus reservas:");
        String dni = scanner.nextLine();

        System.out.println("Reservas del cliente con DNI " + dni + ":");

        boolean found = false;
        for (Reserva reserva : reservas) {
            if (reserva.getClienteDni().equals(dni)) {
                System.out.println("ID de reserva: " + reserva.getId());
                System.out.println("Buque: " + reserva.getBuqueId());
                System.out.println("Destino: " + (reserva.getDestino() == 1 ? "Argentina" : "Uruguay")); // Mostrar el destino del buque como texto
                System.out.println("Asiento: " + convertirAsiento(reserva.getFila(), reserva.getColumna()));
                
                // Verificar si la reserva está pagada en el carrito
                boolean pagada = false;
                for (Object item : GestionarCarrito.carritoCompras) {
                    if (item instanceof Reserva) {
                        Reserva carritoReserva = (Reserva) item;
                        if (reserva.getDestino() == 1 && carritoReserva.isPagada()) {
                            pagada = true;
                            break;
                        }
                    }
                }
                
                System.out.println("Pagado: " + pagada);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No hay reservas para este DNI.");
        }
    }*/


    public static String convertirAsiento(int fila, int columna) {
        char letraColumna = (char) ('A' + columna); 
        return (fila + 1) + String.valueOf(letraColumna); 
    }

    protected static Buque encontrarBuque(String id) {
        for (Buque buque : buques) {
            if (buque.getId().equals(id)) {
                return buque;
            }
        }
        return null;
    }

    private static Buque encontrarBuquePorDestino(int destino) {
        for (Buque buque : buques) {
            if (buque.getDestino() == destino) {
                return buque;
            }
        }
        return null;
    }
}
