package org.example.ejercicio17;

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

    private ArrayList<Libro> libros = new ArrayList<>();

    private TextField tfTitulo;
    private TextField tfAutor;

    private Label lblLista;
    private Label lblDisponibles;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(12);

        Label lblTitulo = new Label("Sistema de biblioteca");

        Label lblLibro = new Label("Título del libro:");
        tfTitulo = new TextField();
        tfTitulo.setPromptText("Ingrese el título");

        Label lblAutor = new Label("Autor:");
        tfAutor = new TextField();
        tfAutor.setPromptText("Ingrese el autor");

        Button btnRegistrar = new Button("Registrar libro");
        Button btnBuscar = new Button("Buscar");
        Button btnPrestar = new Button("Prestar");
        Button btnDevolver = new Button("Devolver");
        Button btnDisponibles = new Button("Contar disponibles");
        Button btnLimpiar = new Button("Limpiar");

        lblLista = new Label("Libros:");
        lblDisponibles = new Label("Libros disponibles: 0");
        lblMensaje = new Label("");

        btnRegistrar.setOnAction(event -> registrarLibro());
        btnBuscar.setOnAction(event -> buscarLibro());
        btnPrestar.setOnAction(event -> prestarLibro());
        btnDevolver.setOnAction(event -> devolverLibro());
        btnDisponibles.setOnAction(event -> mostrarDisponibles());
        btnLimpiar.setOnAction(event -> limpiarCampos());

        HBox botones1 = new HBox(
                btnRegistrar,
                btnBuscar,
                btnPrestar
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnDevolver,
                btnDisponibles,
                btnLimpiar
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblLibro,
                tfTitulo,
                lblAutor,
                tfAutor,
                botones1,
                botones2,
                lblLista,
                lblDisponibles,
                lblMensaje
        );

        Scene scene = new Scene(root, 750, 550);

        stage.setTitle("Ejercicio 17");
        stage.setScene(scene);
        stage.show();
    }

    private void registrarLibro() {

        String titulo = tfTitulo.getText().trim();
        String autor = tfAutor.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty()) {
            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        if (buscarPorTitulo(titulo) != null) {
            lblMensaje.setText("El libro ya existe.");
            return;
        }

        Libro libro = new Libro(titulo, autor);

        libros.add(libro);

        actualizarLista();
        mostrarDisponibles();
        limpiarCampos();

        lblMensaje.setText("Libro registrado correctamente.");
    }

    private void buscarLibro() {

        String titulo = tfTitulo.getText().trim();

        if (titulo.isEmpty()) {
            lblMensaje.setText("Ingrese el título del libro.");
            return;
        }

        Libro libro = buscarPorTitulo(titulo);

        if (libro != null) {

            tfAutor.setText(libro.getAutor());

            if (libro.isDisponible()) {

                lblMensaje.setText("Libro encontrado y disponible.");

            } else {

                lblMensaje.setText("Libro encontrado, pero está prestado.");
            }

        } else {

            lblMensaje.setText("Libro no encontrado.");
        }
    }

    private void prestarLibro() {

        String titulo = tfTitulo.getText().trim();

        if (titulo.isEmpty()) {
            lblMensaje.setText("Ingrese el título del libro.");
            return;
        }

        Libro libro = buscarPorTitulo(titulo);

        if (libro == null) {
            lblMensaje.setText("Libro no encontrado.");
            return;
        }

        if (!libro.isDisponible()) {
            lblMensaje.setText("El libro ya está prestado.");
            return;
        }

        libro.setDisponible(false);

        actualizarLista();
        mostrarDisponibles();

        lblMensaje.setText("Libro prestado correctamente.");
    }

    private void devolverLibro() {

        String titulo = tfTitulo.getText().trim();

        if (titulo.isEmpty()) {
            lblMensaje.setText("Ingrese el título del libro.");
            return;
        }

        Libro libro = buscarPorTitulo(titulo);

        if (libro == null) {
            lblMensaje.setText("Libro no encontrado.");
            return;
        }

        if (libro.isDisponible()) {
            lblMensaje.setText("El libro ya estaba disponible.");
            return;
        }

        libro.setDisponible(true);

        actualizarLista();
        mostrarDisponibles();

        lblMensaje.setText("Libro devuelto correctamente.");
    }

    private Libro buscarPorTitulo(String titulo) {

        for (Libro libro : libros) {

            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }

        return null;
    }

    private void mostrarDisponibles() {

        int cantidad = 0;

        for (Libro libro : libros) {

            if (libro.isDisponible()) {
                cantidad++;
            }
        }

        lblDisponibles.setText(
                "Libros disponibles: " + cantidad
        );
    }

    private void actualizarLista() {

        String texto = "";

        for (Libro libro : libros) {

            String estado;

            if (libro.isDisponible()) {
                estado = "Disponible";
            } else {
                estado = "Prestado";
            }

            texto += "\n"
                    + libro.getTitulo()
                    + " | "
                    + libro.getAutor()
                    + " | "
                    + estado;
        }

        lblLista.setText("Libros:" + texto);
    }

    private void limpiarCampos() {

        tfTitulo.clear();
        tfAutor.clear();

        tfTitulo.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

    private static class Libro {

        private String titulo;
        private String autor;
        private boolean disponible;

        public Libro(String titulo, String autor) {
            this.titulo = titulo;
            this.autor = autor;
            this.disponible = true;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        public boolean isDisponible() {
            return disponible;
        }

        public void setDisponible(boolean disponible) {
            this.disponible = disponible;
        }
    }
}