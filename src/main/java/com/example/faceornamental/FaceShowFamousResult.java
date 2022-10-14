package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FaceShowFamousResult extends FaceCelebFrag{
    FaceCelebFrag fcfg = new FaceCelebFrag();


    @FXML
    Text headerFamousCeleb;
    @FXML
    Text headerFamousConfidence;
    @FXML
    Text explainFamous;
    @FXML
    Text showToNext;
    @FXML
    Text countRemain;
    @FXML
    Button submitResultBtn;



    int status = 0;
    int endResult;
    boolean isResultEnd = false;


    String calcPercent(double conf){
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
        headerFamousCeleb.setText(getFamous.get(status));
        countRemain.setText("(" + (status + 1) + " / " + endResult + ")");
        headerFamousConfidence.setText("확률(" + famousConfidence.get(status) + "%)");
        explainFamous.setText("당신은 " + getFamous.get(status) + " 님과 \n[" + calcPercent(famousConfidence.get(status)) +"] 닮았어요!\n " + getFamous.get(status)  + "님의 얼굴이 궁금하다면,\n 직접 검색해보세요!");
    }

    public void setResult(){
        endResult =  getFamous.size();
        if(endResult == 1){
            System.out.println("a");
            submitResultBtn.setText("끝내기");
        showToNext.setText("모든 결과가 산출되었습니다. 끝내기 버튼을 누르면 해당 검사 결과가 자동저장됩니다.");
        printFamous();
        }
        else if(status == 0){
            submitResultBtn.setText("다음 결과 보기");
            showToNext.setText("다음 결과가 남았어요! \n버튼을 눌러 다음 결과를 확인해보세요!");
            printFamous();
            status++;
        }
        else if(status == endResult){
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
