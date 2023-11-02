package src.dpoo.uniandes.hamburguesas.modelo;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado implements Producto {
    private ProductoMenu base;
    private List<Ingrediente> agregados = new ArrayList();
    private List<Ingrediente> eliminados = new ArrayList();

    public ProductoAjustado(ProductoMenu base) {
        throw new UnsupportedOperationException();
    }

    public String getNombre() {
        throw new UnsupportedOperationException();
    }

    public int getPrecio() {
        throw new UnsupportedOperationException();
    }

    public String generarTextoFactura() {
        throw new UnsupportedOperationException();
    }
}
