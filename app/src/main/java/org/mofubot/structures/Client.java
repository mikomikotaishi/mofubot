package org.mofubot.structures;

import okhttp3.OkHttpClient;

public abstract class Client {
    protected final OkHttpClient client = new OkHttpClient();
}
