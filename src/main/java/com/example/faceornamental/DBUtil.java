package com.example.faceornamental;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DBUtil {

    static ArrayList<String> results = new ArrayList<>();

    public static ArrayList<String> fetchDB(String columnName) {
        // Connection 객체를 자동완성으로 import할 때는 com.mysql.connection이 아닌
        // java 표준인 java.sql.Connection 클래스를 import해야 한다.
        results = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
            String url = "jdbc:mysql://localhost/faceornamental";

            // @param  getConnection(url, userName, password);
            // @return Connection
            conn = DriverManager.getConnection(url, "root", "");

            stmt = conn.createStatement();

            String sql = "SELECT " + columnName +" FROM faceUser";

            rs = stmt.executeQuery(sql);

            while(rs.next()){
                String dump = rs.getString(columnName);
                results.add(dump);
            }

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
        return results;
    }

    public static void registerDB(String userId, String userPw, String regDate, String nowCeleb, String nowGender, String nowAge) {
        // Connection 객체를 자동완성으로 import할 때는 com.mysql.connection이 아닌
        // java 표준인 java.sql.Connection 클래스를 import해야 한다.
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
            String url = "jdbc:mysql://localhost/faceornamental";

            // @param  getConnection(url, userName, password);
            // @return Connection
            conn = DriverManager.getConnection(url, "root", "");

            String sql = "INSERT INTO faceuser (userId, userPw, userRegDate, userNowCeleb, userNowGender, userNowAge) VALUES (?,?,?,null,null,null)";

            try{
                System.out.println(conn.prepareStatement(sql));
                pstmt = conn.prepareStatement(sql);
                System.out.println(userId);
                System.out.println(userPw);
                pstmt.setString(1, userId);
                pstmt.setString(2, userPw);
                pstmt.setString(3, regDate);

                System.out.println(pstmt);

                pstmt.executeUpdate();

                System.out.println("삽입 성공!");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("환영합니다. "+ userId + " 님 ");
                alert.setHeaderText("가입 성공");
                alert.setContentText("정상적으로 가입되었습니다. 메인으로 돌아가 로그인 해주세요.");
                alert.showAndWait();


            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("삽입 실패 ㅠ");
            }

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void updateDB(String columnName, String loginUserId, String inputValue){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
            String url = "jdbc:mysql://localhost/faceornamental";

            // @param  getConnection(url, userName, password);
            // @return Connection
            conn = DriverManager.getConnection(url, "root", "");

            String sql = "UPDATE faceuser SET " + columnName +" = ? WHERE userId = ?";

            try{
                System.out.println(conn.prepareStatement(sql));
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, inputValue);
                pstmt.setString(2, loginUserId);

                System.out.println(pstmt);

                pstmt.executeUpdate();

                System.out.println("삽입 성공!");


            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("삽입 실패 ㅠ");
            }

        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
    }
}
