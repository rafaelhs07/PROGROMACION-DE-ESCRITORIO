module org.example.registropaciente {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens org.example.registropaciente to javafx.fxml;
    exports org.example.registropaciente;
}