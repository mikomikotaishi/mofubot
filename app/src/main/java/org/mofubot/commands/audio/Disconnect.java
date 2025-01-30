package org.mofubot.commands.audio;

import javax.annotation.Nonnull;

import org.mofubot.audio.*;
import org.mofubot.commands.structures.AudioCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Disconnect implements AudioCommand {
    private Disconnect() {}

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Disconnect command executed.");
        AudioManager manager = event.getGuild().getAudioManager();

        if (manager.isConnected()) {
            manager.closeAudioConnection();
            BotAudio.getInstance().disconnect();
            event.reply("Disconnected from the voice channel.").queue();
        } else
            event.reply("I'm not connected to a voice channel!").queue();
    }
}
