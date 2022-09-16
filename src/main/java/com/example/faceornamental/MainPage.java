package com.example.faceornamental;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("Cafe24Ssurround.ttf"),16);
        FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("main-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 800);
        stage.setTitle("너의 관상은");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}