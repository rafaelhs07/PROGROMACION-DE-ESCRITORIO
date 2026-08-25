module org.example.ejercicio5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio5 to javafx.fxml;
    exports org.example.ejercicio5;
}