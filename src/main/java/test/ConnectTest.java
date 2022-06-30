package test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //DB接続用定数
    	final String URL = "jdbc:mysql://rivalsofaether/user";
    	final String USER = "ytak";
    	final String PASS = "af3g42sl";

        try {
            //MySQL に接続する
        	Class.forName( "com.mysql.jdbc.Driver" ).getDeclaredConstructor().newInstance();
            //データベースに接続
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            // データベースに対する処理
            System.out.println("データベースに接続に成功");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}