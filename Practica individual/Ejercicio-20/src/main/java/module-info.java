module org.example.ejercicio20 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio20 to javafx.fxml;
    exports org.example.ejercicio20;
}