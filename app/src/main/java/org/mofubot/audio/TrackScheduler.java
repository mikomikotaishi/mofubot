package org.mofubot.audio;

import java.util.LinkedList;
import java.util.Queue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class TrackScheduler extends AudioEventAdapter {
    private final BotAudio botAudio;
    private final AudioPlayer player;
    private final Queue<AudioTrack> queue;

    public TrackScheduler(BotAudio botAudio, AudioPlayer player) {
        this.botAudio = botAudio;
        this.player = player;
        this.queue = new LinkedList<>();
    }

    public void queue(AudioTrack track) {
        if (!player.startTrack(track, true)) {
            System.out.println("Queued track: " + track.getInfo().title);
            queue.offer(track);
        } else {
            System.out.println("Now playing: " + track.getInfo().title);
        }
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            AudioTrack nextTrack = queue.poll();
            if (nextTrack != null) {
                System.out.println("Current song ended. Beginning next song...");
                player.startTrack(nextTrack, false);
            } else {
                System.out.println("Song queue empty.");
                MessageChannel textChannel = botAudio.getTextChannel();
                if (textChannel != null)
                    textChannel.sendMessage("Queue is empty. Use /play to queue new songs.").queue();
            }
        }
    }

    public void skip() {
        player.stopTrack();
    }

    public void clear() {
        queue.clear();
    }

    public Queue<AudioTrack> getQueue() {
        return queue;
    }
}