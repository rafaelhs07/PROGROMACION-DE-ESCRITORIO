package org.example.ejercicio7;

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

    private ArrayList<Producto> productos = new ArrayList<>();

    private TextField tfNombre;
    private TextField tfPrecio;
    private TextField tfCantidad;

    private Label lblLista;
    private Label lblTotal;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(12);

        Label lblTitulo = new Label("Sistema de inventario");

        Label lblNombre = new Label("Nombre del producto:");
        tfNombre = new TextField();
        tfNombre.setPromptText("Ingrese el nombre");

        Label lblPrecio = new Label("Precio:");
        tfPrecio = new TextField();
        tfPrecio.setPromptText("Ingrese el precio");

        Label lblCantidad = new Label("Cantidad:");
        tfCantidad = new TextField();
        tfCantidad.setPromptText("Ingrese la cantidad");

        Button btnAgregar = new Button("Agregar");
        Button btnBuscar = new Button("Buscar");
        Button btnModificar = new Button("Modificar cantidad");
        Button btnEliminar = new Button("Eliminar");
        Button btnTotal = new Button("Calcular total");
        Button btnLimpiar = new Button("Limpiar");

        lblLista = new Label("Productos:");
        lblTotal = new Label("Valor total: C$ 0.00");
        lblMensaje = new Label("");

        btnAgregar.setOnAction(event -> agregarProducto());
        btnBuscar.setOnAction(event -> buscarProducto());
        btnModificar.setOnAction(event -> modificarCantidad());
        btnEliminar.setOnAction(event -> eliminarProducto());
        btnTotal.setOnAction(event -> calcularTotal());
        btnLimpiar.setOnAction(event -> limpiarCampos());

        HBox botones1 = new HBox(
                btnAgregar,
                btnBuscar,
                btnModificar
        );
        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnEliminar,
                btnTotal,
                btnLimpiar
        );
        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblNombre,
                tfNombre,
                lblPrecio,
                tfPrecio,
                lblCantidad,
                tfCantidad,
                botones1,
                botones2,
                lblLista,
                lblTotal,
                lblMensaje
        );

        Scene scene = new Scene(root, 750, 550);

        stage.setTitle("Ejercicio 7");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarProducto() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty() || tfPrecio.getText().isEmpty()
                || tfCantidad.getText().isEmpty()) {

            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        if (buscarPorNombre(nombre) != null) {
            lblMensaje.setText("El producto ya existe.");
            return;
        }

        try {

            double precio = Double.parseDouble(tfPrecio.getText());
            int cantidad = Integer.parseInt(tfCantidad.getText());

            if (precio <= 0) {
                lblMensaje.setText("El precio debe ser mayor que 0.");
                return;
            }

            if (cantidad < 0) {
                lblMensaje.setText("La cantidad no puede ser negativa.");
                return;
            }

            Producto producto = new Producto(
                    nombre,
                    precio,
                    cantidad
            );

            productos.add(producto);

            actualizarLista();
            calcularTotal();
            limpiarCampos();

            lblMensaje.setText("Producto agregado correctamente.");

        } catch (NumberFormatException e) {

            lblMensaje.setText("Precio o cantidad no válidos.");
        }
    }

    private void buscarProducto() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese el nombre del producto.");
            return;
        }

        Producto producto = buscarPorNombre(nombre);

        if (producto != null) {

            tfPrecio.setText(
                    String.valueOf(producto.getPrecio())
            );

            tfCantidad.setText(
                    String.valueOf(producto.getCantidad())
            );

            lblMensaje.setText("Producto encontrado.");

        } else {

            lblMensaje.setText("Producto no encontrado.");
        }
    }

    private void modificarCantidad() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty() || tfCantidad.getText().isEmpty()) {
            lblMensaje.setText(
                    "Ingrese el nombre y la nueva cantidad."
            );
            return;
        }

        Producto producto = buscarPorNombre(nombre);

        if (producto == null) {
            lblMensaje.setText("Producto no encontrado.");
            return;
        }

        try {

            int nuevaCantidad = Integer.parseInt(
                    tfCantidad.getText()
            );

            if (nuevaCantidad < 0) {
                lblMensaje.setText(
                        "La cantidad no puede ser negativa."
                );
                return;
            }

            producto.setCantidad(nuevaCantidad);

            actualizarLista();
            calcularTotal();

            lblMensaje.setText(
                    "Cantidad modificada correctamente."
            );

        } catch (NumberFormatException e) {

            lblMensaje.setText("Ingrese una cantidad válida.");
        }
    }

    private void eliminarProducto() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText(
                    "Ingrese el producto que desea eliminar."
            );
            return;
        }

        Producto producto = buscarPorNombre(nombre);

        if (producto != null) {

            productos.remove(producto);

            actualizarLista();
            calcularTotal();
            limpiarCampos();

            lblMensaje.setText(
                    "Producto eliminado correctamente."
            );

        } else {

            lblMensaje.setText("Producto no encontrado.");
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

    private void calcularTotal() {

        double total = 0;

        for (Producto producto : productos) {

            total += producto.getPrecio()
                    * producto.getCantidad();
        }

        lblTotal.setText(
                String.format("Valor total: C$ %.2f", total)
        );
    }

    private void actualizarLista() {

        String texto = "";

        for (Producto producto : productos) {

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

    private void limpiarCampos() {

        tfNombre.clear();
        tfPrecio.clear();
        tfCantidad.clear();

        tfNombre.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

    private static class Producto {

        private String nombre;
        private double precio;
        private int cantidad;

        public Producto(
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

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }
}