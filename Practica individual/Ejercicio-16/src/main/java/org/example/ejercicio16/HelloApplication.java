package org.example.ejercicio16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication extends Application {

    private ArrayList<ProductoVenta> productos = new ArrayList<>();

    private TextField tfProducto;
    private TextField tfPrecio;
    private TextField tfCantidad;

    private Label lblLista;
    private Label lblSubtotal;
    private Label lblDescuento;
    private Label lblIva;
    private Label lblTotal;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(12);

        Label lblTitulo = new Label("Sistema de ventas");

        Label lblProducto = new Label("Producto:");
        tfProducto = new TextField();
        tfProducto.setPromptText("Nombre del producto");

        Label lblPrecio = new Label("Precio:");
        tfPrecio = new TextField();
        tfPrecio.setPromptText("Precio");

        Label lblCantidad = new Label("Cantidad:");
        tfCantidad = new TextField();
        tfCantidad.setPromptText("Cantidad");

        Button btnAgregar = new Button("Agregar producto");
        Button btnCalcular = new Button("Calcular venta");
        Button btnEliminar = new Button("Eliminar último");
        Button btnReiniciar = new Button("Reiniciar");

        lblLista = new Label("Productos:");
        lblSubtotal = new Label("Subtotal: C$ 0.00");
        lblDescuento = new Label("Descuento: C$ 0.00");
        lblIva = new Label("IVA: C$ 0.00");
        lblTotal = new Label("Total: C$ 0.00");
        lblMensaje = new Label("");

        btnAgregar.setOnAction(event -> agregarProducto());
        btnCalcular.setOnAction(event -> calcularVenta());
        btnEliminar.setOnAction(event -> eliminarUltimo());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones = new HBox(
                btnAgregar,
                btnCalcular,
                btnEliminar,
                btnReiniciar
        );

        botones.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblProducto,
                tfProducto,
                lblPrecio,
                tfPrecio,
                lblCantidad,
                tfCantidad,
                botones,
                lblLista,
                lblSubtotal,
                lblDescuento,
                lblIva,
                lblTotal,
                lblMensaje
        );

        Scene scene = new Scene(root, 750, 600);

        stage.setTitle("Ejercicio 16");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarProducto() {

        String nombre = tfProducto.getText().trim();

        if (nombre.isEmpty()
                || tfPrecio.getText().isEmpty()
                || tfCantidad.getText().isEmpty()) {

            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        try {

            double precio = Double.parseDouble(tfPrecio.getText());
            int cantidad = Integer.parseInt(tfCantidad.getText());

            if (precio <= 0) {
                lblMensaje.setText("El precio debe ser mayor que 0.");
                return;
            }

            if (cantidad <= 0) {
                lblMensaje.setText("La cantidad debe ser mayor que 0.");
                return;
            }

            ProductoVenta producto =
                    new ProductoVenta(nombre, precio, cantidad);

            productos.add(producto);

            mostrarProductos();
            limpiarCampos();

            lblMensaje.setText("Producto agregado correctamente.");

        } catch (NumberFormatException e) {

            lblMensaje.setText("Precio o cantidad no válidos.");
        }
    }

    private void calcularVenta() {

        if (productos.isEmpty()) {
            lblMensaje.setText("Debe agregar al menos un producto.");
            return;
        }

        double subtotal = calcularSubtotal();
        double descuento = calcularDescuento(subtotal);

        double subtotalConDescuento =
                subtotal - descuento;

        double iva =
                calcularIva(subtotalConDescuento);

        double total =
                subtotalConDescuento + iva;

        lblSubtotal.setText(
                String.format("Subtotal: C$ %.2f", subtotal)
        );

        lblDescuento.setText(
                String.format("Descuento: C$ %.2f", descuento)
        );

        lblIva.setText(
                String.format("IVA: C$ %.2f", iva)
        );

        lblTotal.setText(
                String.format("Total: C$ %.2f", total)
        );

        lblMensaje.setText("Venta calculada correctamente.");
    }

    private double calcularSubtotal() {

        double subtotal = 0;

        for (ProductoVenta producto : productos) {

            subtotal += producto.getPrecio()
                    * producto.getCantidad();
        }

        return subtotal;
    }

    private double calcularDescuento(double subtotal) {

        if (subtotal >= 1000) {
            return subtotal * 0.10;
        }

        return 0;
    }

    private double calcularIva(double monto) {

        return monto * 0.15;
    }

    private void mostrarProductos() {

        String texto = "";

        for (ProductoVenta producto : productos) {

            double subtotal =
                    producto.getPrecio()
                            * producto.getCantidad();

            texto += "\n"
                    + producto.getNombre()
                    + " | Precio: C$ "
                    + producto.getPrecio()
                    + " | Cantidad: "
                    + producto.getCantidad()
                    + " | Subtotal: C$ "
                    + subtotal;
        }

        lblLista.setText("Productos:" + texto);
    }

    private void eliminarUltimo() {

        if (productos.isEmpty()) {
            lblMensaje.setText("No hay productos para eliminar.");
            return;
        }

        ProductoVenta eliminado =
                productos.remove(productos.size() - 1);

        mostrarProductos();

        lblMensaje.setText(
                "Producto eliminado: " + eliminado.getNombre()
        );

        limpiarTotales();
    }

    private void limpiarCampos() {

        tfProducto.clear();
        tfPrecio.clear();
        tfCantidad.clear();

        tfProducto.requestFocus();
    }

    private void limpiarTotales() {

        lblSubtotal.setText("Subtotal: C$ 0.00");
        lblDescuento.setText("Descuento: C$ 0.00");
        lblIva.setText("IVA: C$ 0.00");
        lblTotal.setText("Total: C$ 0.00");
    }

    private void reiniciar() {

        productos.clear();

        limpiarCampos();
        limpiarTotales();

        lblLista.setText("Productos:");
        lblMensaje.setText("Sistema reiniciado.");
    }

    public static void main(String[] args) {
        launch();
    }

    private static class ProductoVenta {

        private String nombre;
        private double precio;
        private int cantidad;

        public ProductoVenta(
                String nombre,
                double precio,
                int cantidad
        ) {
            this.nombre = nombre;
            this.precio = precio;
            this.cantidad = cantidad;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public int getCantidad() {
            return cantidad;
        }
    }
}