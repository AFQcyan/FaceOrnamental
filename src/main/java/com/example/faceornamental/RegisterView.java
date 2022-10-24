package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class RegisterView {
    DBUtil db = new DBUtil();

    @FXML
    private TextField idInput;
    @FXML
    private TextField pwInput;
    @FXML
    private Button registerBtn;
    @FXML
    private Button backButton;

    public void backToPrevious(){
        try{
            Parent nextScene = FXMLLoader.load(getClass().getResource("main-page.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryRegister(){
        boolean isDouble = false;
        String testId = idInput.getText();
        String testPw = pwInput.getText();
        ArrayList<String> resultId = DBUtil.fetchDB("userId", "*");
        for(int i = 0; i < resultId.size(); i++){
            if(resultId.get(i).equals(idInput.getText())){
                isDouble = true;
            }
        }
        if(testId.length() < 4 || testPw.length() < 4){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("글자수 부족");
            alert.setContentText("Id 와 패스워드는 4글자 이상이어야 합니다.");
            alert.showAndWait();
        }
        else if(testId.length() > 20 || testPw.length() > 20){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("글자수 초과");
            alert.setContentText("Id 와 패스워드는 20글자 이하이어야 합니다.");
            alert.showAndWait();
        }
        else if(isDouble){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("중복 아이디");
            alert.setContentText("이미 누군가가 사용한 아이디입니다.");
            alert.showAndWait();
        }
        else{
            Date date_now = new Date(System.currentTimeMillis());
            SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMdd");
            System.out.println(fourteen_format.format(date_now));
            DBUtil.registerDB(testId, testPw, fourteen_format.format(date_now),null,null,null);
        }
    }
}

