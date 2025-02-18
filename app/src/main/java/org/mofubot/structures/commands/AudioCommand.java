package org.mofubot.structures.commands;

import jakarta.annotation.Nonnull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface AudioCommand {
    static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        throw new UnsupportedOperationException("invoke() must be overriden!");
    }
}