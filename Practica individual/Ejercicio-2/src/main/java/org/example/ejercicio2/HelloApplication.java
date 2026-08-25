package org.example.ejercicio2;

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

    // Arreglo para guardar 10 números
    private int[] numeros = new int[10];

    // Cantidad de números ingresados
    private int contador = 0;

    // Componentes
    private TextField tfNumero;
    private TextField tfBuscar;

    private Label lblContador;
    private Label lblArreglo;
    private Label lblResultado;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);


        Label lblTitulo = new Label("Ejercicio 2 - Búsqueda en arreglo");


        Label lblIngreso = new Label("Ingrese 10 números:");

        tfNumero = new TextField();
        tfNumero.setPromptText("Ingrese un número");

        Button btnAgregar = new Button("Agregar número");

        lblContador = new Label("Números ingresados: 0 de 10");

        lblArreglo = new Label("Arreglo: ");


        Label lblBuscar = new Label("Número que desea buscar:");

        tfBuscar = new TextField();
        tfBuscar.setPromptText("Número a buscar");

        Button btnBuscar = new Button("Buscar");

        Button btnReiniciar = new Button("Reiniciar");


        lblResultado = new Label("Resultado:");


        btnAgregar.setOnAction(event -> agregarNumero());

        btnBuscar.setOnAction(event -> buscarNumero());

        btnReiniciar.setOnAction(event -> reiniciar());


        HBox botones = new HBox(btnAgregar, btnBuscar, btnReiniciar);
        botones.setSpacing(10);


        root.getChildren().addAll(
                lblTitulo,
                lblIngreso,
                tfNumero,
                lblContador,
                lblArreglo,
                lblBuscar,
                tfBuscar,
                botones,
                lblResultado
        );

        Scene scene = new Scene(root, 650, 500);

        stage.setTitle("Búsqueda en arreglo");
        stage.setScene(scene);
        stage.show();
    }



    private void agregarNumero() {

        // Valida campo vacío
        if (tfNumero.getText().isEmpty()) {

            lblResultado.setText(
                    "Error: debe ingresar un número."
            );

            return;
        }

        // Valida que no haya más de 10 números
        if (contador >= 10) {

            lblResultado.setText(
                    "Ya se ingresaron los 10 números."
            );

            return;
        }

        try {

            int numero = Integer.parseInt(
                    tfNumero.getText()
            );

            numeros[contador] = numero;

            contador++;

            lblContador.setText(
                    "Números ingresados: "
                            + contador
                            + " de 10"
            );

            mostrarArreglo();

            tfNumero.clear();
            tfNumero.requestFocus();

            if (contador == 10) {

                lblResultado.setText(
                        "Arreglo completo. Ahora puede buscar un número."
                );
            }

        } catch (NumberFormatException e) {

            lblResultado.setText(
                    "Error: debe ingresar un número entero."
            );
        }
    }



    private void buscarNumero() {

        // Validar que primero se hayan ingresado los 10 números
        if (contador < 10) {

            lblResultado.setText(
                    "Debe ingresar los 10 números antes de buscar."
            );

            return;
        }

        // Validar campo vacío
        if (tfBuscar.getText().isEmpty()) {

            lblResultado.setText(
                    "Error: ingrese el número que desea buscar."
            );

            return;
        }

        try {

            int numeroBuscado = Integer.parseInt(
                    tfBuscar.getText()
            );

            int cantidad = 0;

            String posiciones = "";

            // Recorrer el arreglo
            for (int i = 0; i < numeros.length; i++) {

                if (numeros[i] == numeroBuscado) {

                    cantidad++;

                    posiciones = posiciones + (i + 1) + " ";
                }
            }

            // Mostrar resultado
            if (cantidad > 0) {

                lblResultado.setText(
                        "El número " + numeroBuscado + " SÍ existe."
                                + "\nPosición(es): " + posiciones
                                + "\nCantidad de veces que aparece: " + cantidad
                );

            } else {

                lblResultado.setText(
                        "El número " + numeroBuscado
                                + " NO existe en el arreglo."
                );
            }

        } catch (NumberFormatException e) {

            lblResultado.setText(
                    "Error: debe ingresar un número entero para buscar."
            );
        }
    }



    private void mostrarArreglo() {

        String texto = "";

        for (int i = 0; i < contador; i++) {

            texto = texto + numeros[i];

            if (i < contador - 1) {

                texto = texto + ", ";
            }
        }

        lblArreglo.setText(
                "Arreglo: [" + texto + "]"
        );
    }



    private void reiniciar() {

        numeros = new int[10];

        contador = 0;

        tfNumero.clear();
        tfBuscar.clear();

        lblContador.setText(
                "Números ingresados: 0 de 10"
        );

        lblArreglo.setText(
                "Arreglo: "
        );

        lblResultado.setText(
                "Resultado:"
        );

        tfNumero.requestFocus();
    }

    public static void main(String[] args) {

        launch();

    }
}