package org.mofubot.structures;

import jakarta.annotation.Nonnull;

import okhttp3.OkHttpClient;

public abstract class Client {
    @Nonnull private final String BASE_URL;
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
