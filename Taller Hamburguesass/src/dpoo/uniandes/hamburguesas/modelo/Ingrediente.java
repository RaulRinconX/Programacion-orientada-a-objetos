package src.dpoo.uniandes.hamburguesas.modelo;

public class Ingrediente {
    private String nombre;
    private int costoAdicional;

    public Ingrediente(String nombre, int costoAdicional) {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getCostoAdicional() {
        return this.costoAdicional;
    }
}
