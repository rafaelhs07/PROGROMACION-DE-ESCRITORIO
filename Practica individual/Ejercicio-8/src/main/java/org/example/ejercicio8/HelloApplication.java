package org.example.ejercicio8;

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

    private ArrayList<String> historial = new ArrayList<>();

    private TextField tfNumero1;
    private TextField tfNumero2;

    private Label lblResultado;
    private Label lblHistorial;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Calculadora con historial");

        Label lblNumero1 = new Label("Número 1:");
        tfNumero1 = new TextField();
        tfNumero1.setPromptText("Ingrese el primer número");

        Label lblNumero2 = new Label("Número 2:");
        tfNumero2 = new TextField();
        tfNumero2.setPromptText("Ingrese el segundo número");

        Button btnSumar = new Button("Sumar");
        Button btnRestar = new Button("Restar");
        Button btnMultiplicar = new Button("Multiplicar");
        Button btnDividir = new Button("Dividir");
        Button btnLimpiar = new Button("Limpiar");
        Button btnBorrarHistorial = new Button("Borrar historial");

        lblResultado = new Label("Resultado: ");
        lblHistorial = new Label("Historial:");
        lblMensaje = new Label("");

        btnSumar.setOnAction(event -> realizarOperacion("+"));
        btnRestar.setOnAction(event -> realizarOperacion("-"));
        btnMultiplicar.setOnAction(event -> realizarOperacion("*"));
        btnDividir.setOnAction(event -> realizarOperacion("/"));

        btnLimpiar.setOnAction(event -> limpiarCampos());
        btnBorrarHistorial.setOnAction(event -> borrarHistorial());

        HBox botones1 = new HBox(
                btnSumar,
                btnRestar,
                btnMultiplicar,
                btnDividir
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnLimpiar,
                btnBorrarHistorial
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblNumero1,
                tfNumero1,
                lblNumero2,
                tfNumero2,
                botones1,
                botones2,
                lblResultado,
                lblMensaje,
                lblHistorial
        );

        Scene scene = new Scene(root, 650, 550);

        stage.setTitle("Ejercicio 8");
        stage.setScene(scene);
        stage.show();
    }

    private void realizarOperacion(String operacion) {

        if (tfNumero1.getText().isEmpty()
                || tfNumero2.getText().isEmpty()) {

            lblMensaje.setText("Debe ingresar los dos números.");
            return;
        }

        try {

            double numero1 = Double.parseDouble(tfNumero1.getText());
            double numero2 = Double.parseDouble(tfNumero2.getText());

            double resultado = 0;

            switch (operacion) {

                case "+":
                    resultado = sumar(numero1, numero2);
                    break;

                case "-":
                    resultado = restar(numero1, numero2);
                    break;

                case "*":
                    resultado = multiplicar(numero1, numero2);
                    break;

                case "/":

                    if (numero2 == 0) {
                        lblMensaje.setText("No se puede dividir entre cero.");
                        return;
                    }

                    resultado = dividir(numero1, numero2);
                    break;
            }

            lblResultado.setText(
                    "Resultado: " + resultado
            );

            String registro =
                    numero1 + " "
                            + operacion + " "
                            + numero2 + " = "
                            + resultado;

            historial.add(registro);

            mostrarHistorial();

            lblMensaje.setText("");

        } catch (NumberFormatException e) {

            lblMensaje.setText("Ingrese números válidos.");
        }
    }

    private double sumar(double numero1, double numero2) {
        return numero1 + numero2;
    }

    private double restar(double numero1, double numero2) {
        return numero1 - numero2;
    }

    private double multiplicar(double numero1, double numero2) {
        return numero1 * numero2;
    }

    private double dividir(double numero1, double numero2) {
        return numero1 / numero2;
    }

    private void mostrarHistorial() {

        String texto = "";

        for (String operacion : historial) {
            texto += "\n" + operacion;
        }

        lblHistorial.setText(
                "Historial:" + texto
        );
    }

    private void limpiarCampos() {

        tfNumero1.clear();
        tfNumero2.clear();

        lblResultado.setText("Resultado: ");
        lblMensaje.setText("");

        tfNumero1.requestFocus();
    }

    private void borrarHistorial() {

        historial.clear();

        lblHistorial.setText("Historial:");
        lblMensaje.setText("Historial eliminado.");
    }

    public static void main(String[] args) {
        launch();
    }
}