package Buquealtoque;

public class PagoTarjeta extends Pago {
    private String numeroTarjeta;
    private String nombreTitular;
    private String fechaExpiracion;
    private String cvv;

    public PagoTarjeta(String numeroTarjeta, String nombreTitular, String fechaExpiracion, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Realizando pago de " + monto + " con tarjeta de crédito...");
        // Lógica de pago con tarjeta
    }
}
