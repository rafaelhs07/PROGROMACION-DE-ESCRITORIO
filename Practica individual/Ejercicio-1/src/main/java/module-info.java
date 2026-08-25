module org.example.ejercicio1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio1 to javafx.fxml;
    exports org.example.ejercicio1;
}