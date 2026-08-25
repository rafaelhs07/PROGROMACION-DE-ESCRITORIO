module org.example.ejercicio13 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio13 to javafx.fxml;
    exports org.example.ejercicio13;
}