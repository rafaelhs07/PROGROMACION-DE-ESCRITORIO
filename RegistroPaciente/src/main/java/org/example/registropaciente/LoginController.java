package org.example.registropaciente;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    private ImageView imagenCandado;

    private int intentos = 0;

    @FXML
    protected void iniciarSesion() {

        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        if (usuario.equals("admin") && password.equals("1234")) {

            Image candadoAbierto = new Image(
                    getClass().getResourceAsStream(
                            "/org/example/registropaciente/candado_abierto.png"
                    )
            );

            imagenCandado.setImage(candadoAbierto);

            lblMensaje.setText("Acceso correcto");

            PauseTransition pausa =
                    new PauseTransition(Duration.seconds(1));

            pausa.setOnFinished(event -> {
                abrirPacientes();
            });

            pausa.play();

        } else {

            intentos++;

            if (intentos >= 3) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Acceso bloqueado");
                alerta.setHeaderText(null);
                alerta.setContentText(
                        "Ha superado el número máximo de intentos."
                );

                alerta.showAndWait();

                Stage ventana =
                        (Stage) txtUsuario.getScene().getWindow();

                ventana.close();

            } else {

                int intentosRestantes = 3 - intentos;

                Alert alerta = new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Inicio de sesión");
                alerta.setHeaderText(null);

                alerta.setContentText(
                        "Usuario o contraseña incorrectos.\n" +
                                "Intentos restantes: " + intentosRestantes
                );

                alerta.showAndWait();

                txtPassword.clear();
                txtPassword.requestFocus();
            }
        }
    }

    private void abrirPacientes() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "paciente-view.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());

            Stage ventana =
                    (Stage) txtUsuario
                            .getScene()
                            .getWindow();

            ventana.setScene(scene);

            ventana.setTitle(
                    "Registro de Pacientes"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}