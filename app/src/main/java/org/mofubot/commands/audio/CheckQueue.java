package org.mofubot.commands.audio;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Queue;

import javax.annotation.Nonnull;

import org.mofubot.audio.BotAudio;
import org.mofubot.audio.TrackScheduler;
import org.mofubot.commands.structures.AudioCommand;
import org.mofubot.utilities.calculations.Temporal;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CheckQueue implements AudioCommand {
    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Check queue command invoked.");
        long guildId = event.getGuild().getIdLong();
        BotAudio botAudio = BotAudio.getInstance(guildId);
        Queue<AudioTrack> queue = botAudio.getScheduler().getQueue();
        Iterator<AudioTrack> iterator = queue.iterator();
        int currentIndex = 1;
        while (iterator.hasNext()) {
            AudioTrack track = iterator.next();
            String trackName = track.getInfo().title;
            long songLength = track.getInfo().length;
            event.reply("" + currentIndex++ + ". " + trackName + " (" + Temporal.getFormattedTime(songLength) + ")").queue();
        }
    }
}