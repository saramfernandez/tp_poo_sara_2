package Buquealtoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorTicketIncidencia {
    private static List<TicketIncidencia> tickets = new ArrayList<>();
    private static int proximoId = 1; // Variable para asignar IDs únicos a los tickets

    public static void crearTicketIncidencia(String emailUsuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el título del ticket de incidencia:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese la descripción del ticket de incidencia:");
        String descripcion = scanner.nextLine();

        // Crear un nuevo ticket de incidencia
        TicketIncidencia ticket = new TicketIncidencia(proximoId++, emailUsuario, titulo, descripcion);

        // Agregar el ticket a la lista de tickets
        tickets.add(ticket);

        System.out.println("Ticket de incidencia creado exitosamente con ID: " + (proximoId - 1));
    }

    public static void mostrarTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets de incidencia registrados.");
        } else {
            System.out.println("Tickets de incidencia registrados:");
            for (TicketIncidencia ticket : tickets) {
                System.out.println("ID: " + ticket.getId());
                System.out.println("Email del usuario: " + ticket.getEmailUsuario());
                System.out.println("Título: " + ticket.getTitulo());
                System.out.println("Descripción: " + ticket.getDescripcion());
                System.out.println("Estado: " + ticket.getEstado());
                if (ticket.getEstado().equals("Resuelto")) {
                    System.out.println("Resolución: " + ticket.getResolucion());
                }
                System.out.println("-----------------------------------------");
            }
        }
    }

    public static void resolverTicket(int idTicket, String resolucion) {
        TicketIncidencia ticket = encontrarTicketPorId(idTicket);
        if (ticket != null) {
            ticket.setResolucion(resolucion);
            ticket.setEstado("Resuelto");
            System.out.println("Ticket resuelto exitosamente.");
        } else {
            System.out.println("Ticket no encontrado.");
        }
    }

    private static TicketIncidencia encontrarTicketPorId(int idTicket) {
        for (TicketIncidencia ticket : tickets) {
            if (ticket.getId() == idTicket) {
                return ticket;
            }
        }
        return null;
    }
}
