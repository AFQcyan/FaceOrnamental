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


    String emotionTrans(double conf){
        String per = " ";
        if(conf <= 20){
            per = "아주 약간";
        }
        else if(conf <= 40){
            per = "알아볼만큼";
        }
        else if(conf <= 60){
            per = "아주 많이";
        }
        else if(conf <= 80){
            per = "티안나는 닮은꼴일정도로";
        }
        else{
            per = "도플갱어라 해도 모를정도로";
        }
        return per;
    }

    void printFamous(){
        emotionText.setText("감정 : " + faceInfos.get(status)[0] + " (" + faceConfidences.get(status)[0] +"%)");
        countRemain.setText("(" + (status + 1) + " / " + endResult + ")");
        headerFamousConfidence.setText("확률(" + faceConfidences.get(status)[0] + "%)");
    }

    public void setResult(){
        endResult =  faceInfos.size();
        if(status == endResult - 1){
            submitResultBtn.setText("끝내기");
            showToNext.setText("모든 결과가 산출되었습니다. 끝내기 버튼을 누르면 해당 검사 결과가 자동저장됩니다.");
            printFamous();
            status++;
        }
        else if(status == 0){
            submitResultBtn.setText("다음 결과 보기");
            showToNext.setText("다음 결과가 남았어요! \n버튼을 눌러 다음 결과를 확인해보세요!");
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