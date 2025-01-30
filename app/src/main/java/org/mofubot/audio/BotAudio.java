package org.mofubot.audio;

import java.util.Queue;
import java.util.LinkedList;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;

import dev.lavalink.youtube.YoutubeAudioSourceManager;

import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class BotAudio {
    private static BotAudio INSTANCE; 

    private AudioManager audioManager;
    private final AudioPlayer player;
    private final AudioPlayerManager manager;
    private MessageChannel textChannel;
    private AudioChannel voiceChannel;
    private final Queue<String> trackQueue = new LinkedList<>();
    private boolean activated = false;

    private BotAudio() {
        this.manager = new DefaultAudioPlayerManager();
        this.manager.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
        this.manager.registerSourceManager(new YoutubeAudioSourceManager(true));
        this.player = manager.createPlayer();
    }

    public static synchronized BotAudio getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BotAudio();
        return INSTANCE;
    }

    public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    public void setTextChannel(MessageChannel textChannel) {
        this.textChannel = textChannel;
    }

    public void setVoiceChannel(AudioChannel voiceChannel) {
        this.voiceChannel = voiceChannel;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public AudioPlayer getAudioPlayer() {
        return player;
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return manager;
    }

    public MessageChannel getTextChannel() {
        return textChannel;
    }

    public AudioChannel getVoiceChannel() {
        return voiceChannel;
    }

    public Queue<String> getTrackQueue() {
        return trackQueue;
    }

    public void clear() {
        trackQueue.clear();
    }

    public boolean activityState() {
        return activated;
    }

    public void activate() {
        activated = true;
    }

    public void disconnect() {
        textChannel = null;
        voiceChannel = null;
        activated = false;
        clear();
    }
}
