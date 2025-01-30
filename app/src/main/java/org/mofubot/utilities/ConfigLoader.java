package org.mofubot.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();
    private static String botToken;
    private static String shutdownPassword;
    private static String weatherToken;

    static {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Unable to find config.properties!");
                throw new FileNotFoundException("Unable to find config.properties!");
            }
            properties.load(input);
            botToken = properties.getProperty("BOT_TOKEN");
            shutdownPassword = properties.getProperty("SHUTDOWN_PASSWORD");
            weatherToken = properties.getProperty("WEATHER_TOKEN");

            if (botToken == null) {
                System.err.println("No bot token found!");
                throw new IllegalArgumentException("No bot token found!");
            }
            if (shutdownPassword == null) {
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

    public static String getShutdownPassword() {
        return shutdownPassword;
    }

    public static String getWeatherToken() {
        return weatherToken;
    }
}