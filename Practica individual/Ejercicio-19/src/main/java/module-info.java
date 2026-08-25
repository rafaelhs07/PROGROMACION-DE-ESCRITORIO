module org.example.ejercicio19 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio19 to javafx.fxml;
    exports org.example.ejercicio19;
}