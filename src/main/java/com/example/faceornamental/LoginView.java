package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class LoginView {

    DBUtil db = new DBUtil();

    @FXML
    private TextField idInput;
    @FXML
    private TextField pwInput;
    @FXML
    private Button goToRegisterBtn;
    @FXML
    private Button loginBtn;

    public void goToRegister(){
        try{
            Parent nextScene = FXMLLoader.load(getClass().getResource("face-famous.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) goToRegisterBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryLogin(){
        ResultSet rs = db.fetchDB();
    }
}
