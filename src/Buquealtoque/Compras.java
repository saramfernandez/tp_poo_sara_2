package Buquealtoque;

import java.time.LocalDate;
import java.util.List;

public class Compras {
    private int id;
    private List<Object> compras;
    private LocalDate fechaCompra;

    public Compras(int id, List<Object> compras) {
        this.id = id;
        this.compras = compras;
        this.fechaCompra = LocalDate.now();
    }
    public int getId() {
        return id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public List<Object> getCompras() {
        return compras;
    }
}
