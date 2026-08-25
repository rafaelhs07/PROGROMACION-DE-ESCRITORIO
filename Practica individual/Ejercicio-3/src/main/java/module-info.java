module org.example.ejercicio3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio3 to javafx.fxml;
    exports org.example.ejercicio3;
}