package Buquealtoque;

import java.util.List;

public class Compras {
    private int id;
    private List<Object> compras;

    public Compras(int id, List<Object> compras) {
        this.id = id;
        this.compras = compras;
    }
    public int getId() {
        return id;
    }
    
    public List<Object> getCompras() {
        return compras;
    }
    
}
