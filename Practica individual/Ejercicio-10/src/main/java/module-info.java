module org.example.ejercicio10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio10 to javafx.fxml;
    exports org.example.ejercicio10;
}