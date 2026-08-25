module org.example.ejercicio7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio7 to javafx.fxml;
    exports org.example.ejercicio7;
}