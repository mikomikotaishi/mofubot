package org.mofubot.commands.system;

import jakarta.annotation.Nonnull;

import org.mofubot.core.ResponseHandler;
import org.mofubot.structures.commands.JDACommand;
import org.mofubot.system.ConfigLoader;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Command to reload the response files.
 */
public class ReloadResponses implements JDACommand {
    /**
     * Private constructor to prevent instantiation.
     */
    private ReloadResponses() {}

    /**
     * Invokes the command.
     *
     * @param event The event that triggered the command.
     * @param instance The JDA instance.
     */
    public static void invoke(@Nonnull SlashCommandInteractionEvent event, @Nonnull JDA instance) {
        System.out.println("Config reload command attempted.");
        String password = event.getOption("password").getAsString();
        if (password.equals(ConfigLoader.getMasterPassword())) {
            event.reply("Reloading response files.").queue();
            ResponseHandler.reloadResponses();
        } else {
            System.out.println("Attempted (failed) response reload attempt by " + event.getUser().getGlobalName() + "(" + event.getUser().getId() + ")");
            event.reply("Incorrect master password!").queue();
        }
    }
}