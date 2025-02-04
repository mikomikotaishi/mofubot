package org.mofubot.commands.imageboard;

import java.io.IOException;

import jakarta.annotation.Nonnull;

import org.mofubot.clients.GyateBooruClient;
import org.mofubot.structures.commands.APICommand;
import org.mofubot.system.ConfigLoader;
import org.mofubot.utilities.calculations.RandomNumberGenerator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GyateBooru implements APICommand {
    private GyateBooru() {}

    private static final GyateBooruClient gyatebooruClient = new GyateBooruClient();

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Gyate Booru command invoked");
        if (gyatebooruClient.getApiKey() == null) {
            System.err.println("Failed to invoke Gyate Booru command due to missing API token.");
            event.reply("Sorry, Gyate Booru API token was not provided, I cannot retrieve anything.");
        }
        String tag1 = event.getOption("tag1").getAsString();
        String tag2 = event.getOption("tag2") != null ? event.getOption("tag2").getAsString() : null;
        try {
            System.out.println("Attempting to retrieve posts for tags: " + tag1 + (tag2 != null ? ", " + tag2 : ""));
            JsonArray posts = gyatebooruClient.getPosts(tag1, tag2);
            if (posts.size() > 0) {
                int randomIndex = RandomNumberGenerator.generateRandomNumber(posts.size());
                JsonObject post = posts.get(randomIndex).getAsJsonObject();
                String imageUrl = post.get("file_url").getAsString();
                event.reply(imageUrl);
            } else 
                event.reply("No posts found for tags " + tag1 + (tag2 != null ? " and " + tag2 : "") + ".");
        } catch (IOException e) {
            System.err.println("Failed to retrieve posts for tags: " + tag1 + (tag2 != null ? ", " + tag2 : ""));
            event.reply("Error retrieving posts for tags: " + tag1 + (tag2 != null ? " and " + tag2 : "") + ".");
        }
    }
}
