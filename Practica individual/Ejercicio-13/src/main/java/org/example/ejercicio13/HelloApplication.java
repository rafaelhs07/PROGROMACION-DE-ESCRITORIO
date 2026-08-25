package org.example.ejercicio13;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class HelloApplication extends Application {

    private Queue<String> colaClientes = new LinkedList<>();

    private TextField tfCliente;

    private Label lblCola;
    private Label lblSiguiente;
    private Label lblCantidad;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Cola de clientes");

        Label lblCliente = new Label("Nombre del cliente:");

        tfCliente = new TextField();
        tfCliente.setPromptText("Ingrese el nombre");

        Button btnAgregar = new Button("Agregar cliente");
        Button btnAtender = new Button("Atender cliente");
        Button btnSiguiente = new Button("Mostrar siguiente");
        Button btnCantidad = new Button("Mostrar cantidad");
        Button btnLimpiar = new Button("Limpiar cola");

        lblCola = new Label("Cola: []");
        lblSiguiente = new Label("Siguiente cliente: ");
        lblCantidad = new Label("Clientes pendientes: 0");
        lblMensaje = new Label("");

        btnAgregar.setOnAction(event -> agregarCliente());
        btnAtender.setOnAction(event -> atenderCliente());
        btnSiguiente.setOnAction(event -> mostrarSiguiente());
        btnCantidad.setOnAction(event -> mostrarCantidad());
        btnLimpiar.setOnAction(event -> limpiarCola());

        HBox botones1 = new HBox(
                btnAgregar,
                btnAtender
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnSiguiente,
                btnCantidad,
                btnLimpiar
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblCliente,
                tfCliente,
                botones1,
                botones2,
                lblCola,
                lblSiguiente,
                lblCantidad,
                lblMensaje
        );

        Scene scene = new Scene(root, 650, 450);

        stage.setTitle("Ejercicio 13");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarCliente() {

        String nombre = tfCliente.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese el nombre del cliente.");
            return;
        }

        colaClientes.offer(nombre);

        lblMensaje.setText("Cliente agregado correctamente.");

        tfCliente.clear();
        tfCliente.requestFocus();

        actualizarDatos();
    }

    private void atenderCliente() {

        if (colaClientes.isEmpty()) {
            lblMensaje.setText("No hay clientes en la cola.");
            return;
        }

        String clienteAtendido = colaClientes.poll();

        lblMensaje.setText(
                "Cliente atendido: " + clienteAtendido
        );

        actualizarDatos();
    }

    private void mostrarSiguiente() {

        if (colaClientes.isEmpty()) {
            lblSiguiente.setText("Siguiente cliente: Ninguno");
            lblMensaje.setText("La cola está vacía.");
            return;
        }

        String siguiente = colaClientes.peek();

        lblSiguiente.setText(
                "Siguiente cliente: " + siguiente
        );

        lblMensaje.setText("");
    }

    private void mostrarCantidad() {

        lblCantidad.setText(
                "Clientes pendientes: " + colaClientes.size()
        );

        lblMensaje.setText("");
    }

    private void actualizarDatos() {

        lblCola.setText(
                "Cola: " + colaClientes
        );

        lblCantidad.setText(
                "Clientes pendientes: " + colaClientes.size()
        );

        if (colaClientes.isEmpty()) {

            lblSiguiente.setText(
                    "Siguiente cliente: Ninguno"
            );

        } else {

            lblSiguiente.setText(
                    "Siguiente cliente: " + colaClientes.peek()
            );
        }
    }

    private void limpiarCola() {

        colaClientes.clear();

        tfCliente.clear();

        lblCola.setText("Cola: []");
        lblSiguiente.setText("Siguiente cliente: Ninguno");
        lblCantidad.setText("Clientes pendientes: 0");
        lblMensaje.setText("Cola limpiada.");

        tfCliente.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}