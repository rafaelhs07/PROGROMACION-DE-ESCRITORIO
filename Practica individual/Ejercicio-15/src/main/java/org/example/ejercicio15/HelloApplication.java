package org.example.ejercicio15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class HelloApplication extends Application {

    private Queue<String> colaImpresion = new LinkedList<>();

    private TextField tfDocumento;

    private Label lblCola;
    private Label lblSiguiente;
    private Label lblPendientes;
    private Label lblUltimoImpreso;
    private Label lblMensaje;

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);

        Label lblTitulo = new Label("Cola de impresión");

        Label lblDocumento = new Label("Nombre del documento:");

        tfDocumento = new TextField();
        tfDocumento.setPromptText("Ejemplo: Tarea.pdf");

        Button btnAgregar = new Button("Agregar documento");
        Button btnImprimir = new Button("Imprimir documento");
        Button btnSiguiente = new Button("Mostrar siguiente");
        Button btnPendientes = new Button("Mostrar pendientes");
        Button btnLimpiar = new Button("Limpiar cola");

        lblCola = new Label("Cola: []");
        lblSiguiente = new Label("Siguiente documento: Ninguno");
        lblPendientes = new Label("Documentos pendientes: 0");
        lblUltimoImpreso = new Label("Último impreso: Ninguno");
        lblMensaje = new Label("");

        btnAgregar.setOnAction(event -> agregarDocumento());
        btnImprimir.setOnAction(event -> imprimirDocumento());
        btnSiguiente.setOnAction(event -> mostrarSiguiente());
        btnPendientes.setOnAction(event -> mostrarPendientes());
        btnLimpiar.setOnAction(event -> limpiarCola());

        HBox botones1 = new HBox(
                btnAgregar,
                btnImprimir
        );

        botones1.setSpacing(10);

        HBox botones2 = new HBox(
                btnSiguiente,
                btnPendientes,
                btnLimpiar
        );

        botones2.setSpacing(10);

        root.getChildren().addAll(
                lblTitulo,
                lblDocumento,
                tfDocumento,
                botones1,
                botones2,
                lblCola,
                lblSiguiente,
                lblPendientes,
                lblUltimoImpreso,
                lblMensaje
        );

        Scene scene = new Scene(root, 650, 450);

        stage.setTitle("Ejercicio 15");
        stage.setScene(scene);
        stage.show();
    }

    private void agregarDocumento() {

        String documento = tfDocumento.getText().trim();

        if (documento.isEmpty()) {
            lblMensaje.setText("Ingrese el nombre del documento.");
            return;
        }

        colaImpresion.offer(documento);

        lblMensaje.setText(
                "Documento agregado: " + documento
        );

        tfDocumento.clear();
        tfDocumento.requestFocus();

        actualizarDatos();
    }

    private void imprimirDocumento() {

        if (colaImpresion.isEmpty()) {
            lblMensaje.setText("No hay documentos para imprimir.");
            return;
        }

        String documento = colaImpresion.poll();

        lblUltimoImpreso.setText(
                "Último impreso: " + documento
        );

        lblMensaje.setText(
                "Documento impreso correctamente."
        );

        actualizarDatos();
    }

    private void mostrarSiguiente() {

        if (colaImpresion.isEmpty()) {

            lblSiguiente.setText(
                    "Siguiente documento: Ninguno"
            );

            lblMensaje.setText(
                    "No hay documentos pendientes."
            );

            return;
        }

        lblSiguiente.setText(
                "Siguiente documento: " + colaImpresion.peek()
        );

        lblMensaje.setText("");
    }

    private void mostrarPendientes() {

        lblPendientes.setText(
                "Documentos pendientes: " + colaImpresion.size()
        );

        lblMensaje.setText("");
    }

    private void actualizarDatos() {

        lblCola.setText(
                "Cola: " + colaImpresion
        );

        lblPendientes.setText(
                "Documentos pendientes: " + colaImpresion.size()
        );

        if (colaImpresion.isEmpty()) {

            lblSiguiente.setText(
                    "Siguiente documento: Ninguno"
            );

        } else {

            lblSiguiente.setText(
                    "Siguiente documento: " + colaImpresion.peek()
            );
        }
    }

    private void limpiarCola() {

        colaImpresion.clear();

        tfDocumento.clear();

        lblCola.setText("Cola: []");
        lblSiguiente.setText("Siguiente documento: Ninguno");
        lblPendientes.setText("Documentos pendientes: 0");
        lblUltimoImpreso.setText("Último impreso: Ninguno");
        lblMensaje.setText("Cola limpiada.");

        tfDocumento.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}