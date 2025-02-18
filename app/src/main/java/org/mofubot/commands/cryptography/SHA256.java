package org.mofubot.commands.cryptography;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import org.mofubot.utilities.cryptography.Hash;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static void invoke(SlashCommandInteractionEvent event) {
        String message = event.getOption("message").getAsString();
        try {
            String hashedMessage = Hash.hash(message, "SHA-256");
            event.reply("SHA-256 hash: " + hashedMessage).queue();
        } catch (NoSuchAlgorithmException e) {
            event.reply("Error: " + e.getMessage()).queue();
        }
    }
}