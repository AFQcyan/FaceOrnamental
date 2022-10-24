package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FaceFragResult extends FaceDefrag {

    DBUtil db = new DBUtil();

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
    @FXML
    private Button saveButton;


    int status = 0;
    int endResult;
    boolean isResultEnd = false;
    public static String emoji;
    public static String gender;
    public static String age;
    public static String pose;


    String transEmoji(String emoji) {
        String per = " ";
        if (emoji.equals("angry")) {
            per = "화남";
        } else if (emoji.equals("disgust")) {
            per = "역겨움";
        } else if (emoji.equals("fear")) {
            per = "공포를 느낌";
        } else if (emoji.equals("laugh")) {
            per = "큰 웃음";
        } else if (emoji.equals("neutral")) {
            per = "무표정";
        } else if (emoji.equals("sad")) {
            per = "슬픔";
        } else if (emoji.equals("surprise")) {
            per = "놀람";
        } else if (emoji.equals("smile")) {
            per = "미소 지음";
        } else if (emoji.equals("talking")) {
            per = "대화중";
        }
        return per;
    }

    String transGender(String emoji) {
        String per = " ";
        if (emoji.equals("male")) {
            per = "남성";
        } else if (emoji.equals("female")) {
            per = "여성";
        } else if (emoji.equals("child")) {
            per = "어린이";
        }
        return per;
    }

    String transPose(String emoji) {
        String per = " ";
        if (emoji.equals("part_face")) {
            per = "일부분의 얼굴";
        } else if (emoji.equals("false_face")) {
            per = "사람이 아님";
        } else if (emoji.equals("sunglasses")) {
            per = "선글라스를 낌";
        } else if (emoji.equals("frontal_face")) {
            per = "정면";
        } else if (emoji.equals("left_face")) {
            per = "왼쪽 얼굴";
        } else if (emoji.equals("right_face")) {
            per = "오른쪽 얼굴";
        } else if (emoji.equals("rotate_face")) {
            per = "돌아가는 얼굴";
        }
        return per;
    }


    void printFamous() {
        emoji = transEmoji(faceInfos.get(status)[0]) + " (" + faceConfidences.get(status)[0] + "%)";
        gender = transGender(faceInfos.get(status)[1]) + " (" + faceConfidences.get(status)[1] + "%)";
        age = faceInfos.get(status)[2] + " (" + faceConfidences.get(status)[2] + "%)";
        pose = transPose(faceInfos.get(status)[3]) + " (" + faceConfidences.get(status)[3] + "%)";
        emotionText.setText("감정 : " + emoji);
        genderText.setText("성별 : " + gender);
        ageText.setText("나이 : " + age);
        poseText.setText("자세 : " + pose);
        countRemain.setText("(" + (status + 1) + " / " + endResult + ")");
        if(LoginView.isLogin){
            DBUtil.updateDB("userNowEmote", LoginView.loginUserid, emoji);
            DBUtil.updateDB("userNowGender", LoginView.loginUserid, gender);
            DBUtil.updateDB("userNowAge", LoginView.loginUserid, age);
            DBUtil.updateDB("userNowPose", LoginView.loginUserid, pose);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("자동 저장");
            alert.setContentText("자동 저장 되었습니다!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("자동 저장 실패");
            alert.setContentText("로그인 하시면 자동저장 기능을 사용하실 수 있습니다!");
            alert.showAndWait();
        }
    }

    public void setResult() {
        endResult = faceInfos.size();
        if (status == endResult - 1) {
            submitResultBtn.setText("끝내기");
            printFamous();
            status++;
        } else if (status == 0) {
            submitResultBtn.setText("다음 결과 보기");
            printFamous();
            status++;
        } else if (status == endResult) {
            System.out.println("b");
            try {
                Parent nextScene = FXMLLoader.load(getClass().getResource("main-page.fxml"));
                Scene scene = new Scene(nextScene);
                Stage primaryStage = (Stage) submitResultBtn.getScene().getWindow();
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            printFamous();
            status++;
        }
    }

}
