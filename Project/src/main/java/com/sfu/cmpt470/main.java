package com.sfu.cmpt470;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.Properties;

@Path("/hello")
public class main {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        StringBuilder result = new StringBuilder();
        String url = "jdbc:postgresql://localhost/projectdb";
        Properties props = new Properties();
        props.setProperty("user","projectuser");
        props.setProperty("password","password");
        props.setProperty("ssl","false");
        try {
            Connection conn = DriverManager.getConnection(url, props);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM restaurant");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                result.append(resultSet.getString("restaurant_name")).append(" ,");
            }

        } catch (SQLException e) {
            return e.getMessage();
        }
        return result.toString();
    }
}
