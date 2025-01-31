package org.mofubot.audio;

import java.util.HashMap;
import java.util.Map;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;

import dev.lavalink.youtube.YoutubeAudioSourceManager;

import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class BotAudio {
    private static final Map<Long, BotAudio> instances = new HashMap<>();
    private final AudioPlayerManager manager;
    private final AudioPlayer player;
    private final TrackScheduler scheduler;
    private AudioManager audioManager;
    private MessageChannel textChannel;
    private AudioChannel voiceChannel;
    private boolean activated = false;

    private BotAudio() {
        this.manager = new DefaultAudioPlayerManager();
        this.manager.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
        this.manager.registerSourceManager(new YoutubeAudioSourceManager(true));
        this.player = manager.createPlayer();
        this.scheduler = new TrackScheduler(this, player);
        player.addListener(scheduler);
    }

    public static BotAudio getInstance(long guildId) {
        return instances.computeIfAbsent(guildId, k -> new BotAudio());
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

    public AudioPlayer getAudioPlayer() {
        return player;
    }

    public AudioPlayerManager getAudioPlayerManager() {
        return manager;
    }

    public TrackScheduler getScheduler() {
        return scheduler;
    }

    public MessageChannel getTextChannel() {
        return textChannel;
    }

    public AudioChannel getVoiceChannel() {
        return voiceChannel;
    }

    public boolean isActive() {
        return activated;
    }

    public void activate() {
        activated = true;
    }

    public void disconnect() {
        activated = false;
        scheduler.clear();
        player.stopTrack();
        if (audioManager != null)
            audioManager.closeAudioConnection();
        textChannel = null;
        voiceChannel = null;
    }
}