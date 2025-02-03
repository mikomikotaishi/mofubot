package org.mofubot.clients;

import java.io.IOException;

import javax.annotation.Nonnull;

import org.mofubot.structures.Client;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import okhttp3.Request;
import okhttp3.Response;

public class DanbooruClient extends Client {
    private static final String BASE_URL = "https://danbooru.donmai.us/posts.json?tags=";

    public DanbooruClient() {
        super(BASE_URL);
    }
    
    public JsonArray getPosts(@Nonnull String tag1, String tag2) throws IOException {
        String tags = tag1;
        if (tag2 != null && !tag2.isEmpty())
            tags += ("+" + tag2);
        
        String url = BASE_URL + tags;
        Request request = new Request.Builder().url(url).build();
        System.out.println("Issuing request to Danbooru for tags: " + tags);
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
