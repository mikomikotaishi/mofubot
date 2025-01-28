package org.mofubot.utilities;

import java.util.Map;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class ResponseHandler {
    public static void handleMessage(String content, MessageChannel textChannel) {
        System.out.println("Parsing for responses");
        System.out.println("Message: " + content);
        for (Map.Entry<String, String> entry: Responses.getInstance().getResponses().entrySet()) {
            System.out.println("Checking response key: " + entry.getKey());
            if (content.toLowerCase().contains(entry.getKey())) {
                System.out.println("Match found! Sending: " + entry.getValue());
                textChannel.sendMessage(entry.getValue()).queue();
                break;
            }
        }
    }
}
