/*
 * This source file was generated by the Gradle 'init' task
 */
package org.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;
import javax.security.auth.login.LoginException;

import org.bot.audio.*;
import org.bot.utilities.*;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Widget.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Mofubot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, InterruptedException {
        
        Properties properties = new Properties();
        String BOT_TOKEN = null;

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (input == null)
                System.err.println("Unable to find config.properties!");
            properties.load(input);
            BOT_TOKEN = properties.getProperty("BOT_TOKEN");
            if (BOT_TOKEN == null) {
                System.err.println("No bot token found!");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        EnumSet<GatewayIntent> INTENTS = EnumSet.of(
            GatewayIntent.GUILD_MEMBERS,
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.GUILD_VOICE_STATES,
            GatewayIntent.MESSAGE_CONTENT
        );

        JDA api = JDABuilder.createDefault(BOT_TOKEN, INTENTS)
            .addEventListeners(new Mofubot())
            .build()
            .awaitReady();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        MessageChannel textChannel = event.getChannel();
        Member member = event.getMember();
        User author = message.getAuthor();
        String content = message.getContentRaw();
        Guild guild = message.getGuild();


        if (event.getAuthor().isBot() || !event.isFromGuild()) 
            return;

        if (content.equals("!ping")) {
            System.out.println("Ping command executed");
            long startTime = System.currentTimeMillis();

            textChannel.sendMessage("Pong!").queue(response -> {
                long endTime = System.currentTimeMillis();
                long latency = endTime - startTime;
                response.editMessage("Pong! (Latency: " + latency + "ms)").queue();
            });
        }

        if (content.startsWith("!play")) {
            System.out.println("Play command executed");
            if (author == null || member.getVoiceState().getChannel() == null) {
                textChannel.sendMessage("You need to be in a voice channel to use this command!").queue();
                return;
            }

            VoiceChannel voiceChannel = (VoiceChannel)member.getVoiceState().getChannel();
            AudioManager manager = guild.getAudioManager();
            manager.setSendingHandler(new SendHandler());
            manager.openAudioConnection((AudioChannel)textChannel);
        }

        if (!content.startsWith("!"))
            ResponseHandler.handleMessage(content, textChannel);
    }
}