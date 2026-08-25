package org.example.ejercicio11;

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

    private TextField tfExpresion;
    private Label lblResultado;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Verificador de paréntesis");

        Label lblExpresion = new Label("Ingrese una expresión:");

        tfExpresion = new TextField();
        tfExpresion.setPromptText("Ejemplo: ((10+5)*2)");

        Button btnVerificar = new Button("Verificar");
        Button btnLimpiar = new Button("Limpiar");

        lblResultado = new Label("Resultado:");
        lblMensaje = new Label("");

        btnVerificar.setOnAction(event -> verificarExpresion());
        btnLimpiar.setOnAction(event -> limpiar());

        HBox botones = new HBox(
                btnVerificar,
                btnLimpiar
        );

        botones.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblExpresion,
                tfExpresion,
                botones,
                lblResultado,
                lblMensaje
        );

        Scene scene = new Scene(root, 600, 350);

        stage.setTitle("Ejercicio 11");
        stage.setScene(scene);
        stage.show();
    }

    private void verificarExpresion() {

        String expresion = tfExpresion.getText().trim();

        if (expresion.isEmpty()) {
            lblMensaje.setText("Ingrese una expresión.");
            return;
        }

        boolean balanceada = verificarParentesis(expresion);

        if (balanceada) {

            lblResultado.setText(
                    "Resultado: Los paréntesis están balanceados."
            );

        } else {

            lblResultado.setText(
                    "Resultado: Los paréntesis NO están balanceados."
            );
        }

        lblMensaje.setText("");
    }

    private boolean verificarParentesis(String expresion) {

        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {

            char caracter = expresion.charAt(i);

            if (caracter == '(') {

                pila.push(caracter);

            } else if (caracter == ')') {

                if (pila.isEmpty()) {
                    return false;
                }

                pila.pop();
            }
        }

        return pila.isEmpty();
    }

    private void limpiar() {

        tfExpresion.clear();
        lblResultado.setText("Resultado:");
        lblMensaje.setText("");

        tfExpresion.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}