module org.example.ejercicio4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio4 to javafx.fxml;
    exports org.example.ejercicio4;
}