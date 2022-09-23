package com.example.faceornamental;

import javafx.fxml.FXML;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javafx.scene.control.Button;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FaceDefrag extends FaceReconize{

    @FXML
    private Button getSetGo;


    FaceReconize faceReco = new FaceReconize();
    public void faceDefrag(){
        StringBuffer reqStr = new StringBuffer();
        String clientId = "Ioc_NlXQCsnnx4k5zacM";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "1PRDknN3bW";//애플리케이션 클라이언트 시크릿값";


        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = "src\\main\\resources\\com\\example\\faceornamental\\images\\seagull.jpg";
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
    public void printInfo(String responseJSON){
        String target = responseJSON;

        try {
            JSONParser jsonParse = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParse.parse(target);
            JSONArray parsedTarget = (JSONArray) jsonObj.get("info");
            System.out.println(parsedTarget.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
