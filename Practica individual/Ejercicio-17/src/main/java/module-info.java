module org.example.ejercicio17 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio17 to javafx.fxml;
    exports org.example.ejercicio17;
}