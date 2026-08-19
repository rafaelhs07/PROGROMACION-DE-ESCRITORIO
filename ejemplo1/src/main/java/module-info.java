module org.example.ejemplo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejemplo1 to javafx.fxml;
    exports org.example.ejemplo1;
}