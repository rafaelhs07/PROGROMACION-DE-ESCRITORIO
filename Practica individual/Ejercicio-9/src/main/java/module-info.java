module org.example.ejercicio9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio9 to javafx.fxml;
    exports org.example.ejercicio9;
}