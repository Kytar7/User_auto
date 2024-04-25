module com.example.autorization {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.autorization to javafx.fxml;
    exports com.example.autorization;
}