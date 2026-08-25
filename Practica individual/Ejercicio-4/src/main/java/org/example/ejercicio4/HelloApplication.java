package org.example.ejercicio4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private double[] notas = new double[10];
    private int contador = 0;

    private TextField tfNota;
    private Label lblContador;
    private Label lblNotas;
    private Label lblResultado;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Estadísticas de notas");
        Label lblIngreso = new Label("Ingrese 10 notas:");

        tfNota = new TextField();
        tfNota.setPromptText("Ingrese una nota");

        Button btnAgregar = new Button("Agregar");
        Button btnCalcular = new Button("Calcular estadísticas");
        Button btnReiniciar = new Button("Reiniciar");

        lblContador = new Label("Notas ingresadas: 0 de 10");
        lblNotas = new Label("Notas: ");
        lblResultado = new Label("Resultados:");
        lblMensaje = new Label("");

        btnAgregar.setOnAction(event -> agregarNota());
        btnCalcular.setOnAction(event -> calcularEstadisticas());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones = new HBox(btnAgregar, btnCalcular, btnReiniciar);
        botones.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblIngreso,
                tfNota,
                lblContador,
                botones,
                lblNotas,
                lblResultado,
                lblMensaje
        );

        Scene scene = new Scene(root, 650, 500);

        stage.setTitle("Ejercicio 4");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarNota() {

        if (tfNota.getText().isEmpty()) {
            lblMensaje.setText("Ingrese una nota.");
            return;
        }

        if (contador >= 10) {
            lblMensaje.setText("Ya ingresó las 10 notas.");
            return;
        }

        try {

            double nota = Double.parseDouble(tfNota.getText());

            if (nota < 0 || nota > 100) {
                lblMensaje.setText("La nota debe estar entre 0 y 100.");
                return;
            }

            notas[contador] = nota;
            contador++;

            lblContador.setText(
                    "Notas ingresadas: " + contador + " de 10"
            );

            lblNotas.setText(
                    "Notas: " + mostrarNotas()
            );

            tfNota.clear();
            tfNota.requestFocus();
            lblMensaje.setText("");

        } catch (NumberFormatException e) {

            lblMensaje.setText("Ingrese una nota válida.");
        }
    }

    private void calcularEstadisticas() {

        if (contador < 10) {
            lblMensaje.setText("Debe ingresar las 10 notas.");
            return;
        }

        double promedio = calcularPromedio();
        double mayor = encontrarMayor();
        double menor = encontrarMenor();
        int aprobados = contarAprobados();
        int reprobados = contarReprobados();

        lblResultado.setText(
                "Resultados:\n"
                        + "Promedio general: " + promedio
                        + "\nNota mayor: " + mayor
                        + "\nNota menor: " + menor
                        + "\nAprobados: " + aprobados
                        + "\nReprobados: " + reprobados
        );

        lblMensaje.setText("");
    }

    private double calcularPromedio() {

        double suma = 0;

        for (double nota : notas) {
            suma += nota;
        }

        return suma / notas.length;
    }

    private double encontrarMayor() {

        double mayor = notas[0];

        for (int i = 1; i < notas.length; i++) {

            if (notas[i] > mayor) {
                mayor = notas[i];
            }
        }

        return mayor;
    }

    private double encontrarMenor() {

        double menor = notas[0];

        for (int i = 1; i < notas.length; i++) {

            if (notas[i] < menor) {
                menor = notas[i];
            }
        }

        return menor;
    }

    private int contarAprobados() {

        int aprobados = 0;

        for (double nota : notas) {

            if (nota >= 60) {
                aprobados++;
            }
        }

        return aprobados;
    }

    private int contarReprobados() {

        int reprobados = 0;

        for (double nota : notas) {

            if (nota < 60) {
                reprobados++;
            }
        }

        return reprobados;
    }

    private String mostrarNotas() {

        String texto = "";

        for (int i = 0; i < contador; i++) {

            texto += notas[i];

            if (i < contador - 1) {
                texto += ", ";
            }
        }

        return "[" + texto + "]";
    }

    private void reiniciar() {

        notas = new double[10];
        contador = 0;

        tfNota.clear();

        lblContador.setText("Notas ingresadas: 0 de 10");
        lblNotas.setText("Notas: ");
        lblResultado.setText("Resultados:");
        lblMensaje.setText("");

        tfNota.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}