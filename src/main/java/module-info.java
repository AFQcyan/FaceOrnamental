module com.example.faceornamental {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires json.simple;
    requires java.sql;
    requires mysql.connector.java;
//    requires json.simple;


    opens com.example.faceornamental to javafx.fxml;
    exports com.example.faceornamental;
}