package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageHandler {
    @FXML
    private Text mainText;
    @FXML
    private Button loginRegisterBtn;
    @FXML
    private Button recognizeFaceBtn;
    @FXML
    private Button FindFamousBtn;
    @FXML
    private Button goToProfileBtn;

    public void goToFaceRecog(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("face-reconize.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("안뭏");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToFindFamous(){

    }
    public void goToProfile(){

    }
    public void goToLogin(){

    }
}
