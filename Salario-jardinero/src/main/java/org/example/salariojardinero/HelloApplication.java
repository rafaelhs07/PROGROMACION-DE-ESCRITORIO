package org.example.salariojardinero;

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

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);


        Label lblTitle = new Label("Cálculo de salario del trabajador");

 
        Label lblSalario = new Label("Ingrese su salario:");
        TextField tfSalario = new TextField();

        // BOTONES
        Button btnSeguroSocial = new Button("Calcular Seguro Social 7%");
        Button btnBono = new Button("Calcular Bono");

        // RESULTADOS
        Label lblSeguro = new Label("Seguro Social: ");
        Label lblBono = new Label("Bono: ");
        Label lblSalarioFinal = new Label("Salario final: ");


        // CALCULAR EL  SEGURO SOCIAL
        btnSeguroSocial.setOnAction(actionEvent -> {

            double salario = Double.parseDouble(tfSalario.getText());

            double seguroSocial = salario * 0.07;

            lblSeguro.setText(
                    "Seguro Social: C$ " + seguroSocial
            );
        });


        // CALCULAR EL BONO
        btnBono.setOnAction(actionEvent -> {

            double salario = Double.parseDouble(tfSalario.getText());

            double bono;

            if (salario < 12000) {

                bono = salario * 0.10;

            } else if (salario <= 20000) {

                bono = salario * 0.05;

            } else {

                bono = salario * 0.03;
            }

            double seguroSocial = salario * 0.07;

            double salarioFinal =
                    salario - seguroSocial + bono;

            lblBono.setText(
                    "Bono: C$ " + bono
            );

            lblSalarioFinal.setText(
                    "Salario final: C$ " + salarioFinal
            );
        });

        // POSICION DE LOS BOTONES
        HBox buttons = new HBox(btnSeguroSocial, btnBono);
        buttons.setSpacing(10);


        root.getChildren().addAll(
                lblTitle, lblSalario, tfSalario, buttons, lblSeguro, lblBono, lblSalarioFinal
        );

        // pantalla
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("UAM - Salario del trabajador");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}