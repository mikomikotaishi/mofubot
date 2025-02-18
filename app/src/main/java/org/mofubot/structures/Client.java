package org.mofubot.structures;

import jakarta.annotation.Nonnull;

import okhttp3.OkHttpClient;

/**
 * Abstract class for clients.
 * This class provides a base structure for clients that interact with external services.
 */
public abstract class Client {
    /**
     * The base URL of the client.
     */
    @Nonnull private final String BASE_URL;
    /**
     * The API key of the client.
     */
    @Nonnull private final String API_KEY;
    protected final OkHttpClient client = new OkHttpClient();

    protected Client(@Nonnull String baseUrl, @Nonnull String apiKey) {
        this.BASE_URL = baseUrl;
        this.API_KEY = apiKey;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public String getApiKey() {
        return API_KEY;
    }
}
