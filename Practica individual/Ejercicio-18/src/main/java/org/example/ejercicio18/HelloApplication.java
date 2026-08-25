package org.example.ejercicio18;

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
import java.util.LinkedList;
import java.util.Queue;

public class HelloApplication extends Application {

    private Queue<Cliente> colaClientes = new LinkedList<>();
    private ArrayList<Cliente> historial = new ArrayList<>();

    private int numeroTurno = 1;

    private TextField tfNombre;

    private Label lblCola;
    private Label lblActual;
    private Label lblPendientes;
    private Label lblHistorial;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(12);

        Label lblTitulo = new Label("Sistema de turnos");

        Label lblNombre = new Label("Nombre del cliente:");

        tfNombre = new TextField();
        tfNombre.setPromptText("Ingrese el nombre");

        Button btnRegistrar = new Button("Registrar cliente");
        Button btnAtender = new Button("Atender cliente");
        Button btnSiguiente = new Button("Mostrar siguiente");
        Button btnPendientes = new Button("Mostrar pendientes");
        Button btnHistorial = new Button("Mostrar historial");
        Button btnReiniciar = new Button("Reiniciar");

        lblCola = new Label("Cola: []");
        lblActual = new Label("Cliente actual: Ninguno");
        lblPendientes = new Label("Clientes pendientes: 0");
        lblHistorial = new Label("Historial:");
        lblMensaje = new Label("");

        btnRegistrar.setOnAction(event -> registrarCliente());
        btnAtender.setOnAction(event -> atenderCliente());
        btnSiguiente.setOnAction(event -> mostrarSiguiente());
        btnPendientes.setOnAction(event -> mostrarPendientes());
        btnHistorial.setOnAction(event -> mostrarHistorial());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones1 = new HBox(
                btnRegistrar,
                btnAtender,
                btnSiguiente
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnPendientes,
                btnHistorial,
                btnReiniciar
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblNombre,
                tfNombre,
                botones1,
                botones2,
                lblActual,
                lblPendientes,
                lblCola,
                lblHistorial,
                lblMensaje
        );

        Scene scene = new Scene(root, 750, 550);

        stage.setTitle("Ejercicio 18");
        stage.setScene(scene);
        stage.show();
    }

    private void registrarCliente() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese el nombre del cliente.");
            return;
        }

        String turno = String.format("A%03d", numeroTurno);

        numeroTurno++;

        Cliente cliente = new Cliente(nombre, turno);

        colaClientes.offer(cliente);

        lblMensaje.setText(
                "Cliente registrado. Turno asignado: " + turno
        );

        tfNombre.clear();
        tfNombre.requestFocus();

        actualizarCola();
    }

    private void atenderCliente() {

        if (colaClientes.isEmpty()) {
            lblMensaje.setText("No hay clientes pendientes.");
            return;
        }

        Cliente cliente = colaClientes.poll();

        historial.add(cliente);

        lblActual.setText(
                "Cliente actual: "
                        + cliente.getTurno()
                        + " - "
                        + cliente.getNombre()
        );

        lblMensaje.setText(
                "Atendiendo al cliente: " + cliente.getNombre()
        );

        actualizarCola();
        mostrarHistorial();
    }

    private void mostrarSiguiente() {

        if (colaClientes.isEmpty()) {

            lblMensaje.setText("No hay clientes pendientes.");
            return;
        }

        Cliente siguiente = colaClientes.peek();

        lblMensaje.setText(
                "Siguiente: "
                        + siguiente.getTurno()
                        + " - "
                        + siguiente.getNombre()
        );
    }

    private void mostrarPendientes() {

        lblPendientes.setText(
                "Clientes pendientes: " + colaClientes.size()
        );

        lblMensaje.setText("");
    }

    private void actualizarCola() {

        String texto = "";

        for (Cliente cliente : colaClientes) {

            texto += cliente.getTurno()
                    + "-"
                    + cliente.getNombre()
                    + " ";
        }

        lblCola.setText("Cola: [" + texto + "]");

        lblPendientes.setText(
                "Clientes pendientes: " + colaClientes.size()
        );
    }

    private void mostrarHistorial() {

        String texto = "";

        for (Cliente cliente : historial) {

            texto += "\n"
                    + cliente.getTurno()
                    + " - "
                    + cliente.getNombre();
        }

        lblHistorial.setText(
                "Historial de atendidos:" + texto
        );
    }

    private void reiniciar() {

        colaClientes.clear();
        historial.clear();

        numeroTurno = 1;

        tfNombre.clear();

        lblCola.setText("Cola: []");
        lblActual.setText("Cliente actual: Ninguno");
        lblPendientes.setText("Clientes pendientes: 0");
        lblHistorial.setText("Historial:");
        lblMensaje.setText("Sistema reiniciado.");

        tfNombre.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

    private static class Cliente {

        private String nombre;
        private String turno;

        public Cliente(String nombre, String turno) {
            this.nombre = nombre;
            this.turno = turno;
        }

        public String getNombre() {
            return nombre;
        }

        public String getTurno() {
            return turno;
        }
    }
}