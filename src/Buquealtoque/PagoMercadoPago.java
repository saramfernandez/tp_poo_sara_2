package Buquealtoque;

import java.util.Scanner;

public class PagoMercadoPago extends Pago {
    private String email;
     Scanner scanner = new Scanner(System.in);

    public PagoMercadoPago(String email) {
        this.email = email;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Realizando pago de " + monto + " con MercadoPago...");// Lógica de pago con MercadoPago
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // Espera a que el usuario presione Enter y no salir repentinamente
        
    }
}
