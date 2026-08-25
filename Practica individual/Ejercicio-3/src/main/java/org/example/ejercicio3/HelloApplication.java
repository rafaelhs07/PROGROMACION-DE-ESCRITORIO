package org.example.ejercicio3;

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

    private int[] numeros = new int[10];
    private int contador = 0;

    private TextField tfNumero;
    private Label lblContador;
    private Label lblOriginal;
    private Label lblOrdenado;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Ordenamiento de números");
        Label lblIngreso = new Label("Ingrese 10 números:");

        tfNumero = new TextField();
        tfNumero.setPromptText("Ingrese un número");

        Button btnAgregar = new Button("Agregar");
        Button btnOrdenar = new Button("Ordenar");
        Button btnReiniciar = new Button("Reiniciar");

        lblContador = new Label("Números ingresados: 0 de 10");
        lblOriginal = new Label("Original: ");
        lblOrdenado = new Label("Ordenado: ");
        lblMensaje = new Label("");

        btnAgregar.setOnAction(event -> agregarNumero());
        btnOrdenar.setOnAction(event -> ordenarNumeros());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones = new HBox(btnAgregar, btnOrdenar, btnReiniciar);
        botones.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblIngreso,
                tfNumero,
                lblContador,
                botones,
                lblOriginal,
                lblOrdenado,
                lblMensaje
        );

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Ejercicio 3");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarNumero() {

        if (tfNumero.getText().isEmpty()) {
            lblMensaje.setText("Ingrese un número.");
            return;
        }

        if (contador >= 10) {
            lblMensaje.setText("Ya ingresó los 10 números.");
            return;
        }

        try {

            int numero = Integer.parseInt(tfNumero.getText());

            numeros[contador] = numero;
            contador++;

            lblContador.setText(
                    "Números ingresados: " + contador + " de 10"
            );

            lblOriginal.setText(
                    "Original: " + mostrarArreglo(numeros, contador)
            );

            tfNumero.clear();
            tfNumero.requestFocus();

            if (contador == 10) {
                lblMensaje.setText("Ya puede ordenar los números.");
            } else {
                lblMensaje.setText("");
            }

        } catch (NumberFormatException e) {

            lblMensaje.setText("Ingrese solamente números enteros.");
        }
    }

    private void ordenarNumeros() {

        if (contador < 10) {
            lblMensaje.setText("Primero debe ingresar los 10 números.");
            return;
        }

        int[] ordenados = copiarArreglo();

        bubbleSort(ordenados);

        lblOrdenado.setText(
                "Ordenado: " + mostrarArreglo(ordenados, ordenados.length)
        );

        lblMensaje.setText("Números ordenados correctamente.");
    }

    private int[] copiarArreglo() {

        int[] copia = new int[numeros.length];

        for (int i = 0; i < numeros.length; i++) {
            copia[i] = numeros[i];
        }

        return copia;
    }

    private void bubbleSort(int[] arreglo) {

        for (int i = 0; i < arreglo.length - 1; i++) {

            for (int j = 0; j < arreglo.length - 1 - i; j++) {

                if (arreglo[j] > arreglo[j + 1]) {

                    int auxiliar = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = auxiliar;
                }
            }
        }
    }

    private String mostrarArreglo(int[] arreglo, int cantidad) {

        String texto = "";

        for (int i = 0; i < cantidad; i++) {

            texto += arreglo[i];

            if (i < cantidad - 1) {
                texto += ", ";
            }
        }

        return "[" + texto + "]";
    }

    private void reiniciar() {

        numeros = new int[10];
        contador = 0;

        tfNumero.clear();

        lblContador.setText("Números ingresados: 0 de 10");
        lblOriginal.setText("Original: ");
        lblOrdenado.setText("Ordenado: ");
        lblMensaje.setText("");

        tfNumero.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}