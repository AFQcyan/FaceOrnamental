package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class LoginView{

    DBUtil db = new DBUtil();

    public static String loginUserid;

    static boolean isLogin = false;

    @FXML
    private TextField idInput;
    @FXML
    private TextField pwInput;
    @FXML
    private Button goToRegisterBtn;
    @FXML
    private Button loginBtn;
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

    public void goToRegister(){
        try{
            Parent nextScene = FXMLLoader.load(getClass().getResource("register-view.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) goToRegisterBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryLogin(){
        ArrayList<String> resultId = DBUtil.fetchDB("userId");
        ArrayList<String> resultPw = DBUtil.fetchDB("userPw");
        for(int i = 0; i < resultId.size(); i++){
           if(resultId.get(i).equals(idInput.getText()) && resultPw.get(i).equals(pwInput.getText())){
                isLogin = true;

//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("");
//                alert.setHeaderText("로그인 확인");
//                alert.setContentText("성공적으로 로그인되었습니다.");
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == ButtonType.OK){
//
//                }
//                else{
//                    continue;
//                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText("로그인 성공");
                alert.setContentText(idInput.getText() + "님의 로그인이 정상적으로 완료 되었습니다.");
                alert.showAndWait();
                loginUserid = DBUtil.fetchDB("userId").get(i);
               try{
                   Parent nextScene = FXMLLoader.load(getClass().getResource("main-page.fxml"));
                   Scene scene = new Scene(nextScene);
                   Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
                   primaryStage.setScene(scene);
               } catch (IOException e) {
                   e.printStackTrace();
               }
                break;
            }
        }
            if(isLogin == false){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText("로그인 실패");
                alert.setContentText("아이디 또는 비밀번호가 올바르지 않습니다.");
                alert.showAndWait();
            }
    }
}
