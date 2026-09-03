package org.example.inventario;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class InventarioController {

    // ============================
    // CAMPOS
    // ============================

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtExistencia;


    // ============================
    // TABLA
    // ============================

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Number> colPrecio;

    @FXML
    private TableColumn<Producto, Number> colExistencia;


    // ============================
    // MENSAJES
    // ============================

    @FXML
    private Label lblMensaje;


    // ============================
    // LISTA DE PRODUCTOS
    // ============================

    private final ObservableList<Producto> listaProductos =
            FXCollections.observableArrayList();


    // ============================
    // INITIALIZE
    // ============================

    @FXML
    public void initialize() {

        // Asociamos cada columna con un atributo del producto.

        colCodigo.setCellValueFactory(datos ->
                new SimpleStringProperty(
                        datos.getValue().getCodigo()
                )
        );

        colNombre.setCellValueFactory(datos ->
                new SimpleStringProperty(
                        datos.getValue().getNombre()
                )
        );

        colCategoria.setCellValueFactory(datos ->
                new SimpleStringProperty(
                        datos.getValue().getCategoria()
                )
        );

        colPrecio.setCellValueFactory(datos ->
                new SimpleDoubleProperty(
                        datos.getValue().getPrecio()
                )
        );

        colExistencia.setCellValueFactory(datos ->
                new SimpleIntegerProperty(
                        datos.getValue().getExistencia()
                )
        );


        // Conectamos la ObservableList con la tabla.

        tblProductos.setItems(listaProductos);


        // Cuando seleccionamos un producto de la tabla,
        // sus datos pasan automáticamente a los TextField.

        tblProductos
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, productoAnterior, productoSeleccionado) -> {

                    if (productoSeleccionado != null) {

                        cargarProductoEnCampos(productoSeleccionado);
                    }
                });
    }


    // ============================================================
    // CREATE
    // GUARDAR PRODUCTO
    // ============================================================

    @FXML
    private void guardarProducto() {

        if (!validarCampos()) {
            return;
        }

        try {

            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String categoria = txtCategoria.getText().trim();

            double precio =
                    Double.parseDouble(
                            txtPrecio.getText().trim()
                    );

            int existencia =
                    Integer.parseInt(
                            txtExistencia.getText().trim()
                    );


            // Validaciones numéricas

            if (precio < 0) {

                mostrarAdvertencia(
                        "Precio incorrecto",
                        "El precio no puede ser negativo."
                );

                return;
            }

            if (existencia < 0) {

                mostrarAdvertencia(
                        "Existencia incorrecta",
                        "La existencia no puede ser negativa."
                );

                return;
            }


            // Evitar códigos repetidos

            if (existeCodigo(codigo)) {

                mostrarAdvertencia(
                        "Código duplicado",
                        "Ya existe un producto con el código " + codigo + "."
                );

                return;
            }


            // Creamos el objeto

            Producto nuevoProducto =
                    new Producto(
                            codigo,
                            nombre,
                            categoria,
                            precio,
                            existencia
                    );


            // CREATE

            listaProductos.add(nuevoProducto);


            limpiarCampos();

            tblProductos
                    .getSelectionModel()
                    .clearSelection();

            lblMensaje.setText(
                    "Producto guardado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarAdvertencia(
                    "Datos incorrectos",
                    "El precio debe ser decimal y la existencia debe ser un número entero."
            );
        }
    }


    // ============================================================
    // READ
    // VER DETALLE
    // ============================================================

    @FXML
    private void verDetalle() {

        Producto productoSeleccionado =
                obtenerProductoSeleccionado();

        if (productoSeleccionado == null) {

            mostrarAdvertencia(
                    "Sin selección",
                    "Seleccione un producto para ver sus detalles."
            );

            return;
        }


        Alert alerta =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alerta.setTitle(
                "Detalle del producto"
        );

        alerta.setHeaderText(
                productoSeleccionado.getNombre()
        );

        alerta.setContentText(
                "Código: "
                        + productoSeleccionado.getCodigo()
                        + "\n\nNombre: "
                        + productoSeleccionado.getNombre()
                        + "\n\nCategoría: "
                        + productoSeleccionado.getCategoria()
                        + "\n\nPrecio: C$ "
                        + String.format(
                        "%.2f",
                        productoSeleccionado.getPrecio()
                )
                        + "\n\nExistencia: "
                        + productoSeleccionado.getExistencia()
                        + " unidades"
        );

        alerta.showAndWait();
    }


    // ============================================================
    // UPDATE
    // EDITAR PRODUCTO
    // ============================================================

    @FXML
    private void editarProducto() {

        Producto productoSeleccionado =
                obtenerProductoSeleccionado();

        if (productoSeleccionado == null) {

            mostrarAdvertencia(
                    "Sin selección",
                    "Seleccione un producto de la tabla para editarlo."
            );

            lblMensaje.setText(
                    "No hay un producto seleccionado."
            );

            return;
        }


        if (!validarCampos()) {
            return;
        }


        try {

            String nuevoCodigo =
                    txtCodigo.getText().trim();

            double nuevoPrecio =
                    Double.parseDouble(
                            txtPrecio.getText().trim()
                    );

            int nuevaExistencia =
                    Integer.parseInt(
                            txtExistencia.getText().trim()
                    );


            if (nuevoPrecio < 0) {

                mostrarAdvertencia(
                        "Precio incorrecto",
                        "El precio no puede ser negativo."
                );

                return;
            }


            if (nuevaExistencia < 0) {

                mostrarAdvertencia(
                        "Existencia incorrecta",
                        "La existencia no puede ser negativa."
                );

                return;
            }


            // Si cambió el código,
            // comprobamos que el nuevo no pertenezca a otro producto.

            for (Producto producto : listaProductos) {

                if (producto != productoSeleccionado
                        && producto.getCodigo()
                        .equalsIgnoreCase(nuevoCodigo)) {

                    mostrarAdvertencia(
                            "Código duplicado",
                            "Ya existe otro producto con ese código."
                    );

                    return;
                }
            }


            // UPDATE

            productoSeleccionado.setCodigo(
                    nuevoCodigo
            );

            productoSeleccionado.setNombre(
                    txtNombre.getText().trim()
            );

            productoSeleccionado.setCategoria(
                    txtCategoria.getText().trim()
            );

            productoSeleccionado.setPrecio(
                    nuevoPrecio
            );

            productoSeleccionado.setExistencia(
                    nuevaExistencia
            );


            // Refrescar TableView

            tblProductos.refresh();


            limpiarCampos();

            tblProductos
                    .getSelectionModel()
                    .clearSelection();

            lblMensaje.setText(
                    "Producto editado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarAdvertencia(
                    "Datos incorrectos",
                    "El precio debe ser decimal y la existencia un número entero."
            );
        }
    }


    // ============================================================
    // DELETE
    // ELIMINAR PRODUCTO
    // ============================================================

    @FXML
    private void eliminarProducto() {

        Producto productoSeleccionado =
                obtenerProductoSeleccionado();


        if (productoSeleccionado == null) {

            mostrarAdvertencia(
                    "Sin selección",
                    "Seleccione un producto para eliminarlo."
            );

            lblMensaje.setText(
                    "No hay un producto seleccionado."
            );

            return;
        }


        Alert confirmacion =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmacion.setTitle(
                "Confirmar eliminación"
        );

        confirmacion.setHeaderText(
                "Eliminar producto"
        );

        confirmacion.setContentText(
                "¿Está seguro de eliminar el producto "
                        + productoSeleccionado.getNombre()
                        + "?"
        );


        Optional<ButtonType> resultado =
                confirmacion.showAndWait();


        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {

            // DELETE

            listaProductos.remove(
                    productoSeleccionado
            );

            limpiarCampos();

            tblProductos
                    .getSelectionModel()
                    .clearSelection();

            lblMensaje.setText(
                    "Producto eliminado correctamente."
            );
        }
    }


    // ============================================================
    // NUEVO
    // ============================================================

    @FXML
    private void nuevoProducto() {

        tblProductos
                .getSelectionModel()
                .clearSelection();

        limpiarCampos();

        txtCodigo.requestFocus();

        lblMensaje.setText(
                "Ingrese los datos del nuevo producto."
        );
    }


    // ============================================================
    // LIMPIAR
    // ============================================================

    @FXML
    private void limpiarCampos() {

        txtCodigo.clear();
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtExistencia.clear();
    }


    // ============================================================
    // ACERCA DE
    // ============================================================

    @FXML
    private void acercaDe() {

        Alert alerta =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alerta.setTitle(
                "Acerca de"
        );

        alerta.setHeaderText(
                "Distribuidora El Güegüense"
        );

        alerta.setContentText(
                """
                Sistema de administración de productos.

                Asignatura:
                Programación de Aplicaciones de Escritorio

                Tecnología:
                JavaFX

                Funciones:
                • Crear productos
                • Consultar productos
                • Editar productos
                • Eliminar productos

                Autor:
                Rafael Hernández
                """
        );

        alerta.showAndWait();
    }


    // ============================================================
    // SALIR
    // ============================================================

    @FXML
    private void salir() {

        Alert confirmacion =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmacion.setTitle(
                "Salir"
        );

        confirmacion.setHeaderText(
                "Cerrar aplicación"
        );

        confirmacion.setContentText(
                "¿Está seguro de que desea salir?"
        );


        Optional<ButtonType> respuesta =
                confirmacion.showAndWait();


        if (respuesta.isPresent()
                && respuesta.get() == ButtonType.OK) {

            Stage ventana =
                    (Stage) tblProductos
                            .getScene()
                            .getWindow();

            ventana.close();
        }
    }


    // ============================================================
    // MÉTODOS AUXILIARES
    // ============================================================

    private Producto obtenerProductoSeleccionado() {

        return tblProductos
                .getSelectionModel()
                .getSelectedItem();
    }


    private void cargarProductoEnCampos(
            Producto producto
    ) {

        txtCodigo.setText(
                producto.getCodigo()
        );

        txtNombre.setText(
                producto.getNombre()
        );

        txtCategoria.setText(
                producto.getCategoria()
        );

        txtPrecio.setText(
                String.valueOf(
                        producto.getPrecio()
                )
        );

        txtExistencia.setText(
                String.valueOf(
                        producto.getExistencia()
                )
        );
    }


    private boolean validarCampos() {

        if (txtCodigo.getText().isBlank()
                || txtNombre.getText().isBlank()
                || txtCategoria.getText().isBlank()
                || txtPrecio.getText().isBlank()
                || txtExistencia.getText().isBlank()) {

            mostrarAdvertencia(
                    "Campos incompletos",
                    "Debe completar todos los campos."
            );

            return false;
        }

        return true;
    }


    private boolean existeCodigo(
            String codigo
    ) {

        for (Producto producto : listaProductos) {

            if (producto
                    .getCodigo()
                    .equalsIgnoreCase(codigo)) {

                return true;
            }
        }

        return false;
    }


    private void mostrarAdvertencia(
            String titulo,
            String mensaje
    ) {

        Alert alerta =
                new Alert(
                        Alert.AlertType.WARNING
                );

        alerta.setTitle(titulo);

        alerta.setHeaderText(null);

        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}