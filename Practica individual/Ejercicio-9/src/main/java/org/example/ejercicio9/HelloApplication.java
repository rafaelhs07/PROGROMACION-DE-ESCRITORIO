package org.example.ejercicio9;

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

    private Stack<Integer> pila = new Stack<>();

    private TextField tfNumero;

    private Label lblPila;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Pila de números");

        Label lblNumero = new Label("Ingrese un número:");
        tfNumero = new TextField();
        tfNumero.setPromptText("Número");

        Button btnPush = new Button("Push");
        Button btnPop = new Button("Pop");
        Button btnPeek = new Button("Peek");
        Button btnMostrar = new Button("Mostrar pila");
        Button btnVacia = new Button("¿Está vacía?");
        Button btnLimpiar = new Button("Limpiar");

        lblPila = new Label("Pila: []");
        lblMensaje = new Label("");

        btnPush.setOnAction(event -> agregarNumero());
        btnPop.setOnAction(event -> eliminarNumero());
        btnPeek.setOnAction(event -> verUltimo());
        btnMostrar.setOnAction(event -> mostrarPila());
        btnVacia.setOnAction(event -> verificarVacia());
        btnLimpiar.setOnAction(event -> limpiarPila());

        HBox botones1 = new HBox(
                btnPush,
                btnPop,
                btnPeek
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnMostrar,
                btnVacia,
                btnLimpiar
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblNumero,
                tfNumero,
                botones1,
                botones2,
                lblPila,
                lblMensaje
        );

        Scene scene = new Scene(root, 650, 400);

        stage.setTitle("Ejercicio 9");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarNumero() {

        if (tfNumero.getText().isEmpty()) {
            lblMensaje.setText("Ingrese un número.");
            return;
        }

        try {

            int numero = Integer.parseInt(tfNumero.getText());

            pila.push(numero);

            lblMensaje.setText("Número agregado a la pila.");

            tfNumero.clear();
            tfNumero.requestFocus();

            mostrarPila();

        } catch (NumberFormatException e) {

            lblMensaje.setText("Ingrese un número entero válido.");
        }
    }

    private void eliminarNumero() {

        if (pila.isEmpty()) {
            lblMensaje.setText("La pila está vacía.");
            return;
        }

        int eliminado = pila.pop();

        lblMensaje.setText(
                "Número eliminado: " + eliminado
        );

        mostrarPila();
    }

    private void verUltimo() {

        if (pila.isEmpty()) {
            lblMensaje.setText("La pila está vacía.");
            return;
        }

        int ultimo = pila.peek();

        lblMensaje.setText(
                "Último número: " + ultimo
        );
    }

    private void mostrarPila() {

        lblPila.setText(
                "Pila: " + pila
        );
    }

    private void verificarVacia() {

        if (pila.isEmpty()) {

            lblMensaje.setText("La pila está vacía.");

        } else {

            lblMensaje.setText(
                    "La pila tiene " + pila.size() + " elemento(s)."
            );
        }
    }

    private void limpiarPila() {

        pila.clear();

        lblPila.setText("Pila: []");
        lblMensaje.setText("Pila limpiada.");
        tfNumero.clear();
    }

    public static void main(String[] args) {
        launch();
    }
}