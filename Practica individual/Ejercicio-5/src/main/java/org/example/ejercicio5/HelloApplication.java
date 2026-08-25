package org.example.ejercicio5;

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

    private ArrayList<String> estudiantes = new ArrayList<>();

    private TextField tfNombre;
    private Label lblLista;
    private Label lblCantidad;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Lista de estudiantes");
        Label lblNombre = new Label("Nombre del estudiante:");

        tfNombre = new TextField();
        tfNombre.setPromptText("Ingrese el nombre");

        Button btnAgregar = new Button("Agregar");
        Button btnBuscar = new Button("Buscar");
        Button btnEliminar = new Button("Eliminar");
        Button btnReiniciar = new Button("Reiniciar");

        lblLista = new Label("Estudiantes: ");
        lblCantidad = new Label("Cantidad: 0");
        lblMensaje = new Label("");

        btnAgregar.setOnAction(event -> agregarEstudiante());
        btnBuscar.setOnAction(event -> buscarEstudiante());
        btnEliminar.setOnAction(event -> eliminarEstudiante());
        btnReiniciar.setOnAction(event -> reiniciar());

        HBox botones = new HBox(
                btnAgregar,
                btnBuscar,
                btnEliminar,
                btnReiniciar
        );

        botones.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblNombre,
                tfNombre,
                botones,
                lblLista,
                lblCantidad,
                lblMensaje
        );

        Scene scene = new Scene(root, 650, 400);

        stage.setTitle("Ejercicio 5");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarEstudiante() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese el nombre del estudiante.");
            return;
        }

        estudiantes.add(nombre);

        actualizarLista();

        lblMensaje.setText("Estudiante agregado correctamente.");

        tfNombre.clear();
        tfNombre.requestFocus();
    }

    private void buscarEstudiante() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese un nombre para buscar.");
            return;
        }

        int posicion = buscarPosicion(nombre);

        if (posicion != -1) {

            lblMensaje.setText(
                    "El estudiante existe en la posición "
                            + (posicion + 1)
            );

        } else {

            lblMensaje.setText("El estudiante no existe.");
        }
    }

    private void eliminarEstudiante() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese un nombre para eliminar.");
            return;
        }

        int posicion = buscarPosicion(nombre);

        if (posicion != -1) {

            estudiantes.remove(posicion);

            actualizarLista();

            lblMensaje.setText("Estudiante eliminado correctamente.");

            tfNombre.clear();

        } else {

            lblMensaje.setText("El estudiante no existe.");
        }
    }

    private int buscarPosicion(String nombre) {

        for (int i = 0; i < estudiantes.size(); i++) {

            if (estudiantes.get(i).equalsIgnoreCase(nombre)) {
                return i;
            }
        }

        return -1;
    }

    private void actualizarLista() {

        String texto = "";

        for (int i = 0; i < estudiantes.size(); i++) {

            texto += estudiantes.get(i);

            if (i < estudiantes.size() - 1) {
                texto += ", ";
            }
        }

        lblLista.setText("Estudiantes: [" + texto + "]");

        lblCantidad.setText(
                "Cantidad: " + estudiantes.size()
        );
    }

    private void reiniciar() {

        estudiantes.clear();

        tfNombre.clear();

        lblLista.setText("Estudiantes: ");
        lblCantidad.setText("Cantidad: 0");
        lblMensaje.setText("");

        tfNombre.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}