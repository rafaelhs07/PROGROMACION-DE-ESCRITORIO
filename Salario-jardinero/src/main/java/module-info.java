module org.example.salariojardinero {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.salariojardinero to javafx.fxml;
    exports org.example.salariojardinero;
}