package Buquealtoque;

import java.util.Scanner;

public class Menu {
    public void mostrarMenu() {
        System.out.println("\n[1] Comprar");
        System.out.println("[2] Alta de cliente");
        System.out.println("[3] Pagar reserva");
        System.out.println("[4] Mostrar todos los paquetes");
        System.out.println("[5] Buscar paquete por ID");
        System.out.println("[6] Ver mis compras");
        System.out.println("[7] Carga de ticket");
        System.out.println("[8] Mostrar ticket");
        System.out.println("[9] Reporte compras");
        System.out.println("[0] Salir\n");
        System.out.println("Ingrese la opción deseada:");
    }

    public void mostrarMenuCarrito() {
        System.out.println("\n[1] Alta de reserva");
        System.out.println("[2] Alta de Experiencias");
        System.out.println("[3] Ver carrito");
        System.out.println("[4] Pagar");
        System.out.println("[5] Volver al menú principal");
        System.out.println("[6] Limpiar carrito y Salir\n");
        
        System.out.println("Ingrese la opción deseada:");
    }

    public void mostrarMenuCompras() {
        System.out.println("\n[1] Ver reservas");
        System.out.println("[2] Ver productos");
        System.out.println("[3] Ver todas las compras");
        System.out.println("[4] Volver al menú principal");
        System.out.println("Ingrese la opción deseada:");
    }


    public int leerOpcion() {
        Scanner opcion = new Scanner(System.in);
        return opcion.nextInt();
    }
}
