package org.mofubot.commands.general;

import jakarta.annotation.Nonnull;

import org.mofubot.structures.commands.APICommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Command to uwuify text.
 */
public class Uwuify implements APICommand {
    /**
     * Private constructor to prevent instantiation.
     */
    private Uwuify() {}

    /**
     * Invokes the command.
     *
     * @param event The event that triggered the command.
     */
    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        
    }
}


