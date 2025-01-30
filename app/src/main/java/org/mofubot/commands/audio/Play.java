package org.mofubot.commands.audio;

import javax.annotation.Nonnull;

import org.mofubot.audio.*;
import org.mofubot.commands.structures.AudioCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Play implements AudioCommand {
    private Play() {}

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Play command executed.");
        String query = event.getOption("query").getAsString();
        if (event.getMember().getVoiceState().getChannel() == null) {
            event.reply("You need to be in a voice channel to use this command!").queue();
            return;
        }
        event.reply("Queueing song.").queue();
        if (!BotAudio.getInstance().activityState()) {
            BotAudio.getInstance().setTextChannel(event.getChannel());
            BotAudio.getInstance().setVoiceChannel(event.getMember().getVoiceState().getChannel());
            BotAudio.getInstance().activate();
        }
        BotAudio.getInstance().setAudioManager(event.getGuild().getAudioManager());
        BotAudio.getInstance().getAudioManager().setSendingHandler(new AudioPlayerSendHandler(BotAudio.getInstance().getAudioPlayer()));
        BotAudio.getInstance().getAudioManager().openAudioConnection(BotAudio.getInstance().getVoiceChannel());
        BotAudio.getInstance().getAudioPlayerManager().loadItem("ytsearch:" + query, new AudioPlayerLoadResultHandler(BotAudio.getInstance().getTextChannel(), BotAudio.getInstance().getAudioPlayer()));
    }
}
