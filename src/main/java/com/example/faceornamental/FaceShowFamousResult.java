package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class FaceShowFamousResult {
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

    FaceCelebFrag fcfg = new FaceCelebFrag();


    int status = 0;
    int endResult = fcfg.faceLength;
    boolean isResultEnd = false;


    public void setResult(){
        if(status == 0){
            submitResultBtn.setText("다음 결과 보기");
            headerFamousCeleb.setText(fcfg.getFamous[status]);
            headerFamousConfidence.setText(String.valueOf(fcfg.famousConfidence[status]));
            status++;
        }
    }


}
