package org.mofubot.clients;

import java.io.IOException;

import jakarta.annotation.Nonnull;

import org.mofubot.structures.Client;
import org.mofubot.system.ConfigLoader;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Client for Gelbooru.
 */
public class GelbooruClient extends Client {
    /**
     * The base URL for Gelbooru.
     */
    private static final String BASE_URL = "https://gelbooru.com/index.php?page=dapi&s=post&q=index&json=1&tags=";

    /**
     * Constructs a new Gelbooru client.
     */
    public GelbooruClient() {
        super(BASE_URL, ConfigLoader.getGelbooruToken());
    }
    
    /**
     * Retrieves posts from Gelbooru.
     *
     * @param tag1 The first tag.
     * @param tag2 The second tag.
     * @return The posts.
     * @throws IOException If an error occurs while retrieving the posts.
     */
    public JsonArray getPosts(@Nonnull String tag1, String tag2) throws IOException {
        if (getApiKey() == null || getApiKey().isEmpty()) {
            System.err.println("Gelbooru API key missing!");
            throw new IllegalArgumentException("No Gelbooru token found!");
        }

        String tags = tag1;
        if (tag2 != null && !tag2.isEmpty())
            tags += ("+" + tag2);
        
        String url = BASE_URL + tags + "&api_key" + getApiKey();
        Request request = new Request.Builder().url(url).build();
        System.out.println("Issuing request to Gelbooru for tags: " + tags);
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
