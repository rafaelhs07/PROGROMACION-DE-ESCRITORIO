package org.example.ejercicio6;

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

    private ArrayList<Contacto> contactos = new ArrayList<>();

    private TextField tfNombre;
    private TextField tfTelefono;
    private TextField tfCorreo;

    private Label lblLista;
    private Label lblCantidad;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(12);

        Label lblTitulo = new Label("Agenda de contactos");

        Label lblNombre = new Label("Nombre:");
        tfNombre = new TextField();
        tfNombre.setPromptText("Ingrese el nombre");

        Label lblTelefono = new Label("Teléfono:");
        tfTelefono = new TextField();
        tfTelefono.setPromptText("Ingrese el teléfono");

        Label lblCorreo = new Label("Correo:");
        tfCorreo = new TextField();
        tfCorreo.setPromptText("Ingrese el correo");

        Button btnAgregar = new Button("Agregar");
        Button btnBuscar = new Button("Buscar");
        Button btnEliminar = new Button("Eliminar");
        Button btnLimpiar = new Button("Limpiar");

        lblLista = new Label("Contactos:");
        lblCantidad = new Label("Cantidad: 0");
        lblMensaje = new Label("");

        btnAgregar.setOnAction(event -> agregarContacto());
        btnBuscar.setOnAction(event -> buscarContacto());
        btnEliminar.setOnAction(event -> eliminarContacto());
        btnLimpiar.setOnAction(event -> limpiarCampos());

        HBox botones = new HBox(
                btnAgregar,
                btnBuscar,
                btnEliminar,
                btnLimpiar
        );

        botones.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblNombre,
                tfNombre,
                lblTelefono,
                tfTelefono,
                lblCorreo,
                tfCorreo,
                botones,
                lblCantidad,
                lblLista,
                lblMensaje
        );

        Scene scene = new Scene(root, 650, 500);

        stage.setTitle("Ejercicio 6");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarContacto() {

        String nombre = tfNombre.getText().trim();
        String telefono = tfTelefono.getText().trim();
        String correo = tfCorreo.getText().trim();

        if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            lblMensaje.setText("Complete todos los campos.");
            return;
        }

        if (!telefono.matches("\\d+")) {
            lblMensaje.setText("El teléfono solo debe contener números.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            lblMensaje.setText("Ingrese un correo válido.");
            return;
        }

        contactos.add(new Contacto(nombre, telefono, correo));

        actualizarLista();
        limpiarCampos();

        lblMensaje.setText("Contacto agregado correctamente.");
    }

    private void buscarContacto() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese el nombre que desea buscar.");
            return;
        }

        Contacto contacto = encontrarContacto(nombre);

        if (contacto != null) {

            tfTelefono.setText(contacto.getTelefono());
            tfCorreo.setText(contacto.getCorreo());

            lblMensaje.setText("Contacto encontrado.");

        } else {

            lblMensaje.setText("El contacto no existe.");
        }
    }

    private void eliminarContacto() {

        String nombre = tfNombre.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese el nombre que desea eliminar.");
            return;
        }

        Contacto contacto = encontrarContacto(nombre);

        if (contacto != null) {

            contactos.remove(contacto);

            actualizarLista();
            limpiarCampos();

            lblMensaje.setText("Contacto eliminado correctamente.");

        } else {

            lblMensaje.setText("El contacto no existe.");
        }
    }

    private Contacto encontrarContacto(String nombre) {

        for (Contacto contacto : contactos) {

            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                return contacto;
            }
        }

        return null;
    }

    private void actualizarLista() {

        String texto = "";

        for (Contacto contacto : contactos) {

            texto += "\n"
                    + contacto.getNombre()
                    + " | "
                    + contacto.getTelefono()
                    + " | "
                    + contacto.getCorreo();
        }

        lblLista.setText("Contactos:" + texto);

        lblCantidad.setText(
                "Cantidad: " + contactos.size()
        );
    }

    private void limpiarCampos() {

        tfNombre.clear();
        tfTelefono.clear();
        tfCorreo.clear();

        tfNombre.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

    private static class Contacto {

        private String nombre;
        private String telefono;
        private String correo;

        public Contacto(String nombre, String telefono, String correo) {
            this.nombre = nombre;
            this.telefono = telefono;
            this.correo = correo;
        }

        public String getNombre() {
            return nombre;
        }

        public String getTelefono() {
            return telefono;
        }

        public String getCorreo() {
            return correo;
        }
    }
}