package org.mofubot.commands.imageboard;

import java.io.IOException;

import jakarta.annotation.Nonnull;

import org.mofubot.clients.DanbooruClient;
import org.mofubot.structures.commands.APICommand;
import org.mofubot.utilities.RandomNumberGenerator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Danbooru implements APICommand {
    private Danbooru() {}

    private static final DanbooruClient danbooruClient = new DanbooruClient();

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Danbooru command invoked");
        if (danbooruClient.getApiKey() == null) {
            System.err.println("Failed to invoke Danbooru command due to missing API token.");
            event.reply("Sorry, Danbooru API token was not provided, I cannot retrieve anything.");
        }
        String tag1 = event.getOption("tag1").getAsString();
        String tag2 = event.getOption("tag2") != null ? event.getOption("tag2").getAsString() : null;
        try {
            System.out.println("Attempting to retrieve posts for tags: " + tag1 + (tag2 != null ? ", " + tag2 : ""));
            JsonArray posts = danbooruClient.getPosts(tag1, tag2);
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
