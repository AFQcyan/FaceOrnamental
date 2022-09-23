package com.example.faceornamental;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FaceReconize {
    FileChooser fileChooser = new FileChooser();

    @FXML
    private Label headerText;
    @FXML
    private ImageView showImage;
    @FXML
    private Button verticalButton;
    @FXML
    private Button horizonButton;
    @FXML
    private Button submittingButton;

    private Boolean isPhotoSet = false;

    public void changeToVertical(){
        showImage.setFitWidth(300);
        showImage.setFitHeight(400);
        showImage.setLayoutX(90);
        showImage.setLayoutY(121);
    }
    public void changeToHorizon(){
        showImage.setFitWidth(400);
        showImage.setFitHeight(300);
        showImage.setLayoutX(54);
        showImage.setLayoutY(178);
    }
    public void submitManage(){
        if(isPhotoSet == true){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("face-defrag.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("안뭏");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(selectedFile);
                BufferedInputStream bis = new BufferedInputStream(fis);

                Image img = new Image(bis);
                showImage.setImage(img);

                try {
                    bis.close();
                    fis.close();
                    isPhotoSet = true;
                } catch (IOException e) {
                    System.out.println("a");
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                System.out.println("b");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("실패");
        }
    }
}