package org.example.ejercicio20;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class HelloApplication extends Application {

    private ArrayList<Producto> productos = new ArrayList<>();
    private Queue<String> colaClientes = new LinkedList<>();
    private Stack<Venta> historialVentas = new Stack<>();

    private double[] totalesVentas = new double[100];
    private int contadorVentas = 0;

    private TextField tfProducto;
    private TextField tfPrecio;
    private TextField tfStock;

    private TextField tfCliente;

    private TextField tfProductoVenta;
    private TextField tfCantidadVenta;

    private Label lblInventario;
    private Label lblCola;
    private Label lblVentaActual;
    private Label lblResumen;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(25));
        root.setSpacing(20);
        root.setStyle("-fx-background-color: #f4f6f8;");

        Label lblTitulo = new Label("Sistema de Gestión");
        lblTitulo.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #263238;"
        );

        Label lblSubtitulo = new Label(
                "Control de inventario, clientes y ventas"
        );

        lblSubtitulo.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-text-fill: #607d8b;"
        );

        VBox panelProductos = crearPanelProductos();
        VBox panelClientes = crearPanelClientes();
        VBox panelVentas = crearPanelVentas();
        VBox panelResultados = crearPanelResultados();

        root.getChildren().addAll(
                lblTitulo,
                lblSubtitulo,
                panelProductos,
                panelClientes,
                panelVentas,
                panelResultados
        );

        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(scroll, 850, 750);

        stage.setTitle("Proyecto Final - Sistema de Gestión");
        stage.setScene(scene);
        stage.show();
    }

    private VBox crearPanelProductos() {

        VBox panel = crearPanel();

        Label titulo = crearTituloSeccion("Inventario de productos");

        Label lblProducto = new Label("Nombre del producto:");
        tfProducto = new TextField();
        tfProducto.setPromptText("Ejemplo: Coca Cola");

        Label lblPrecio = new Label("Precio:");
        tfPrecio = new TextField();
        tfPrecio.setPromptText("Ejemplo: 35");

        Label lblStock = new Label("Cantidad disponible:");
        tfStock = new TextField();
        tfStock.setPromptText("Ejemplo: 20");

        Button btnAgregar = new Button("Agregar producto");
        Button btnBuscar = new Button("Buscar producto");

        estiloBoton(btnAgregar);
        estiloBoton(btnBuscar);

        btnAgregar.setOnAction(event -> agregarProducto());
        btnBuscar.setOnAction(event -> buscarProducto());

        HBox botones = new HBox(10, btnAgregar, btnBuscar);

        lblInventario = new Label("Inventario vacío");
        lblInventario.setWrapText(true);

        panel.getChildren().addAll(
                titulo,
                lblProducto,
                tfProducto,
                lblPrecio,
                tfPrecio,
                lblStock,
                tfStock,
                botones,
                lblInventario
        );

        return panel;
    }

    private VBox crearPanelClientes() {

        VBox panel = crearPanel();

        Label titulo = crearTituloSeccion("Cola de clientes");

        Label lblCliente = new Label("Nombre del cliente:");

        tfCliente = new TextField();
        tfCliente.setPromptText("Ingrese el nombre");

        Button btnAgregar = new Button("Agregar a la cola");
        Button btnSiguiente = new Button("Ver siguiente");

        estiloBoton(btnAgregar);
        estiloBoton(btnSiguiente);

        btnAgregar.setOnAction(event -> agregarCliente());
        btnSiguiente.setOnAction(event -> mostrarSiguienteCliente());

        HBox botones = new HBox(10, btnAgregar, btnSiguiente);

        lblCola = new Label("Clientes pendientes: []");
        lblCola.setWrapText(true);

        panel.getChildren().addAll(
                titulo,
                lblCliente,
                tfCliente,
                botones,
                lblCola
        );

        return panel;
    }

    private VBox crearPanelVentas() {

        VBox panel = crearPanel();

        Label titulo = crearTituloSeccion("Registro de ventas");

        Label lblProducto = new Label("Producto:");

        tfProductoVenta = new TextField();
        tfProductoVenta.setPromptText("Nombre del producto");

        Label lblCantidad = new Label("Cantidad:");

        tfCantidadVenta = new TextField();
        tfCantidadVenta.setPromptText("Cantidad a vender");

        Button btnVender = new Button("Realizar venta");
        Button btnDeshacer = new Button("Deshacer última venta");

        estiloBoton(btnVender);
        estiloBoton(btnDeshacer);

        btnVender.setOnAction(event -> realizarVenta());
        btnDeshacer.setOnAction(event -> deshacerVenta());

        HBox botones = new HBox(10, btnVender, btnDeshacer);

        lblVentaActual = new Label("Última venta: Ninguna");
        lblVentaActual.setWrapText(true);

        panel.getChildren().addAll(
                titulo,
                lblProducto,
                tfProductoVenta,
                lblCantidad,
                tfCantidadVenta,
                botones,
                lblVentaActual
        );

        return panel;
    }

    private VBox crearPanelResultados() {

        VBox panel = crearPanel();

        Label titulo = crearTituloSeccion("Resumen del sistema");

        Button btnResumen = new Button("Mostrar resumen");
        Button btnReiniciar = new Button("Reiniciar sistema");

        estiloBoton(btnResumen);
        estiloBoton(btnReiniciar);

        btnResumen.setOnAction(event -> mostrarResumen());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones = new HBox(10, btnResumen, btnReiniciar);

        lblResumen = new Label("Resumen:");
        lblResumen.setWrapText(true);

        lblMensaje = new Label("");
        lblMensaje.setWrapText(true);

        panel.getChildren().addAll(
                titulo,
                botones,
                lblResumen,
                lblMensaje
        );

        return panel;
    }

    private VBox crearPanel() {

        VBox panel = new VBox();
        panel.setSpacing(10);
        panel.setPadding(new Insets(20));

        panel.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #dfe5e8;" +
                        "-fx-border-radius: 10;"
        );

        return panel;
    }

    private Label crearTituloSeccion(String texto) {

        Label label = new Label(texto);

        label.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #37474f;"
        );

        return label;
    }

    private void estiloBoton(Button boton) {

        boton.setStyle(
                "-fx-background-color: #37474f;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 9 15;" +
                        "-fx-background-radius: 6;"
        );
    }

    private void agregarProducto() {

        String nombre = tfProducto.getText().trim();
        String precioTexto = tfPrecio.getText().trim();
        String stockTexto = tfStock.getText().trim();

        if (nombre.isEmpty()
                || precioTexto.isEmpty()
                || stockTexto.isEmpty()) {

            mostrarError("Complete todos los datos del producto.");
            return;
        }

        if (buscarPorNombre(nombre) != null) {
            mostrarError("El producto ya está registrado.");
            return;
        }

        try {

            double precio = Double.parseDouble(precioTexto);
            int stock = Integer.parseInt(stockTexto);

            if (precio <= 0) {
                mostrarError("El precio debe ser mayor que cero.");
                return;
            }

            if (stock < 0) {
                mostrarError("El stock no puede ser negativo.");
                return;
            }

            Producto producto =
                    new Producto(nombre, precio, stock);

            productos.add(producto);

            actualizarInventario();

            tfProducto.clear();
            tfPrecio.clear();
            tfStock.clear();

            mostrarMensaje("Producto agregado correctamente.");

        } catch (NumberFormatException e) {

            mostrarError("El precio y el stock deben ser números válidos.");
        }
    }

    private void buscarProducto() {

        String nombre = tfProducto.getText().trim();

        if (nombre.isEmpty()) {
            mostrarError("Ingrese el nombre del producto.");
            return;
        }

        Producto producto = buscarPorNombre(nombre);

        if (producto == null) {

            mostrarError("Producto no encontrado.");

        } else {

            tfPrecio.setText(
                    String.valueOf(producto.getPrecio())
            );

            tfStock.setText(
                    String.valueOf(producto.getStock())
            );

            mostrarMensaje("Producto encontrado.");
        }
    }

    private Producto buscarPorNombre(String nombre) {

        for (Producto producto : productos) {

            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }

        return null;
    }

    private void agregarCliente() {

        String cliente = tfCliente.getText().trim();

        if (cliente.isEmpty()) {
            mostrarError("Ingrese el nombre del cliente.");
            return;
        }

        colaClientes.offer(cliente);

        tfCliente.clear();

        actualizarCola();

        mostrarMensaje("Cliente agregado a la cola.");
    }

    private void mostrarSiguienteCliente() {

        if (colaClientes.isEmpty()) {
            mostrarError("No hay clientes esperando.");
            return;
        }

        mostrarMensaje(
                "Siguiente cliente: " + colaClientes.peek()
        );
    }

    private void realizarVenta() {

        if (colaClientes.isEmpty()) {
            mostrarError("Debe agregar un cliente antes de realizar una venta.");
            return;
        }

        String nombreProducto =
                tfProductoVenta.getText().trim();

        String cantidadTexto =
                tfCantidadVenta.getText().trim();

        if (nombreProducto.isEmpty()
                || cantidadTexto.isEmpty()) {

            mostrarError("Ingrese el producto y la cantidad.");
            return;
        }

        Producto producto =
                buscarPorNombre(nombreProducto);

        if (producto == null) {
            mostrarError("El producto no está registrado.");
            return;
        }

        try {

            int cantidad =
                    Integer.parseInt(cantidadTexto);

            if (cantidad <= 0) {
                mostrarError("La cantidad debe ser mayor que cero.");
                return;
            }

            if (cantidad > producto.getStock()) {
                mostrarError("No hay suficiente producto en inventario.");
                return;
            }

            if (contadorVentas >= totalesVentas.length) {
                mostrarError("Se alcanzó el límite de ventas.");
                return;
            }

            String cliente = colaClientes.poll();

            double total =
                    producto.getPrecio() * cantidad;

            producto.setStock(
                    producto.getStock() - cantidad
            );

            Venta venta = new Venta(
                    cliente,
                    producto,
                    cantidad,
                    total
            );

            historialVentas.push(venta);

            totalesVentas[contadorVentas] = total;
            contadorVentas++;

            lblVentaActual.setText(
                    "Última venta: "
                            + cliente
                            + " | "
                            + producto.getNombre()
                            + " | Cantidad: "
                            + cantidad
                            + " | Total: C$ "
                            + String.format("%.2f", total)
            );

            tfProductoVenta.clear();
            tfCantidadVenta.clear();

            actualizarInventario();
            actualizarCola();

            mostrarMensaje("Venta realizada correctamente.");

        } catch (NumberFormatException e) {

            mostrarError("La cantidad debe ser un número entero.");
        }
    }

    private void deshacerVenta() {

        if (historialVentas.isEmpty()) {
            mostrarError("No hay ventas para deshacer.");
            return;
        }

        Venta venta = historialVentas.pop();

        Producto producto = venta.getProducto();

        producto.setStock(
                producto.getStock() + venta.getCantidad()
        );

        colaClientes.offer(venta.getCliente());

        if (contadorVentas > 0) {

            contadorVentas--;

            totalesVentas[contadorVentas] = 0;
        }

        actualizarInventario();
        actualizarCola();

        if (historialVentas.isEmpty()) {

            lblVentaActual.setText(
                    "Última venta: Ninguna"
            );

        } else {

            Venta ultima = historialVentas.peek();

            lblVentaActual.setText(
                    "Última venta: "
                            + ultima.getCliente()
                            + " | "
                            + ultima.getProducto().getNombre()
                            + " | Total: C$ "
                            + String.format("%.2f", ultima.getTotal())
            );
        }

        mostrarMensaje("Última venta deshecha.");
    }

    private void actualizarInventario() {

        if (productos.isEmpty()) {

            lblInventario.setText("Inventario vacío");
            return;
        }

        String texto = "Productos registrados:\n";

        for (Producto producto : productos) {

            texto += producto.getNombre()
                    + " | Precio: C$ "
                    + String.format("%.2f", producto.getPrecio())
                    + " | Stock: "
                    + producto.getStock()
                    + "\n";
        }

        lblInventario.setText(texto);
    }

    private void actualizarCola() {

        lblCola.setText(
                "Clientes pendientes: " + colaClientes
        );
    }

    private void mostrarResumen() {

        double totalVendido = calcularTotalVendido();

        double promedio = calcularPromedioVentas();

        lblResumen.setText(
                "Productos registrados: "
                        + productos.size()
                        + "\nClientes pendientes: "
                        + colaClientes.size()
                        + "\nVentas realizadas: "
                        + contadorVentas
                        + "\nTotal vendido: C$ "
                        + String.format("%.2f", totalVendido)
                        + "\nPromedio por venta: C$ "
                        + String.format("%.2f", promedio)
        );

        mostrarMensaje("Resumen actualizado.");
    }

    private double calcularTotalVendido() {

        double total = 0;

        for (int i = 0; i < contadorVentas; i++) {

            total += totalesVentas[i];
        }

        return total;
    }

    private double calcularPromedioVentas() {

        if (contadorVentas == 0) {
            return 0;
        }

        return calcularTotalVendido() / contadorVentas;
    }

    private void mostrarMensaje(String mensaje) {

        lblMensaje.setStyle(
                "-fx-text-fill: #2e7d32;" +
                        "-fx-font-weight: bold;"
        );

        lblMensaje.setText(mensaje);
    }

    private void mostrarError(String mensaje) {

        lblMensaje.setStyle(
                "-fx-text-fill: #c62828;" +
                        "-fx-font-weight: bold;"
        );

        lblMensaje.setText("Error: " + mensaje);
    }

    private void reiniciar() {

        productos.clear();
        colaClientes.clear();
        historialVentas.clear();

        totalesVentas = new double[100];

        contadorVentas = 0;

        tfProducto.clear();
        tfPrecio.clear();
        tfStock.clear();
        tfCliente.clear();
        tfProductoVenta.clear();
        tfCantidadVenta.clear();

        lblInventario.setText("Inventario vacío");
        lblCola.setText("Clientes pendientes: []");
        lblVentaActual.setText("Última venta: Ninguna");
        lblResumen.setText("Resumen:");
        lblMensaje.setText("");

        mostrarMensaje("Sistema reiniciado.");
    }

    public static void main(String[] args) {
        launch();
    }

    private static class Producto {

        private String nombre;
        private double precio;
        private int stock;

        public Producto(
                String nombre,
                double precio,
                int stock
        ) {
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }

    private static class Venta {

        private String cliente;
        private Producto producto;
        private int cantidad;
        private double total;

        public Venta(
                String cliente,
                Producto producto,
                int cantidad,
                double total
        ) {
            this.cliente = cliente;
            this.producto = producto;
            this.cantidad = cantidad;
            this.total = total;
        }

        public String getCliente() {
            return cliente;
        }

        public Producto getProducto() {
            return producto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public double getTotal() {
            return total;
        }
    }
}