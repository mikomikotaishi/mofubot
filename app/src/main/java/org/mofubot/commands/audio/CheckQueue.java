package org.mofubot.commands.audio;

import java.util.Iterator;
import java.util.Queue;

import jakarta.annotation.Nonnull;

import org.mofubot.audio.BotAudio;
import org.mofubot.structures.commands.AudioCommand;
import org.mofubot.utilities.calculations.Temporal;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CheckQueue implements AudioCommand {
    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Check queue command invoked.");
        long guildId = event.getGuild().getIdLong();
        BotAudio botAudio = BotAudio.getInstance(guildId);
        Queue<AudioTrack> queue = botAudio.getScheduler().getQueue();
        if (queue.isEmpty()) {
            System.out.println("Music queue empty.");
            event.reply("Music queue empty.").queue();
            return;
        }
        Iterator<AudioTrack> iterator = queue.iterator();
        int currentIndex = 1;
        StringBuilder fullList = new StringBuilder();
        System.out.println("Beginning to parse queue.");
            while (iterator.hasNext()) {
                AudioTrack track = iterator.next();
                String trackName = track.getInfo().title;
                long songLength = track.getInfo().length;
                fullList.append("" + currentIndex++ + ". " + trackName + " (" + Temporal.getFormattedTime(songLength) + ")\n");
            }
        System.out.println("Queue completed parsing.");
        System.out.println(fullList);
        event.reply(fullList.toString()).queue();
    }
}