package org.mofubot.structures.commands;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface APICommand {
    static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        throw new UnsupportedOperationException("invoke() must be overriden!");
    }
}