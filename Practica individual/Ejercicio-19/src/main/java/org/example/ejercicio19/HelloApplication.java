package org.example.ejercicio19;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class HelloApplication extends Application {

    private Stack<String> pilaDeshacer = new Stack<>();
    private Stack<String> pilaRehacer = new Stack<>();

    private TextField tfTexto;

    private Label lblEstado;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Editor con Deshacer y Rehacer");

        Label lblTexto = new Label("Escriba un texto:");

        tfTexto = new TextField();
        tfTexto.setPromptText("Escriba aquí");

        Button btnGuardar = new Button("Guardar cambio");
        Button btnDeshacer = new Button("Deshacer");
        Button btnRehacer = new Button("Rehacer");
        Button btnMostrar = new Button("Mostrar estado");
        Button btnReiniciar = new Button("Reiniciar");

        lblEstado = new Label("Estado actual: Vacío");
        lblMensaje = new Label("");

        pilaDeshacer.push("");

        btnGuardar.setOnAction(event -> guardarCambio());
        btnDeshacer.setOnAction(event -> deshacer());
        btnRehacer.setOnAction(event -> rehacer());
        btnMostrar.setOnAction(event -> mostrarEstado());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones1 = new HBox(
                btnGuardar,
                btnDeshacer,
                btnRehacer
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnMostrar,
                btnReiniciar
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblTexto,
                tfTexto,
                botones1,
                botones2,
                lblEstado,
                lblMensaje
        );

        Scene scene = new Scene(root, 700, 400);

        stage.setTitle("Ejercicio 19");
        stage.setScene(scene);
        stage.show();
    }

    private void guardarCambio() {

        String texto = tfTexto.getText();

        if (texto.equals(pilaDeshacer.peek())) {
            lblMensaje.setText("No hay cambios nuevos para guardar.");
            return;
        }

        pilaDeshacer.push(texto);

        pilaRehacer.clear();

        mostrarEstado();

        lblMensaje.setText("Cambio guardado.");
    }

    private void deshacer() {

        if (pilaDeshacer.size() <= 1) {
            lblMensaje.setText("No hay cambios para deshacer.");
            return;
        }

        String actual = pilaDeshacer.pop();

        pilaRehacer.push(actual);

        String anterior = pilaDeshacer.peek();

        tfTexto.setText(anterior);

        mostrarEstado();

        lblMensaje.setText("Cambio deshecho.");
    }

    private void rehacer() {

        if (pilaRehacer.isEmpty()) {
            lblMensaje.setText("No hay cambios para rehacer.");
            return;
        }

        String texto = pilaRehacer.pop();

        pilaDeshacer.push(texto);

        tfTexto.setText(texto);

        mostrarEstado();

        lblMensaje.setText("Cambio rehecho.");
    }

    private void mostrarEstado() {

        String texto = tfTexto.getText();

        if (texto.isEmpty()) {

            lblEstado.setText("Estado actual: Vacío");

        } else {

            lblEstado.setText(
                    "Estado actual: " + texto
            );
        }
    }

    private void reiniciar() {

        pilaDeshacer.clear();
        pilaRehacer.clear();

        pilaDeshacer.push("");

        tfTexto.clear();

        lblEstado.setText("Estado actual: Vacío");
        lblMensaje.setText("Editor reiniciado.");

        tfTexto.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}