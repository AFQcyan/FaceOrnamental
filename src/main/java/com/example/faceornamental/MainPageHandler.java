package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class
MainPageHandler{
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
            Parent nextScene = FXMLLoader.load(getClass().getResource("face-reconize.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) recognizeFaceBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToFindFamous(){
        try{
            Parent nextScene = FXMLLoader.load(getClass().getResource("face-famous.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) recognizeFaceBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToProfile(){

    }
    public void goToLogin(){
        try{
            Parent nextScene = FXMLLoader.load(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) recognizeFaceBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
