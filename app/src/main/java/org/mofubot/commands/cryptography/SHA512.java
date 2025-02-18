package org.mofubot.commands.cryptography;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import org.mofubot.utilities.cryptography.Hash;
import java.security.NoSuchAlgorithmException;

/**
 * Command to hash a message using SHA-512.
 */
public class SHA512 {
    /**
     * Private constructor to prevent instantiation.
     */
    private SHA512() {}

    /**
     * Invokes the command.
     *
     * @param event The event that triggered the command.
     */
    public static void invoke(SlashCommandInteractionEvent event) {
        String message = event.getOption("message").getAsString();
        try {
            String hashedMessage = Hash.hash(message, "SHA-512");
            event.reply("SHA-512 hash: " + hashedMessage).queue();
        } catch (NoSuchAlgorithmException e) {
            event.reply("Error: " + e.getMessage()).queue();
        }
    }
}