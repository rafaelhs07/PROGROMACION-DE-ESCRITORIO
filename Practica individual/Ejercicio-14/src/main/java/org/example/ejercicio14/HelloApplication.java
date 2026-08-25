package org.example.ejercicio14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class HelloApplication extends Application {

    private Queue<String> colaTurnos = new LinkedList<>();

    private int numeroTurno = 1;

    private Label lblTurnoActual;
    private Label lblSiguiente;
    private Label lblPendientes;
    private Label lblCola;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Sistema de turnos del banco");

        Button btnGenerar = new Button("Generar turno");
        Button btnLlamar = new Button("Llamar turno");
        Button btnSiguiente = new Button("Mostrar siguiente");
        Button btnPendientes = new Button("Mostrar pendientes");
        Button btnReiniciar = new Button("Reiniciar");

        lblTurnoActual = new Label("Turno actual: Ninguno");
        lblSiguiente = new Label("Siguiente turno: Ninguno");
        lblPendientes = new Label("Clientes pendientes: 0");
        lblCola = new Label("Cola: []");
        lblMensaje = new Label("");

        btnGenerar.setOnAction(event -> generarTurno());
        btnLlamar.setOnAction(event -> llamarTurno());
        btnSiguiente.setOnAction(event -> mostrarSiguiente());
        btnPendientes.setOnAction(event -> mostrarPendientes());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones1 = new HBox(
                btnGenerar,
                btnLlamar,
                btnSiguiente
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnPendientes,
                btnReiniciar
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                botones1,
                botones2,
                lblTurnoActual,
                lblSiguiente,
                lblPendientes,
                lblCola,
                lblMensaje
        );

        Scene scene = new Scene(root, 650, 400);

        stage.setTitle("Ejercicio 14");
        stage.setScene(scene);
        stage.show();
    }

    private void generarTurno() {

        String turno = String.format(
                "A%03d",
                numeroTurno
        );

        colaTurnos.offer(turno);

        numeroTurno++;

        lblMensaje.setText(
                "Turno generado: " + turno
        );

        actualizarDatos();
    }

    private void llamarTurno() {

        if (colaTurnos.isEmpty()) {

            lblMensaje.setText(
                    "No hay turnos pendientes."
            );

            return;
        }

        String turno = colaTurnos.poll();

        lblTurnoActual.setText(
                "Turno actual: " + turno
        );

        lblMensaje.setText(
                "Llamando al turno " + turno
        );

        actualizarDatos();
    }

    private void mostrarSiguiente() {

        if (colaTurnos.isEmpty()) {

            lblSiguiente.setText(
                    "Siguiente turno: Ninguno"
            );

            lblMensaje.setText(
                    "No hay turnos pendientes."
            );

            return;
        }

        lblSiguiente.setText(
                "Siguiente turno: " + colaTurnos.peek()
        );

        lblMensaje.setText("");
    }

    private void mostrarPendientes() {

        lblPendientes.setText(
                "Clientes pendientes: " + colaTurnos.size()
        );

        lblMensaje.setText("");
    }

    private void actualizarDatos() {

        lblCola.setText(
                "Cola: " + colaTurnos
        );

        lblPendientes.setText(
                "Clientes pendientes: " + colaTurnos.size()
        );

        if (colaTurnos.isEmpty()) {

            lblSiguiente.setText(
                    "Siguiente turno: Ninguno"
            );

        } else {

            lblSiguiente.setText(
                    "Siguiente turno: " + colaTurnos.peek()
            );
        }
    }

    private void reiniciar() {

        colaTurnos.clear();

        numeroTurno = 1;

        lblTurnoActual.setText(
                "Turno actual: Ninguno"
        );

        lblSiguiente.setText(
                "Siguiente turno: Ninguno"
        );

        lblPendientes.setText(
                "Clientes pendientes: 0"
        );

        lblCola.setText(
                "Cola: []"
        );

        lblMensaje.setText(
                "Sistema reiniciado."
        );
    }

    public static void main(String[] args) {
        launch();
    }
}