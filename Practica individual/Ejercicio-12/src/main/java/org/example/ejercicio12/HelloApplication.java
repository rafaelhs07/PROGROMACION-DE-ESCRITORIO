package org.example.ejercicio12;

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

    private TextField tfTexto;
    private Label lblResultado;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Invertir texto");

        Label lblTexto = new Label("Ingrese una palabra o frase:");

        tfTexto = new TextField();
        tfTexto.setPromptText("Ingrese el texto");

        Button btnInvertir = new Button("Invertir");
        Button btnLimpiar = new Button("Limpiar");

        lblResultado = new Label("Resultado: ");
        lblMensaje = new Label("");

        btnInvertir.setOnAction(event -> invertirTexto());
        btnLimpiar.setOnAction(event -> limpiar());

        HBox botones = new HBox(
                btnInvertir,
                btnLimpiar
        );

        botones.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblTexto,
                tfTexto,
                botones,
                lblResultado,
                lblMensaje
        );

        Scene scene = new Scene(root, 600, 350);

        stage.setTitle("Ejercicio 12");
        stage.setScene(scene);
        stage.show();
    }

    private void invertirTexto() {

        String texto = tfTexto.getText();

        if (texto.trim().isEmpty()) {
            lblMensaje.setText("Ingrese una palabra o frase.");
            return;
        }

        String textoInvertido = invertirConPila(texto);

        lblResultado.setText(
                "Resultado: " + textoInvertido
        );

        lblMensaje.setText("");
    }

    private String invertirConPila(String texto) {

        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < texto.length(); i++) {
            pila.push(texto.charAt(i));
        }

        String resultado = "";

        while (!pila.isEmpty()) {
            resultado += pila.pop();
        }

        return resultado;
    }

    private void limpiar() {

        tfTexto.clear();
        lblResultado.setText("Resultado: ");
        lblMensaje.setText("");

        tfTexto.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}