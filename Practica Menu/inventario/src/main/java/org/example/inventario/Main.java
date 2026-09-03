package org.example.inventario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader =
                new FXMLLoader(
                        Main.class.getResource(
                                "inventario-view.fxml"
                        )
                );

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle(
                "Distribuidora El Güegüense - Inventario"
        );

        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}