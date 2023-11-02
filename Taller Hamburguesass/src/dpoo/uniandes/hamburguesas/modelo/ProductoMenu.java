package src.dpoo.uniandes.hamburguesas.modelo;

public class ProductoMenu implements Producto {
    private String nombre;
    private int precioBase;

    public ProductoMenu(String nombre, int precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPrecio() {
        return precioBase;
    }

    public String generarTextoFactura() {
        String formato = "\t%s %s $%s";

        int cantidadBarras = 45 - getNombre().length() + Integer.toString(getPrecio()).length();

        String barras = "";

        for (int j = 0; j < cantidadBarras; j++) barras += "-";

        return String.format(formato, getNombre(), barras, getPrecio());
    }
}
