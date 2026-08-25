module org.example.ejercicio8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio8 to javafx.fxml;
    exports org.example.ejercicio8;
}