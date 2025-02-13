package org.mofubot.audio;

import java.util.LinkedList;
import java.util.Queue;

import org.mofubot.utilities.Temporal;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class TrackScheduler extends AudioEventAdapter {
    private final BotAudio botAudio;
    private final AudioPlayer player;
    private final Queue<AudioTrack> queue;
    MessageChannel textChannel = botAudio.getTextChannel();

    private void sendNowPlayingMessage(AudioTrack track) {
        String trackInfo = track.getInfo().title + " (" + Temporal.getFormattedTime(track.getInfo().length) + ")";
        if (textChannel != null)
            textChannel.sendMessage("Now playing: **" + trackInfo + "**").queue();
    }

    public TrackScheduler(BotAudio botAudio, AudioPlayer player) {
        this.botAudio = botAudio;
        this.player = player;
        this.queue = new LinkedList<>();
    }

    public void queue(AudioTrack track) {
        String trackInfo = track.getInfo().title + " (" + Temporal.getFormattedTime(track.getInfo().length) + ")";
        if (!player.startTrack(track, true)) {
            System.out.println("Queued track: " + track.getInfo().title);
            queue.offer(track);
            if (textChannel != null)
                textChannel.sendMessage("Queued track: **" + trackInfo + "**").queue();
        } else {
            System.out.println("Now playing: " + track.getInfo().title);
            sendNowPlayingMessage(track);
        }
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            AudioTrack nextTrack = queue.poll();
            if (nextTrack != null) {
                System.out.println("Current song ended. Beginning next song...");
                player.startTrack(nextTrack, false);
                sendNowPlayingMessage(nextTrack);
            } else {
                System.out.println("Song queue empty.");
                MessageChannel textChannel = botAudio.getTextChannel();
                if (textChannel != null)
                    textChannel.sendMessage("Queue is empty. Use /play to queue new songs.").queue();
            }
        }
    }

    public void skip() {
        AudioTrack nextTrack = queue.poll();
        if (nextTrack != null) {
            System.out.println("Skipping to next track...");
            player.startTrack(nextTrack, false);
        } else {
            System.out.println("No more tracks to skip.");
            player.stopTrack();
        }
    }

    public void clear() {
        queue.clear();
    }

    public Queue<AudioTrack> getQueue() {
        return queue;
    }
}