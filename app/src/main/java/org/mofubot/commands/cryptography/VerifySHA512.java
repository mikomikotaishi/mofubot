package org.mofubot.commands.cryptography;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.mofubot.utilities.cryptography.Hash;

import java.security.NoSuchAlgorithmException;

/**
 * Command to verify a SHA-512 hash.
 */
public class VerifySHA512 {
    /**
     * Private constructor to prevent instantiation.
     */
    private VerifySHA512() {}

    /**
     * Invokes the command.
     *
     * @param event The event that triggered the command.
     */
    public static void invoke(SlashCommandInteractionEvent event) {
        String message = event.getOption("message").getAsString();
        String hash = event.getOption("hash").getAsString();
        try {
            boolean isValid = Hash.verifyHash(message, hash, "SHA-512");
            event.reply("SHA-512 verification: " + (isValid ? "Valid" : "Invalid")).queue();
        } catch (NoSuchAlgorithmException e) {
            event.reply("Error: " + e.getMessage()).queue();
        }
    }
}