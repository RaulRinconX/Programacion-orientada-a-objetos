package src.dpoo.uniandes.hamburguesas.modelo;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Restaurante {
    private List<Combo> combos;
    private Queue<Pedido> pedidos;
    private List<Producto> menuBase;
    private List<Ingrediente> ingredientes;

    public Restaurante() {
        this.menuBase = new ArrayList<>();
        this.combos = new ArrayList<>();
        this.pedidos = new LinkedList<>();
        this.ingredientes = new ArrayList<>();
    }

    public Pedido iniciarPedido(String nombreCliente, String direccionCliente) {
        Pedido pedido = new Pedido(nombreCliente, direccionCliente);

        pedidos.add(pedido);

        return pedido;
    }

    public void modificarPedidoActual(Producto productoSeleccionado) {
        this.pedidos.peek().agregarProducto(productoSeleccionado);
    }

    public void cerrarYGuardarPedido() throws IOException {
        Pedido pedido = pedidos.remove();

        File facturas = new File("data/facturas");

        if (!facturas.exists()) facturas.mkdirs();

        pedido.guardarFactura(new File("data/facturas/pedido_" + pedido.getIdPedido() + ".txt"));
    }

    public Pedido getPedidoEnCurso() {
        return this.pedidos.peek();
    }

    public List<Producto> darMenu() {
        List<Producto> menuCompleto = new ArrayList<>();

        this.menuBase.sort((p1, p2) -> Integer.compare(p1.getPrecio(), p2.getPrecio()));

        menuCompleto.addAll(this.menuBase);

        this.combos.sort((p1, p2) -> Integer.compare(p1.getPrecio(), p2.getPrecio()));

        menuCompleto.addAll(this.combos);

        return menuCompleto;
    }

    public List<Ingrediente> getIngredientes() {
        return this.ingredientes;
    }

    public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws IOException {
        cargarIngrediente(archivoIngredientes);
        cargarMenu(archivoMenu);
        cargarCombos(archivoCombos);
    }

    private void cargarIngrediente(File archivoIngredientes) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoIngredientes));

        String lineaInformacion = bufferedReader.readLine();

        while (lineaInformacion != null) {
            String[] datos = lineaInformacion.split(";");

            Ingrediente ingrediente = new Ingrediente(datos[0], Integer.parseInt(datos[1]));

            ingredientes.add(ingrediente);

            lineaInformacion = bufferedReader.readLine();
        }
    }

    private void cargarMenu(File archivoMenu) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoMenu));

        String lineaInformacion = bufferedReader.readLine();

        while (lineaInformacion != null) {
            String[] datos = lineaInformacion.split(";");

            ProductoMenu producto = new ProductoMenu(datos[0], Integer.parseInt(datos[1]));

            menuBase.add(producto);

            lineaInformacion = bufferedReader.readLine();
        }
    }

    private void cargarCombos(File archivoCombos) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoCombos));

        String lineaInformacion = bufferedReader.readLine();

        while (lineaInformacion != null) {
            String[] datos = lineaInformacion.split(";");

            Combo combo = new Combo(datos[0], 1 - Double.parseDouble(String.format("0.%s", datos[1].split("%")[0])));

            for (int i = 2; i < datos.length; i++) {
                String nombreProducto = datos[i];

                Producto producto = null;

                for (int j = 0; j < this.menuBase.size() && producto == null; j++) {
                    if (this.menuBase.get(j).getNombre().equals(nombreProducto)) producto = this.menuBase.get(j);
                }

                combo.agregarItemACombo(producto);
            }

            this.combos.add(combo);

            lineaInformacion = bufferedReader.readLine();
        }
    }

    public Pedido buscarPedido(int id) {
        Pedido pedido = null;

        List<Pedido> pedidos = new ArrayList<>();

        pedidos.addAll(this.pedidos);

        for (int i = 0; i < pedidos.size() && pedido == null; i++) {
            if (pedidos.get(i).getIdPedido() == id) pedido = pedidos.get(i);
        }

        return pedido;
    }
}
