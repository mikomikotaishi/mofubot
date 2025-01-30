package org.mofubot.utilities;

import java.io.IOException;

import org.mofubot.system.ConfigLoader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherClient {
    private final OkHttpClient client = new OkHttpClient();
    private final String API_KEY = ConfigLoader.getWeatherToken();

    public JsonObject getWeather(String location) throws IOException {
        if (API_KEY == null || API_KEY.isEmpty()) {
            System.err.println("Weather API key missing!");
            throw new IllegalArgumentException("No weather token found!");
        }

        String URL = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY + "&units=metric";
        Request request = new Request.Builder().url(URL).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("Failed to execute HTTP request!");
                throw new IOException("Failed to execute HTTP request");
            }
            String responseBody = response.body().string();
            return JsonParser.parseString(responseBody).getAsJsonObject().getAsJsonObject("main");
        }
    }
}
