module org.example.ejercicio14 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio14 to javafx.fxml;
    exports org.example.ejercicio14;
}