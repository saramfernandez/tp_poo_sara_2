package Buquealtoque;

public class Reserva {
    private String clienteDni;
    private String buqueId;
    private int fila;
    private int columna;
    private static int contadorReservas = 500; // Contador para generar IDs únicos
    private int id;
    private boolean pagada;
    private int destino;

    public Reserva(String clienteDni, String buqueId, int destino, int fila, int columna) {
    	this.id = contadorReservas++;
    	this.clienteDni = clienteDni;
        this.buqueId = buqueId;
        this.destino = destino;
        this.fila = fila;
        this.columna = columna;
        this.pagada = false; // Por defecto, la reserva está marcada como no pagada, la idea es que cambie despues de pagarla (eso lo vemos despues)

    }

    public int getId() {
        return id;
    }
    
    public String getClienteDni() {
        return clienteDni;
    }
    
    public int getDestino() {
    	return destino;
    }

    public String getBuqueId() {
        return buqueId;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    public boolean isPagada() {
        return pagada;
    }


    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public double calcularMonto() {
        Buque buque = GestorReserva.encontrarBuque(buqueId);
        if (buque != null) {
            return buque.getMonto();
        } else {
            return 0.0; // O devuelve un valor predeterminado si el buque no se encuentra
        }
    }
}
