module org.example.ejercicio16 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio16 to javafx.fxml;
    exports org.example.ejercicio16;
}