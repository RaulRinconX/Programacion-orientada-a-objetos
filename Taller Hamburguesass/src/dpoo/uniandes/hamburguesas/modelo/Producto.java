package src.dpoo.uniandes.hamburguesas.modelo;

public interface Producto {
    int getPrecio();

    String getNombre();

    String generarTextoFactura();
}
