package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class
MainPageHandler{

    LoginView lgv = new LoginView();
    @FXML
    private Text mainText;
    @FXML
    private Text loginID;
    @FXML
    Button loginRegisterBtn;
    @FXML
    private Button recognizeFaceBtn;
    @FXML
    private Button FindFamousBtn;
    @FXML
    private Button goToProfileBtn;
    @FXML
    private Button goToAccount;
    @FXML
    private Button goToRefresh;

    public void goToFaceRecog(){
        try{
            Parent nextScene = FXMLLoader.load(getClass().getResource("face-reconize.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) recognizeFaceBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToFindFamous(){
        try{
            Parent nextScene = FXMLLoader.load(getClass().getResource("face-famous.fxml"));
            Scene scene = new Scene(nextScene);
            Stage primaryStage = (Stage) recognizeFaceBtn.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToLogin(){
        if(LoginView.isLogin){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("이미 로그인됨");
            alert.setContentText("이미 " + LoginView.loginUserid + " 님으로 로그인 되어있습니다. 로그아웃 하시겠습니까?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                LoginView.isLogin = false;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText("로그아웃됨");
                alert.setContentText("성공적으로 로그아웃 되었습니다.");
                alert.showAndWait();
                loginRegisterBtn.setText("로그인 / 회원가입");
                loginID.setText("현재 로그인된 ID : ");
                LoginView.loginUserid = null;
            }
            else{
            }

        }
        else{
            try {
                Parent nextScene = FXMLLoader.load(getClass().getResource("login-view.fxml"));
                Scene scene = new Scene(nextScene);
                Stage primaryStage = (Stage) recognizeFaceBtn.getScene().getWindow();
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void accountMethod(){
        if(LoginView.isLogin == false){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("로그인 되어있지 않음!");
            alert.setContentText("현재 로그인 되어있지 않습니다. 로그인 후 다시 시도하세요.");
            alert.showAndWait();
        }
        else{
            try {
                Parent nextScene = FXMLLoader.load(getClass().getResource("account-view.fxml"));
                Scene scene = new Scene(nextScene);
                Stage primaryStage = (Stage) recognizeFaceBtn.getScene().getWindow();
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void reloadMethod(){
        if(LoginView.isLogin == false){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("로그인 되어있지 않음!");
            alert.setContentText("현재 로그인 되어있지 않습니다. 로그인 후 다시 시도하세요.");
            alert.showAndWait();
        }
        else{
            loginID.setText("현재 로그인된 ID : " + LoginView.loginUserid);
            loginRegisterBtn.setText("로그아웃");
        }
    }

}
