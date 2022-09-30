package com.example.faceornamental;

import javafx.fxml.FXML;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.control.Button;

public class FaceDefrag{

    @FXML
    private Button getSetGo;


    public void faceDefrag(){


        String clientId = "Ioc_NlXQCsnnx4k5zacM";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "1PRDknN3bW";//애플리케이션 클라이언트 시크릿값";


        try {
            FaceReconize fcr = new FaceReconize();
            String paramName = "image"; // 파라미터명은 image로 지정
            System.out.println(fcr.getSendToNext());
            String imgFile = fcr.sendToNext;
            File uploadFile = new File(imgFile);
//            String apiURL = "https://openapi.naver.com/v1/vision/celebrity"; // 유명인 얼굴 인식
            String apiURL = "https://openapi.naver.com/v1/vision/face"; // 얼굴 감지
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
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
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
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
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                printInfo(response.toString());

            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void printInfo(String target){
        JSONParser jsonParser = new JSONParser();

        //JSON데이터를 넣어 JSON Object 로 만들어 준다.
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(target);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //faces의 배열을 추출
        JSONArray getFaces = (JSONArray) jsonObject.get("faces");
        int faceLength = getFaces.size();
        System.out.println(faceLength);
        JSONObject getFaceInfo = (JSONObject) getFaces.get(0);

        JSONObject getEmotion = (JSONObject) getFaceInfo.get("emotion");
        String emotion = (String) getEmotion.get("value");
        Double getEmotionConfidence = (Double) getEmotion.get("confidence");
        Double emotionConfidence = (double)Math.round(getEmotionConfidence * 1000000)/10000;
        System.out.println("감정 : " + emotion + " / 확률 : " + emotionConfidence + "%");

        JSONObject getGender = (JSONObject) getFaceInfo.get("gender");
        String gender = (String) getGender.get("value");
        Double getGenderConfidence = (Double) getGender.get("confidence");
        Double genderConfidence =  (double)Math.round(getGenderConfidence * 1000000)/10000;
        System.out.println("성별 : " + gender + " / 확률 : " + genderConfidence + "%");

        JSONObject getAge = (JSONObject) getFaceInfo.get("age");
        String age = (String) getAge.get("value");
        Double getAgeConfidence = (Double) getAge.get("confidence");
        Double ageConfidence =  (double)Math.round(getAgeConfidence * 1000000)/10000;
        System.out.println("나이 : " + age + " / 확률 : " + ageConfidence + "%");

        JSONObject getPose = (JSONObject) getFaceInfo.get("pose");
        String pose = (String) getPose.get("value");
        Double getPoseConfidence = (Double) getPose.get("confidence");
        Double poseConfidence =  (double)Math.round(getPoseConfidence * 1000000)/10000;
        System.out.println("자세 : " + pose + " / 확률 : " + poseConfidence + "%");

        //얼굴좌표 불러오기

        JSONObject getFaceLocate = (JSONObject) getFaceInfo.get("roi");
        long faceStartX = (long) getFaceLocate.get("x");
        long faceStartY = (long) getFaceLocate.get("y");
        long faceWidth = (long) getFaceLocate.get("width");
        long faceHeight = (long) getFaceLocate.get("height");
        System.out.println("얼굴 시작 좌표 : ( " + faceStartX+ " , " + faceStartY + " )");
        System.out.println("얼굴 넓이 : ( " + faceWidth+ " , " + faceHeight + " )");
        long faceEndX = faceStartX + faceWidth;
        long faceEndY = faceStartY + faceHeight;

        JSONObject getElementLocate = (JSONObject) getFaceInfo.get("landmark");

        JSONObject getLeftEye = (JSONObject) getElementLocate.get("leftEye");
        long leftEyeX = (long) getLeftEye.get("x");
        long leftEyeY = (long) getLeftEye.get("y");
        long relativeLeftEyeX = leftEyeX - faceStartX;
        long relativeLeftEyeY = leftEyeY - faceStartY;
        System.out.println("왼쪽 눈 상대좌표 : ( " + relativeLeftEyeX + " , " + relativeLeftEyeY + " )");

        JSONObject getRightEye = (JSONObject) getElementLocate.get("rightEye");
        long rightEyeX = (long) getRightEye.get("x");
        long rightEyeY = (long) getRightEye.get("y");
        long relativeRightEyeX = rightEyeX - faceStartX;
        long relativeRightEyeY = rightEyeY - faceStartY;
        System.out.println("오른쪽 눈 상대좌표 : ( " + relativeRightEyeX + " , " + relativeRightEyeY + " )");

        JSONObject getNose = (JSONObject) getElementLocate.get("nose");
        long noseX = (long) getNose.get("x");
        long noseY = (long) getNose.get("y");
        long relativeNoseX = noseX - faceStartX;
        long relativeNoseY = noseY - faceStartY;
        System.out.println("코 상대좌표 : ( " + relativeNoseX + " , " + relativeNoseY + " )");

        JSONObject getLeftMouth = (JSONObject) getElementLocate.get("leftMouth");
        long leftMouthX = (long) getLeftMouth.get("x");
        long leftMouthY = (long) getLeftMouth.get("y");
        long relativeLeftMouthX = leftMouthX - faceStartX;
        long relativeLeftMouthY = leftMouthY - faceStartY;
        System.out.println("왼쪽 입 상대좌표 : ( " + relativeLeftMouthX + " , " + relativeLeftMouthY + " )");

        JSONObject getRightMouth = (JSONObject) getElementLocate.get("rightMouth");
        long rightMouthX = (long) getRightMouth.get("x");
        long rightMouthY = (long) getRightMouth.get("y");
        long relativeRightMouthX = rightMouthX - faceStartX;
        long relativeRightMouthY = rightMouthY - faceStartY;
        System.out.println("오른쪽 입 상대좌표 : ( " + relativeRightMouthX + " , " + relativeRightMouthY + " )");


    }
}
