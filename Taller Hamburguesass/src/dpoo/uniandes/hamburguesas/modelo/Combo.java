package src.dpoo.uniandes.hamburguesas.modelo;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Producto {
    private double descuento;
    private String nombreCombo;
    private List<Producto> itemsCombo;

    public Combo(String nombre, double descuento) {
        this.descuento = descuento;
        this.nombreCombo = nombre;
        this.itemsCombo = new ArrayList<>();
    }

    public void agregarItemACombo(Producto itemCombo) {
        this.itemsCombo.add(itemCombo);
    }

    public int getPrecio() {
        double precioTotal = 0;

        for (int i = 0; i < itemsCombo.size(); i++) {
            precioTotal += itemsCombo.get(i).getPrecio() * descuento;
        }

        return (int) precioTotal;
    }

    public String generarTextoFactura() {
        String formato = "\t%s %s $%s";

        int cantidadBarras = 45 - getNombre().length() + Integer.toString(getPrecio()).length();

        String barras = "";

        for (int j = 0; j < cantidadBarras; j++) barras += "-";

        String textoFactura = String.format(formato, getNombre(), barras, getPrecio());

        String formatoDescripcion = "\n\t\t - %s";

        for (int i = 0; i < itemsCombo.size(); i++) {
            Producto producto = itemsCombo.get(i);
            textoFactura += String.format(formatoDescripcion, producto.getNombre());
        }

        return textoFactura;
    }

    public String getNombre() {
        return this.nombreCombo;
    }
}
