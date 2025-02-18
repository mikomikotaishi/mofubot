package org.mofubot.commands.system;

import jakarta.annotation.Nonnull;

import org.mofubot.structures.commands.JDACommand;
import org.mofubot.system.ConfigLoader;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Command to shut down the bot.
 */
public class Shutdown implements JDACommand {
    /**
     * Private constructor to prevent instantiation.
     */
    private Shutdown() {}

    /**
     * Invokes the command.
     *
     * @param event The event that triggered the command.
     * @param instance The JDA instance.
     */
    public static void invoke(@Nonnull SlashCommandInteractionEvent event, @Nonnull JDA instance) {
        System.out.println("Shutdown command attempted.");
        String password = event.getOption("password").getAsString();
        if (password.equals(ConfigLoader.getMasterPassword())) {
            event.reply("Shutting down bot.").queue();
            instance.shutdown();
        } else {
            System.out.println("Attempted (failed) shutdown attempt by " + event.getUser().getGlobalName() + "(" + event.getUser().getId() + ")");
            event.reply("Incorrect shutdown password!").queue();
        }
    }
}