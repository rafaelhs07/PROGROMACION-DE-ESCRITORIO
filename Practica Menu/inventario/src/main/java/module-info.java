module org.example.inventario {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.inventario to javafx.fxml;
    exports org.example.inventario;
}