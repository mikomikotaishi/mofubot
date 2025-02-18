package org.mofubot.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class AudioPlayerLoadResultHandler implements AudioLoadResultHandler {
    private final MessageChannel textChannel;
    private final AudioPlayer player;
    private final TrackScheduler scheduler;

    public AudioPlayerLoadResultHandler(MessageChannel textChannel, AudioPlayer player, TrackScheduler scheduler) {
        this.textChannel = textChannel;
        this.player = player;
        this.scheduler = scheduler;
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        System.out.println("Loading track: " + track.getInfo().title);
        textChannel.sendMessage("Added to queue: **" + track.getInfo().title + "**").queue();
        scheduler.queue(track);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        System.out.println("Loaidng playlist: " + playlist.getName());
        if (playlist.isSearchResult()) {
            AudioTrack firstTrack = playlist.getTracks().get(0);
            textChannel.sendMessage("Added to queue: **" + firstTrack.getInfo().title + "**").queue();
            scheduler.queue(firstTrack);
        } else {
            for (AudioTrack track : playlist.getTracks())
                scheduler.queue(track);
            textChannel.sendMessage("Added **" + playlist.getTracks().size() + "** tracks to the queue.").queue();
        }
    }

    @Override
    public void noMatches() {
        textChannel.sendMessage("No matches found!").queue();
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        textChannel.sendMessage("Failed to load track: " + exception.getMessage()).queue();
    }
}