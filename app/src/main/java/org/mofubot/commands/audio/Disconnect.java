package org.mofubot.commands.audio;

import jakarta.annotation.Nonnull;

import org.mofubot.audio.BotAudio;
import org.mofubot.structures.commands.AudioCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Disconnect implements AudioCommand {
    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Disconnect command invoked.");
        long guildId = event.getGuild().getIdLong();
        BotAudio botAudio = BotAudio.getInstance(guildId);
        botAudio.disconnect();
        event.reply("Disconnected from the voice channel.").queue();
    }
}