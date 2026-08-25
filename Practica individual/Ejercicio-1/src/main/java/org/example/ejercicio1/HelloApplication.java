package org.example.ejercicio1;

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

    // ARREGLO PARA GUARDAR LOS 10 NUMEROS
    private int[] numeros = new int[10];

    // INDICA CUANTOS NUMEROS HEMOS INGRESADO
    private int contador = 0;

    // COMPONENTES
    private TextField tfNumero;
    private Label lblContador;
    private Label lblResultado;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();

        root.setPadding(new Insets(30));
        root.setSpacing(15);

        // TITULO
        Label lblTitulo = new Label("Analizador de números");

        // INSTRUCCION
        Label lblInstruccion =
                new Label("Ingrese 10 números:");

        // CAMPO DE TEXTO
        tfNumero = new TextField();
        tfNumero.setPromptText("Ingrese un número");

        // CONTADOR
        lblContador =
                new Label("Números ingresados: 0 de 10");

        // BOTONES
        Button btnAgregar =
                new Button("Agregar número");

        Button btnReiniciar =
                new Button("Reiniciar");

        // RESULTADOS
        lblResultado =
                new Label("Resultados:");

        // EVENTO BOTON AGREGAR
        btnAgregar.setOnAction(event -> {

            agregarNumero();

        });

        // EVENTO BOTON REINICIAR
        btnReiniciar.setOnAction(event -> {

            reiniciar();

        });

        // CONTENEDOR DE BOTONES
        HBox botones =
                new HBox(btnAgregar, btnReiniciar);

        botones.setSpacing(10);

        // AGREGAR ELEMENTOS
        root.getChildren().addAll(
                lblTitulo,
                lblInstruccion,
                tfNumero,
                lblContador,
                botones,
                lblResultado
        );

        // VENTANA
        Scene scene =
                new Scene(root, 600, 500);

        stage.setTitle("Ejercicio 1");
        stage.setScene(scene);
        stage.show();
    }

    // ==================================
    // METODO PARA AGREGAR NUMEROS
    // ==================================

    private void agregarNumero() {

        // VALIDAR QUE NO ESTE VACIO
        if (tfNumero.getText().isEmpty()) {

            lblResultado.setText(
                    "Error: debe ingresar un número."
            );

            return;
        }

        // VALIDAR QUE NO HAYA MAS DE 10
        if (contador >= 10) {

            lblResultado.setText(
                    "Ya se ingresaron los 10 números."
            );

            return;
        }

        try {

            // CONVERTIR TEXTO A NUMERO
            int numero =
                    Integer.parseInt(tfNumero.getText());

            // GUARDAR EN EL ARREGLO
            numeros[contador] = numero;

            contador++;

            // ACTUALIZAR CONTADOR
            lblContador.setText(
                    "Números ingresados: "
                            + contador
                            + " de 10"
            );

            // LIMPIAR TEXTFIELD
            tfNumero.clear();

            // VOLVER A COLOCAR EL CURSOR
            tfNumero.requestFocus();

            // SI YA SON 10, CALCULAR
            if (contador == 10) {

                mostrarResultados();

            }

        } catch (NumberFormatException e) {

            lblResultado.setText(
                    "Error: solamente puede ingresar números enteros."
            );

        }
    }

    // ==================================
    // MOSTRAR RESULTADOS
    // ==================================

    private void mostrarResultados() {

        int suma = calcularSuma();

        int mayor = encontrarMayor();

        int menor = encontrarMenor();

        double promedio = calcularPromedio();

        int pares = contarPares();

        int impares = contarImpares();

        lblResultado.setText(

                "RESULTADOS\n\n"
                        + "Suma: " + suma
                        + "\nMayor: " + mayor
                        + "\nMenor: " + menor
                        + "\nPromedio: " + promedio
                        + "\nPares: " + pares
                        + "\nImpares: " + impares

        );
    }

    // ==================================
    // CALCULAR SUMA
    // ==================================

    private int calcularSuma() {

        int suma = 0;

        for (int i = 0; i < numeros.length; i++) {

            suma = suma + numeros[i];

        }

        return suma;
    }

    // ==================================
    // ENCONTRAR NUMERO MAYOR
    // ==================================

    private int encontrarMayor() {

        int mayor = numeros[0];

        for (int i = 1; i < numeros.length; i++) {

            if (numeros[i] > mayor) {

                mayor = numeros[i];

            }
        }

        return mayor;
    }

    // ==================================
    // ENCONTRAR NUMERO MENOR
    // ==================================

    private int encontrarMenor() {

        int menor = numeros[0];

        for (int i = 1; i < numeros.length; i++) {

            if (numeros[i] < menor) {

                menor = numeros[i];

            }
        }

        return menor;
    }

    // ==================================
    // CALCULAR PROMEDIO
    // ==================================

    private double calcularPromedio() {

        int suma = calcularSuma();

        return (double) suma / numeros.length;
    }

    // ==================================
    // CONTAR NUMEROS PARES
    // ==================================

    private int contarPares() {

        int pares = 0;

        for (int numero : numeros) {

            if (numero % 2 == 0) {

                pares++;

            }
        }

        return pares;
    }

    // ==================================
    // CONTAR NUMEROS IMPARES
    // ==================================

    private int contarImpares() {

        int impares = 0;

        for (int numero : numeros) {

            if (numero % 2 != 0) {

                impares++;

            }
        }

        return impares;
    }

    // ==================================
    // REINICIAR PROGRAMA
    // ==================================

    private void reiniciar() {

        numeros = new int[10];

        contador = 0;

        tfNumero.clear();

        lblContador.setText(
                "Números ingresados: 0 de 10"
        );

        lblResultado.setText(
                "Resultados:"
        );

        tfNumero.requestFocus();
    }

    public static void main(String[] args) {

        launch();

    }
}