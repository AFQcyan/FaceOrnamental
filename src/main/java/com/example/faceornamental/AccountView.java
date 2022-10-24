package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

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

    public void reloadAccount(){
        emote.setText("감정 : " + FaceFragResult.emoji);
    }
}
