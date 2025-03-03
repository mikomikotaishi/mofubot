package bot.ninetail.clients;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.*;

import jakarta.annotation.Nonnull;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import bot.ninetail.structures.clients.ImageboardClient;
import bot.ninetail.system.ConfigLoader;

/**
 * Client for Gelbooru.
 * 
 * @extends ImageboardClient
 */
public class GelbooruClient extends ImageboardClient {
    /**
     * The base URL for Gelbooru.
     */
    private static final String BASE_URL = "https://gelbooru.com/index.php?page=dapi&s=post&q=index&json=1&tags=%s&api_key=%s";

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
     * @throws InterruptedException If the operation is interrupted.
     */
    @Override
    public JsonArray getPosts(@Nonnull String tag1, String tag2) throws IOException, InterruptedException {
        if (getApiKey() == null || getApiKey().isEmpty()) {
            System.err.println("Gelbooru API key missing!");
            throw new IllegalArgumentException("No Gelbooru token found!");
        }

        String tags = tag1;
        if (tag2 != null && !tag2.isEmpty())
            tags += ("+" + tag2);
        
        String url = String.format(BASE_URL, tags, getApiKey());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        System.out.println("Issuing request to Gelbooru for tags: " + tags);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Obtaining response.");
        if (response.statusCode() != 200) {
            System.err.println("Failed to execute HTTP request!");
            throw new IOException("Failed to execute HTTP request");
        }
        System.out.println("Successfully obtained response.");
        String responseBody = response.body();
        
        try (JsonReader jsonReader = Json.createReader(new StringReader(responseBody))) {
            JsonObject jsonResponse = jsonReader.readObject();
            if (!jsonResponse.containsKey("post")) {
                System.err.println("No 'post' key in Gelbooru response");
                return Json.createArrayBuilder().build();
            }
            return jsonResponse.getJsonArray("post");
        }
    }
}
