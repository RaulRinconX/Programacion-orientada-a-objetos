package src.dpoo.uniandes.hamburguesas.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int numeroPedidos = 0;
    private int idPedido;
    private String nombreCliente;
    private String direccionCliente;
    private List<Producto> itemsPedido = new ArrayList();

    public Pedido(String nombreCliente, String direccionCliente) {
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.idPedido = numeroPedidos;
        numeroPedidos += 1;
    }

    public int getIdPedido() {
        return this.idPedido;
    }

    public void agregarProducto(Producto nuevoItem) {
        itemsPedido.add(nuevoItem);
    }

    private int getPrecioNetoPedido() {
        int total = 0;

        for (int i = 0; i < itemsPedido.size(); i++) {
            total += itemsPedido.get(i).getPrecio() - (itemsPedido.get(i).getPrecio() * 0.19);
        }

        return total;
    }

    private int getPrecioTotalPedido() {
        int total = 0;

        for (int i = 0; i < itemsPedido.size(); i++) {
            total += itemsPedido.get(i).getPrecio();
        }

        return total;
    }

    private int getPrecioIVAPedido() {
        int total = 0;

        for (int i = 0; i < itemsPedido.size(); i++) {
            total += (itemsPedido.get(i).getPrecio() * 0.19);
        }

        return total;
    }

    private String generarTextoFactura() {
        throw new UnsupportedOperationException();
    }

    public void guardarFactura(File archivo) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivo));

        bufferedWriter.write("HAMBURGUESAS");
        bufferedWriter.write("\nID Pedido: " + idPedido);
        bufferedWriter.write("\nCliente: " + nombreCliente);
        bufferedWriter.write("\nDirección: " + direccionCliente);
        bufferedWriter.write("\nDescripción:");

        for(int i = 0; i < itemsPedido.size(); i++) {
            Producto producto = itemsPedido.get(i);

            int cantidadBarras = 45 - producto.getNombre().length() + Integer.toString(producto.getPrecio()).length();

            String barras = "";

            for (int j = 0; j < cantidadBarras; j++) barras += "-";

            bufferedWriter.write("\n" + producto.generarTextoFactura());
        }

        bufferedWriter.write("\nVALOR NETO: $" + getPrecioNetoPedido());
        bufferedWriter.write("\nIVA: $" + getPrecioIVAPedido());
        bufferedWriter.write("\nTOTAL: $" + getPrecioTotalPedido());

        bufferedWriter.close();
    }

    @Override
    public String toString() {
        String pedido = "ID Pedido: " + idPedido;
        pedido += "\nCliente: " + nombreCliente;
        pedido += "\nDireccion: " + direccionCliente;
        pedido += "\nDescripción:";

        for(int i = 0; i < itemsPedido.size(); i++) {
            Producto producto = itemsPedido.get(i);

            int cantidadBarras = 45 - producto.getNombre().length() + Integer.toString(producto.getPrecio()).length();

            String barras = "";

            for (int j = 0; j < cantidadBarras; j++) barras += "-";

            pedido += "\n" + producto.generarTextoFactura();
        }

        pedido += "\nVALOR NETO: $" + getPrecioNetoPedido();
        pedido += "\nIVA: $" + getPrecioIVAPedido();
        pedido += "\nTOTAL: $" + getPrecioTotalPedido();

        return pedido;
    }
}
