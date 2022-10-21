package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FaceFragResult extends FaceDefrag{

    @FXML
    Text headerFaceReco;
    @FXML
    Text emotionText;
    @FXML
    Text genderText;
    @FXML
    Text ageText;
    @FXML
    Text poseText;
    @FXML
    Text countRemain;
    @FXML
    Button submitResultBtn;



    int status = 0;
    int endResult;
    boolean isResultEnd = false;


    String transEmoji(String emoji){
        String per = " ";
        if(emoji.equals("angry")){
            per = "화남";
        }
        else if(emoji.equals("disgust")){
            per = "역겨움";
        }
        else if(emoji.equals("fear")){
            per = "공포를 느낌";
        }
        else if(emoji.equals("laugh")){
            per = "큰 웃음";
        }
        else if(emoji.equals("neutral")){
            per = "무표정";
        }
        else if(emoji.equals("sad")){
            per = "슬픔";
        }
        else if(emoji.equals("surprise")){
            per = "놀람";
        }
        else if(emoji.equals("smile")){
            per = "미소 지음";
        }
        else if(emoji.equals("talking")){
            per = "대화중";
        }
        return per;
    }


    void printFamous(){
        emotionText.setText("감정 : " + transEmoji(faceInfos.get(status)[0]) + " (" + faceConfidences.get(status)[0] +"%)");
        genderText.setText("성별 : " + faceInfos.get(status)[1] + " (" + faceConfidences.get(status)[1] +"%)");
        ageText.setText("나이 : " + faceInfos.get(status)[2] + " (" + faceConfidences.get(status)[2] +"%)");
        poseText.setText("자세 : " + faceInfos.get(status)[3] + " (" + faceConfidences.get(status)[3] +"%)");
        countRemain.setText("(" + (status + 1) + " / " + endResult + ")");
    }

    public void setResult(){
        endResult =  faceInfos.size();
        if(status == endResult - 1){
            submitResultBtn.setText("끝내기");
            printFamous();
            status++;
        }
        else if(status == 0){
            submitResultBtn.setText("다음 결과 보기");
            printFamous();
            status++;
        }
        else if(status == endResult){
            System.out.println("b");
            try{
                Parent nextScene = FXMLLoader.load(getClass().getResource("main-page.fxml"));
                Scene scene = new Scene(nextScene);
                Stage primaryStage = (Stage) submitResultBtn.getScene().getWindow();
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            printFamous();
            status++;
        }
    }
}
