package org.mofubot.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties PROPERTIES = new Properties();
    private static String botToken;
    private static String masterPassword;
    private static String weatherToken;

    static {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Unable to find config.properties!");
                throw new FileNotFoundException("Unable to find config.properties!");
            }
            PROPERTIES.load(input);
            botToken = PROPERTIES.getProperty("BOT_TOKEN");
            masterPassword = PROPERTIES.getProperty("MASTER_PASSWORD");
            weatherToken = PROPERTIES.getProperty("WEATHER_TOKEN");

            if (botToken == null) {
                System.err.println("No bot token found!");
                throw new IllegalArgumentException("No bot token found!");
            }
            if (masterPassword == null) {
                System.err.println("No shutdown password found!");
                throw new IllegalArgumentException("No shutdown password found!");
            }
            if (weatherToken == null)
                System.err.println("No weather token found!");
                
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

    public static void reloadConfig() {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Unable to find config.properties!");
                throw new FileNotFoundException("Unable to find config.properties!");
            }
            PROPERTIES.load(input);
            weatherToken = PROPERTIES.getProperty("WEATHER_TOKEN");
            
            if (weatherToken == null)
                System.err.println("No weather token found!");
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}