module com.example.faceornamental {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.faceornamental to javafx.fxml;
    exports com.example.faceornamental;
}