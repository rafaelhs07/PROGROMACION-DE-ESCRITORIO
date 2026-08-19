module org.example.ejemplo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens org.example.ejemplo2 to javafx.fxml;
    exports org.example.ejemplo2;
}