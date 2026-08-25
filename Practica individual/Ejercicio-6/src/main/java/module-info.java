module org.example.ejercicio6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio6 to javafx.fxml;
    exports org.example.ejercicio6;
}