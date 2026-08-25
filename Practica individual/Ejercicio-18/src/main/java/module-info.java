module org.example.ejercicio18 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio18 to javafx.fxml;
    exports org.example.ejercicio18;
}