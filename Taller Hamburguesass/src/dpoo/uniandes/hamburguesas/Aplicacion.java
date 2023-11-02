package src.dpoo.uniandes.hamburguesas;

import src.dpoo.uniandes.hamburguesas.modelo.Pedido;
import src.dpoo.uniandes.hamburguesas.modelo.Producto;
import src.dpoo.uniandes.hamburguesas.modelo.Restaurante;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Aplicacion {
    private Restaurante restaurante;

    public Aplicacion() {
        this.restaurante = new Restaurante();
    }
    public void mostrarMenu() {
        System.out.println("\nOpciones de la aplicación\n");
        System.out.println("1. Mostrar menu");
        System.out.println("2. Iniciar un nuevo pedido");
        System.out.println("3. Agregar item a pedido en curso");
        System.out.println("4. Cerrar pedido");
        System.out.println("5. Consultar la información de un pedido dado su id");
        System.out.println("6. Salir de la aplicacion");
    }

    public void ejecutarOpcion() throws IOException {
        System.out.println("Restaurante de Hamburguesas");
        boolean continuar = true;

        while (continuar) {
            try {
                this.mostrarMenu();
                int opcionSeleccionada = Integer.parseInt(this.input("Por favor seleccione una opción"));
                if (opcionSeleccionada == 1) {
                    this.ejecutarmostrarMenu();
                } else if (opcionSeleccionada == 2) {
                    this.ejecutarhacerPedido();
                } else if (opcionSeleccionada == 3) {
                    this.modificarPedidoEnCurso();
                } else if (opcionSeleccionada == 4) {
                    this.ejecutarCerrarPedido();
                } else if (opcionSeleccionada == 5) {
                    this.consultarpedidoconID();
                } else if (opcionSeleccionada == 6) {
                    System.out.println("Saliendo de la aplicación ...");
                    continuar = false;
                } else {
                    System.out.println("Por favor seleccione una opción válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones.");
            }
        }

    }

    private void ejecutarCargarRestaurante() throws IOException {
        File menu = new File("data/menu.txt");
        File combos = new File("data/combos.txt");
        File ingredientes = new File("data/ingredientes.txt");
        restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
    }

    private void ejecutarmostrarMenu() {
        System.out.println("\nMenu del restaurante: \n");
        for (int i = 0; i < restaurante.darMenu().size(); i++) {
            imprimirLineaMenu(i + 1, restaurante.darMenu().get(i).getNombre(), restaurante.darMenu().get(i).getPrecio());
        }
    }

    private void imprimirLineaMenu(int opcion, String nombre, int precio) {
        String formato = "\t%s. %s %s $%s";

        int cantidadBarras = 45 - (Integer.toString(opcion).length() + nombre.length() + Integer.toString(precio).length());

        String barras = "";

        for (int i = 0; i < cantidadBarras; i++) barras += "-";

        System.out.println(String.format(formato, opcion, nombre, barras, precio));
    }

    private void ejecutarhacerPedido() {
        String cliente = input("Nombre");
        String direccion = input("Dirección");

        Pedido pedido = restaurante.iniciarPedido(cliente, direccion);

        System.out.println("Pedido iniciado con ID: " + pedido.getIdPedido());
    }

    private void modificarPedidoEnCurso() {
        Pedido pedido = restaurante.getPedidoEnCurso();

        System.out.println("Modificando el pedido con ID: " + pedido.getIdPedido());

        ejecutarmostrarMenu();

        String productoDeseado = input("Ingrese el numero correspondiente al producto que desea añadir");

        List<Producto> menu = restaurante.darMenu();

        Producto productoSeleccionado = menu.get(Integer.parseInt(productoDeseado) - 1);

        restaurante.modificarPedidoActual(productoSeleccionado);

        System.out.println("Se añadió " + productoSeleccionado.getNombre() + " al pedido");
    }

    private void ejecutarCerrarPedido() throws IOException {
        restaurante.cerrarYGuardarPedido();
    }

    private void consultarpedidoconID() {
        System.out.println("\nConsultando el pedido con una ID: ");
        int ID = Integer.parseInt(this.input("Ingrese el ID del pedido que desea consultar"));

        Pedido pedido = restaurante.buscarPedido(ID);

        System.out.println(pedido);
    }

    public String input(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException var3) {
            System.out.println("Error leyendo de la consola");
            var3.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        Aplicacion aplicacion = new Aplicacion();
        aplicacion.ejecutarCargarRestaurante();
        aplicacion.ejecutarOpcion();
    }
}
