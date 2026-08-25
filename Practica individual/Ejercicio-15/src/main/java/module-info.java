module org.example.ejercicio15 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio15 to javafx.fxml;
    exports org.example.ejercicio15;
}