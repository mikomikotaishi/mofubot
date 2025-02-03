package org.mofubot.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class ResponseHandler {
    private ResponseHandler() {};

    private static Map<String, String> RESPONSES = new HashMap<>();

    static {
        Path filePath = Paths.get("responses.json");
        if (Files.exists(filePath)) {
            System.out.println("Loading responses.json");
            try {
                String content = new String(Files.readAllBytes(Paths.get("responses.json")));
                JsonObject responsesJSON = JsonParser.parseString(content).getAsJsonObject();
                for (String key: responsesJSON.keySet()) {
                    System.out.println("Loading response for key " + key);
                    RESPONSES.put(key, responsesJSON.get(key).getAsString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("responses.json not found. Skipping loading responses.");
    }

    public static void handleMessage(String content, MessageChannel textChannel) {
        System.out.println("Parsing for responses");
        System.out.println("Message: " + content);
        for (Map.Entry<String, String> entry: RESPONSES.entrySet()) {
            System.out.println("Checking response key: " + entry.getKey());
            if (content.toLowerCase().contains(entry.getKey())) {
                System.out.println("Match found! Sending: " + entry.getValue());
                textChannel.sendMessage(entry.getValue()).queue();
                break;
            }
        }
    }
}
