package org.mofubot.commands.structures;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface JDAControl {
    static void invoke(@Nonnull SlashCommandInteractionEvent event, @Nonnull JDA instance) {
        throw new UnsupportedOperationException("invoke() must be overriden!");
    }
}
