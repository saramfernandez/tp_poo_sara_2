package Buquealtoque;

public class TicketIncidencia {
    private int id;
    private String emailUsuario;
    private String titulo;
    private String descripcion;
    private String estado;
    private String resolucion;

    public TicketIncidencia(int id, String emailUsuario, String titulo, String descripcion) {
        this.id = id;
        this.emailUsuario = emailUsuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = "Pendiente"; // Estado predeterminado
        this.resolucion = ""; 
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
}
