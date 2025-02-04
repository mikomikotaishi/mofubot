package org.mofubot.clients;

import java.io.IOException;

import javax.annotation.Nonnull;

import org.mofubot.structures.Client;
import org.mofubot.system.ConfigLoader;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import okhttp3.Request;
import okhttp3.Response;

public class GyateBooruClient extends Client {
    private static final String BASE_URL = "https://gyate.net"; // Currently does nothing

    public GyateBooruClient() {
        super(BASE_URL, ConfigLoader.getGyateBooruToken());
    }

    public JsonArray getPosts(@Nonnull String tag1, String tag2) throws IOException {
        if (getApiKey() == null || getApiKey().isEmpty()) {
            System.err.println("Gyate Booru API key missing!");
            throw new IllegalArgumentException("No Gyate Booru token found!");
        }

        String tags = tag1;
        if (tag2 != null && !tag2.isEmpty())
            tags += ("+" + tag2);
        
        String url = BASE_URL + tags + "&api_key" + getApiKey();
        Request request = new Request.Builder().url(url).build();
        System.out.println("Issuing request to Gyate Booru for tags: " + tags);
        try (Response response = client.newCall(request).execute()) {
            System.out.println("Obtaining response.");
            if (!response.isSuccessful()) {
                System.err.println("Failed to execute HTTP request!");
                throw new IOException("Failed to execute HTTP request");
            }
            System.out.println("Successfully obtained response.");
            String responseBody = response.body().string();
            return JsonParser.parseString(responseBody).getAsJsonArray();
        }
    }
}
