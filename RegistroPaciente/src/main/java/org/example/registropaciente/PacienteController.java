package org.example.registropaciente;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.registropaciente.dao.PacienteDAD;

public class PacienteController {

    private final PacienteDAD pacientes = new PacienteDAD();

    @FXML
    private TextField txtnombres;

    @FXML
    private TextField txtapellidos;

    @FXML
    private RadioButton radioMasculino;

    @FXML
    private RadioButton radioFemenino;

    @FXML
    private ToggleGroup grupoGenero;

    @FXML
    private CheckBox checkEnfermo;

    @FXML
    private Label lblContador;

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> columnaNombres;

    @FXML
    private TableColumn<Paciente, String> columnaApellidos;

    @FXML
    private TableColumn<Paciente, String> columnaGenero;

    @FXML
    private TableColumn<Paciente, String> columnaEstado;

    @FXML
    public void initialize() {

        columnaNombres.setCellValueFactory(
                new PropertyValueFactory<>("nombres")
        );

        columnaApellidos.setCellValueFactory(
                new PropertyValueFactory<>("apellidos")
        );

        columnaGenero.setCellValueFactory(
                new PropertyValueFactory<>("genero")
        );

        columnaEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );
    }

    @FXML
    protected void agregarOnclick() {

        String nombres = txtnombres.getText().trim();
        String apellidos = txtapellidos.getText().trim();

        String genero = "";

        if (radioMasculino.isSelected()) {

            genero = "Masculino";

        } else if (radioFemenino.isSelected()) {

            genero = "Femenino";
        }

        boolean enfermo = checkEnfermo.isSelected();

        if (nombres.isEmpty()
                || apellidos.isEmpty()
                || genero.isEmpty()) {

            Alert alerta =
                    new Alert(Alert.AlertType.WARNING);

            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);

            alerta.setContentText(
                    "Debe ingresar nombres, apellidos y género."
            );

            alerta.showAndWait();

            return;
        }

        Paciente paciente =
                new Paciente(
                        nombres,
                        apellidos,
                        genero,
                        enfermo
                );

        pacientes.AgregarPaciente(paciente);

        tablaPacientes.getItems().add(paciente);

        cantidadPaciente();

        limpiarCampos();
    }

    private void cantidadPaciente() {

        lblContador.setText(
                String.valueOf(
                        pacientes.listarPacientes().size()
                )
        );
    }

    private void limpiarCampos() {

        txtnombres.clear();
        txtapellidos.clear();

        grupoGenero.selectToggle(null);

        checkEnfermo.setSelected(false);

        txtnombres.requestFocus();
    }
}