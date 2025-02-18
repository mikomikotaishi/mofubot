package org.mofubot.commands.audio;

import jakarta.annotation.Nonnull;

import org.mofubot.audio.*;
import org.mofubot.structures.commands.AudioCommand;

import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Play implements AudioCommand {
    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Play command invoked.");
        if (event.getMember().getVoiceState() == null || !event.getMember().getVoiceState().inAudioChannel()) {
            event.reply("You need to be in a voice channel to use this command!").queue();
            return;
        }
        long guildId = event.getGuild().getIdLong();
        BotAudio botAudio = BotAudio.getInstance(guildId);
        AudioManager audioManager = event.getGuild().getAudioManager();

        botAudio.setAudioManager(audioManager);
        botAudio.setTextChannel(event.getChannel());
        AudioChannel userChannel = event.getMember().getVoiceState().getChannel();

        if (!botAudio.isActive()) {
            System.out.println("Bot audio currently inactive. Activating...");
            botAudio.setVoiceChannel(userChannel);
            botAudio.activate();
            audioManager.openAudioConnection(userChannel);
            audioManager.setSendingHandler(new AudioPlayerSendHandler(botAudio.getAudioPlayer()));
        } else if (!botAudio.getVoiceChannel().equals(userChannel)) {
            System.out.println("Voice channel and user channel not the same!");
            botAudio.setVoiceChannel(userChannel);
            audioManager.openAudioConnection(userChannel);
        }

        String query = event.getOption("query").getAsString();
        String identifier = query.startsWith("http") ? query : "ytsearch:" + query;

        event.reply("Queueing...").queue();
        botAudio.getAudioPlayerManager().loadItem(identifier, 
            new AudioPlayerLoadResultHandler(botAudio.getTextChannel(), botAudio.getAudioPlayer(), botAudio.getScheduler()));
    }
}