package com.sfu.cmpt470.properties;

import java.util.Properties;

public class Database {
    public static String CONNECTION_URL = "jdbc:postgresql://localhost/projectdb";

    public static String USER_NAME = "projectuser";

    public static String PASSWORD = "password";

    public static Properties getDBProperties(){
        Properties properties = new Properties();
        properties.setProperty("user", USER_NAME);
        properties.setProperty("password",PASSWORD);
        properties.setProperty("ssl","false");

        return properties;
    }
}
