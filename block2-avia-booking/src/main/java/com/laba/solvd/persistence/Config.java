package com.laba.solvd.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String driver;
    public static String url;
    public static String userName;
    public static String password;
    public static Integer poolSize;

    static {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream =
                    new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            userName = properties.getProperty("username");
            password = properties.getProperty("password");
            poolSize = Integer.valueOf(properties.getProperty("poolSize"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
