package com.example.faceornamental;

import javafx.fxml.FXML;

import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.control.Button;

public class FaceDefrag {

    @FXML
    private Button getSetGo;

    String[] info = new String[4];
    double[] conf = new double[4];

    static ArrayList<String[]> faceInfos = new ArrayList<>();
    static ArrayList<double[]> faceConfidences = new ArrayList<>();
    static int faceLength;


    public void faceDefrag() {


        String clientId = "Ioc_NlXQCsnnx4k5zacM";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "1PRDknN3bW";//애플리케이션 클라이언트 시크릿값";


        try {

            faceInfos = new ArrayList<>();
            faceConfidences = new ArrayList<>();

            FaceReconize fcr = new FaceReconize();
            String paramName = "image"; // 파라미터명은 image로 지정
            System.out.println(fcr.getSendToNext());
            String imgFile = fcr.sendToNext;
            File uploadFile = new File(imgFile);
//            String apiURL = "https://openapi.naver.com/v1/vision/celebrity"; // 유명인 얼굴 인식
            String apiURL = "https://openapi.naver.com/v1/vision/face"; // 얼굴 감지
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if (br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                printInfo(response.toString());
                try {
                    Parent nextScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("face-frag-result.fxml")));
                    Scene scene = new Scene(nextScene);
                    Stage primaryStage = (Stage) getSetGo.getScene().getWindow();
                    primaryStage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void printInfo(String target) {
        JSONParser jsonParser = new JSONParser();

        //JSON데이터를 넣어 JSON Object 로 만들어 준다.
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(target);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray getFaces = (JSONArray) jsonObject.get("faces");
        faceLength = getFaces.size();
        //faces의 배열을 추출
        if (faceLength > 0) {
            for (int i = 0; i < faceLength; i++) {
                info = new String[4];
                conf = new double[4];

                System.out.println(faceLength);
                JSONObject getFaceInfo = (JSONObject) getFaces.get(i);

                JSONObject getEmotion = (JSONObject) getFaceInfo.get("emotion");
                String emotion = (String) getEmotion.get("value");
                Double getEmotionConfidence = (Double) getEmotion.get("confidence");
                Double emotionConfidence = (double) Math.round(getEmotionConfidence * 10000) / 100;
                info[0] = emotion;
                conf[0] = emotionConfidence;

                JSONObject getGender = (JSONObject) getFaceInfo.get("gender");
                String gender = (String) getGender.get("value");
                Double getGenderConfidence = (Double) getGender.get("confidence");
                Double genderConfidence = (double) Math.round(getGenderConfidence * 10000) / 100;
                info[1] = gender;
                conf[1] = genderConfidence;

                JSONObject getAge = (JSONObject) getFaceInfo.get("age");
                String age = (String) getAge.get("value");
                Double getAgeConfidence = (Double) getAge.get("confidence");
                Double ageConfidence = (double) Math.round(getAgeConfidence * 10000) / 100;
                info[2] = age;
                conf[2] = ageConfidence;

                JSONObject getPose = (JSONObject) getFaceInfo.get("pose");
                String pose = (String) getPose.get("value");
                Double getPoseConfidence = (Double) getPose.get("confidence");
                Double poseConfidence = (double) Math.round(getPoseConfidence * 10000) / 100;
                info[3] = pose;
                conf[3] = poseConfidence;

                //얼굴좌표 불러오기
                System.out.println(Arrays.toString(info));
                System.out.println(Arrays.toString(conf));

                faceInfos.add(info);
                faceConfidences.add(conf);



                if (info[3].equals("false_face")) {
                    continue;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("얼굴 미감지!");
            alert.setContentText("검사하고자 하는 사진에서 얼굴이 감지되지 않았습니다. 다른 사진을 시도해보세요.");
            alert.showAndWait();

            try {
                Parent nextScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("face-reconize.fxml")));
                Scene scene = new Scene(nextScene);
                Stage primaryStage = (Stage) getSetGo.getScene().getWindow();
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
