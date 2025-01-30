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

    public AudioPlayerLoadResultHandler(MessageChannel textChannel, AudioPlayer player) {
        this.textChannel = textChannel;
        this.player = player;
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        textChannel.sendMessage("Now playing: " + track.getInfo().title).queue();
        player.playTrack(track);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        AudioTrack firstTrack = playlist.getTracks().get(0); // Play the first track
        textChannel.sendMessage("Now playing: " + firstTrack.getInfo().title).queue();
        player.playTrack(firstTrack);
    }

    @Override
    public void noMatches() {
        System.err.println("No matches found for query.");
        textChannel.sendMessage("No matches found!").queue();
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        System.err.println("Failed to load track.");
        textChannel.sendMessage("Failed to load track: " + exception.getMessage()).queue();
    }
}