package org.mofubot.clients;

import java.io.IOException;

import jakarta.annotation.Nonnull;

import org.mofubot.structures.Client;
import org.mofubot.system.ConfigLoader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.Request;
import okhttp3.Response;

public class WeatherClient extends Client {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public WeatherClient() {
        super(BASE_URL, ConfigLoader.getWeatherToken());
    }

    public JsonObject getWeather(@Nonnull String location) throws IOException {
        if (getApiKey() == null || getApiKey().isEmpty()) {
            System.err.println("Weather API key missing!");
            throw new IllegalArgumentException("No Weather token found!");
        }

        String URL = BASE_URL + location + "&appid=" + getApiKey() + "&units=metric";
        Request request = new Request.Builder().url(URL).build();
        System.out.println("Issuing request to OpenWeatherMap for location: " + location);
        try (Response response = client.newCall(request).execute()) {
            System.out.println("Obtaining response.");
            if (!response.isSuccessful()) {
                System.err.println("Failed to execute HTTP request!");
                throw new IOException("Failed to execute HTTP request");
            }
            System.out.println("Successfully obtained response.");
            String responseBody = response.body().string();
            return JsonParser.parseString(responseBody).getAsJsonObject().getAsJsonObject("main");
        }
    }
}
