module org.example.ejercicio11 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio11 to javafx.fxml;
    exports org.example.ejercicio11;
}