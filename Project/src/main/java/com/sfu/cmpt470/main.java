package com.sfu.cmpt470;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.Properties;
import org.postgresql.*;

@Path("/hello")
public class main {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        String url = "jdbc:postgresql://localhost/projectdb";
        Properties props = new Properties();
        props.setProperty("user","projectuser");
        props.setProperty("password","password");
        props.setProperty("ssl","false");
        try {
            Connection conn = DriverManager.getConnection(url, props);
            PreparedStatement statement = conn.prepareStatement(String.format("CREATE TABLE IF NOT EXISTS table1 (something int)"));
            statement.executeUpdate();
            System.out.println("success!");
            statement = conn.prepareStatement("INSERT INTO table1 values (1)");
            statement.execute();
            statement = conn.prepareStatement("SELECT * FROM table1");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                System.out.println(String.format("There is one thing in the table which is %d",resultSet.getInt("something")));
            }

        } catch (SQLException e) {
            return e.getMessage();
        }
        return "DB is on";
    }
//
//    public static void main(String[] args) {
//        String url = "jdbc:postgresql://localhost/projectdb";
//        Properties props = new Properties();
//        props.setProperty("user","vagrant");
//        props.setProperty("password","vagrant");
//        props.setProperty("ssl","false");
//        try {
//            Connection conn = DriverManager.getConnection(url, props);
//            PreparedStatement statement = conn.prepareStatement(String.format("CREATE TABLE IF NOT EXISTS table1 (something int)"));
//            statement.executeUpdate();
//            System.out.println("success!");
//            statement = conn.prepareStatement("INSERT INTO table1 values (1)");
//            statement.execute();
//            statement = conn.prepareStatement("SELECT * FROM table1");
//            ResultSet resultSet = statement.executeQuery();
//            while(resultSet.next()){
//                System.out.println(String.format("There is one thing in the table which is %d",resultSet.getInt("something")));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
