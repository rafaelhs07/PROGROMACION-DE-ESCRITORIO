package org.example.ejercicio10;

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

    private Stack<Operacion> historial = new Stack<>();

    private double resultado = 0;

    private TextField tfNumero;
    private Label lblResultado;
    private Label lblOperacionActual;
    private Label lblHistorial;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Sistema Deshacer");

        Label lblNumero = new Label("Ingrese un número:");

        tfNumero = new TextField();
        tfNumero.setPromptText("Número");

        Button btnSumar = new Button("Sumar");
        Button btnRestar = new Button("Restar");
        Button btnDeshacer = new Button("Deshacer");
        Button btnActual = new Button("Operación actual");
        Button btnReiniciar = new Button("Reiniciar");

        lblResultado = new Label("Resultado: 0");
        lblOperacionActual = new Label("Operación actual: Ninguna");
        lblHistorial = new Label("Historial:");
        lblMensaje = new Label("");

        btnSumar.setOnAction(event -> realizarOperacion("SUMA"));
        btnRestar.setOnAction(event -> realizarOperacion("RESTA"));
        btnDeshacer.setOnAction(event -> deshacer());
        btnActual.setOnAction(event -> mostrarOperacionActual());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones1 = new HBox(
                btnSumar,
                btnRestar,
                btnDeshacer
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnActual,
                btnReiniciar
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblNumero,
                tfNumero,
                botones1,
                botones2,
                lblResultado,
                lblOperacionActual,
                lblHistorial,
                lblMensaje
        );

        Scene scene = new Scene(root, 650, 500);

        stage.setTitle("Ejercicio 10");
        stage.setScene(scene);
        stage.show();
    }

    private void realizarOperacion(String tipo) {

        if (tfNumero.getText().isEmpty()) {
            lblMensaje.setText("Ingrese un número.");
            return;
        }

        try {

            double numero = Double.parseDouble(tfNumero.getText());

            if (tipo.equals("SUMA")) {

                resultado += numero;

            } else if (tipo.equals("RESTA")) {

                resultado -= numero;
            }

            Operacion operacion = new Operacion(tipo, numero);

            historial.push(operacion);

            lblResultado.setText("Resultado: " + resultado);

            mostrarOperacionActual();
            mostrarHistorial();

            tfNumero.clear();
            tfNumero.requestFocus();

            lblMensaje.setText("Operación realizada.");

        } catch (NumberFormatException e) {

            lblMensaje.setText("Ingrese un número válido.");
        }
    }

    private void deshacer() {

        if (historial.isEmpty()) {

            lblMensaje.setText("No hay operaciones para deshacer.");
            return;
        }

        Operacion ultima = historial.pop();

        if (ultima.getTipo().equals("SUMA")) {

            resultado -= ultima.getValor();

        } else if (ultima.getTipo().equals("RESTA")) {

            resultado += ultima.getValor();
        }

        lblResultado.setText("Resultado: " + resultado);

        mostrarOperacionActual();
        mostrarHistorial();

        lblMensaje.setText("Última operación deshecha.");
    }

    private void mostrarOperacionActual() {

        if (historial.isEmpty()) {

            lblOperacionActual.setText(
                    "Operación actual: Ninguna"
            );

            return;
        }

        Operacion actual = historial.peek();

        String simbolo;

        if (actual.getTipo().equals("SUMA")) {
            simbolo = "+";
        } else {
            simbolo = "-";
        }

        lblOperacionActual.setText(
                "Operación actual: "
                        + simbolo
                        + " "
                        + actual.getValor()
        );
    }

    private void mostrarHistorial() {

        String texto = "";

        for (Operacion operacion : historial) {

            String simbolo;

            if (operacion.getTipo().equals("SUMA")) {
                simbolo = "+";
            } else {
                simbolo = "-";
            }

            texto += "\n"
                    + simbolo
                    + " "
                    + operacion.getValor();
        }

        lblHistorial.setText("Historial:" + texto);
    }

    private void reiniciar() {

        historial.clear();

        resultado = 0;

        tfNumero.clear();

        lblResultado.setText("Resultado: 0");
        lblOperacionActual.setText("Operación actual: Ninguna");
        lblHistorial.setText("Historial:");
        lblMensaje.setText("");

        tfNumero.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

    private static class Operacion {

        private String tipo;
        private double valor;

        public Operacion(String tipo, double valor) {
            this.tipo = tipo;
            this.valor = valor;
        }

        public String getTipo() {
            return tipo;
        }

        public double getValor() {
            return valor;
        }
    }
}