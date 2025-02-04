package org.mofubot.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties PROPERTIES = new Properties();
    // System
    private static String botToken;
    private static String masterPassword;

    // General
    private static String weatherToken;

    // Imageboards
    private static String danbooruToken;
    private static String e621Token;
    private static String gelbooruToken;
    private static String gyatebooruToken;
    private static String rule34Token;


    static {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Unable to find config.properties!");
                throw new FileNotFoundException("Unable to find config.properties!");
            }
            PROPERTIES.load(input);
            // System
            botToken = PROPERTIES.getProperty("BOT_TOKEN");
            masterPassword = PROPERTIES.getProperty("MASTER_PASSWORD");
            
            // General
            weatherToken = PROPERTIES.getProperty("WEATHER_TOKEN");

            // Imageboards
            danbooruToken = PROPERTIES.getProperty("DANBOORU_TOKEN");
            e621Token = PROPERTIES.getProperty("E621_TOKEN");
            gelbooruToken = PROPERTIES.getProperty("GELBOORU_TOKEN");
            gyatebooruToken = PROPERTIES.getProperty("GYATEBOORU_TOKEN");
            rule34Token = PROPERTIES.getProperty("RULE34_TOKEN");


            if (botToken == null) {
                System.err.println("No bot token found!");
                throw new IllegalArgumentException("No bot token found!");
            }
            if (masterPassword == null) {
                System.err.println("No shutdown password found!");
                throw new IllegalArgumentException("No shutdown password found!");
            }
            if (weatherToken == null)
                System.err.println("No Weather token found!");
            if (danbooruToken == null)
                System.err.println("No Danbooru token found!");
            if (e621Token == null)
                System.err.println("No e621 token found!");
            if (gelbooruToken == null)
                System.err.println("No Gelbooru token found!");
            if (gyatebooruToken == null)
                System.err.println("No Gyate Booru token found!");
            if (rule34Token == null)
                System.err.println("No Rule34 token found!");
            
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBotToken() {
        return botToken;
    }

    public static String getMasterPassword() {
        return masterPassword;
    }

    public static String getWeatherToken() {
        return weatherToken;
    }

    public static String getDanbooruToken() {
        return danbooruToken;
    }

    public static String getE621Token() {
        return e621Token;
    }

    public static String getGelbooruToken() {
        return gelbooruToken;
    }

    public static String getGyateBooruToken() {
        return gyatebooruToken;
    }

    public static String getRule34Token() {
        return rule34Token;
    }

    public static void reloadConfig() {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Unable to find config.properties!");
                throw new FileNotFoundException("Unable to find config.properties!");
            }
            PROPERTIES.load(input);
            weatherToken = PROPERTIES.getProperty("WEATHER_TOKEN");
            danbooruToken = PROPERTIES.getProperty("DANBOORU_TOKEN");
            e621Token = PROPERTIES.getProperty("E621_TOKEN");
            gelbooruToken = PROPERTIES.getProperty("GELBOORU_TOKEN");
            gyatebooruToken = PROPERTIES.getProperty("GYATEBOORU_TOKEN");
            rule34Token = PROPERTIES.getProperty("RULE34_TOKEN");
            
            if (weatherToken == null)
                System.err.println("No weather token found!");
            if (danbooruToken == null)
                System.err.println("No Danbooru token found!");
            if (e621Token == null)
                System.err.println("No e621 token found!");
            if (gelbooruToken == null)
                System.err.println("No Gelbooru token found!");
            if (gyatebooruToken == null)
                System.err.println("No Gyate Booru token found!");
            if (rule34Token == null)
                System.err.println("No Rule34 token found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}