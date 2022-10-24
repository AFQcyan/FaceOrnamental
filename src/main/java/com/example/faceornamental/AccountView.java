package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountView {

    @FXML
    private Text celeb;
    @FXML
    private Text emote;
    @FXML
    private Text gender;
    @FXML
    private Text age;
    @FXML
    private Text pose;

    String transNull(String testNull){
        String per = testNull;
        if(testNull == null){
            per = "결과 없음";
        }
        return per;
    }


    public void reloadAccount(){
        celeb.setText("닮은 유명인 : " + transNull(DBUtil.fetchDB("userNowCeleb", LoginView.loginUserid).get(0)));
        emote.setText("감정 : " + transNull(DBUtil.fetchDB("userNowEmote", LoginView.loginUserid).get(0)));
        gender.setText("성별 : " + transNull(DBUtil.fetchDB("userNowGender", LoginView.loginUserid).get(0)));
        age.setText("나이 : " + transNull(DBUtil.fetchDB("userNowAge", LoginView.loginUserid).get(0)));
        pose.setText("상태 : " + transNull(DBUtil.fetchDB("userNowPose", LoginView.loginUserid).get(0)));

    }
    public void backToMain(){
        try{
            Parent nextScene = FXMLLoader.load(getClass().getResource("main-page.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) celeb.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
