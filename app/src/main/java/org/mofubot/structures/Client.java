package org.mofubot.structures;

import okhttp3.OkHttpClient;

public abstract class Client {
    private final String BASE_URL;
    protected final OkHttpClient client = new OkHttpClient();

    protected Client(String baseUrl) {
        this.BASE_URL = baseUrl;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }
}
