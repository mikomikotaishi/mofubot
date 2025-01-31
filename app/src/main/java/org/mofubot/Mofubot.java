package org.mofubot;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;

import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

import org.mofubot.commands.admin.*;
import org.mofubot.commands.audio.*;
import org.mofubot.commands.game.Magic8Ball;
import org.mofubot.commands.general.*;
import org.mofubot.commands.imageboard.*;
import org.mofubot.commands.system.*;
import org.mofubot.system.*;
import org.mofubot.utilities.ResponseHandler;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class Mofubot extends ListenerAdapter {
    private final JDA jda;
    private final String MASTER_PASSWORD;

    public Mofubot() {
        this.jda = null;
        this.MASTER_PASSWORD = null;
    }

    public Mofubot(JDA jda, String shutdownPassword) {
        this.jda = jda;
        this.MASTER_PASSWORD = shutdownPassword;
    }

    public static void main(String[] args) throws LoginException, InterruptedException {
        String BOT_TOKEN = ConfigLoader.getBotToken();
        String MASTER_PASSWORD = ConfigLoader.getMasterPassword();

        EnumSet<GatewayIntent> INTENTS = EnumSet.of(
            GatewayIntent.GUILD_MEMBERS,
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.GUILD_VOICE_STATES,
            GatewayIntent.MESSAGE_CONTENT
        );

        JDA api = JDABuilder.createDefault(BOT_TOKEN, INTENTS)
            .build()
            .awaitReady();

        CommandListUpdateAction commands = api.updateCommands();

        commands.addCommands(
            // ====== Admin commands ======
            // Ban command
            Commands.slash("ban", "Ban a user from this server. Requires permission to ban users.")
                .addOptions(new OptionData(USER, "user", "The user to ban")
                    .setRequired(true))
                .addOptions(new OptionData(INTEGER, "del_days", "Delete messages from the past days.")
                    .setRequiredRange(0, 7))
                .addOptions(new OptionData(STRING, "reason", "The ban reason to use (default: Banned by <user>)"))
                .setGuildOnly(true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)),
            
            // ====== Audio commands ======
            // Check queue command
            Commands.slash("checkqueue", "Sends the list of all songs in the current queue")
                .setGuildOnly(true),
            // Clear command
            Commands.slash("clear", "Clears all music in the current queue")
                .setGuildOnly(true),
            // Disconnect command
            Commands.slash("disconnect", "Disconnects the bot from voice channel")
                .setGuildOnly(true),
            // Play command
            Commands.slash("play", "Plays audio in the voice channel of the user")
                .addOptions(new OptionData(STRING, "query", "The query for YouTube")
                    .setRequired(true))
                .setGuildOnly(true),
            // Skip command
            Commands.slash("skip", "Skips the current song")
                .setGuildOnly(true),
            
            // ====== General commands ======
            // Fox fact command
            Commands.slash("foxfacts", "Get an interesting fact about foxes"),
            // Magic 8 Ball command
            Commands.slash("magic8ball", "Consults the Magic 8 Ball")
                .addOptions(new OptionData(STRING, "query", "The question to ask the magic 8 ball")
                    .setRequired(true)),
            // Ping command
            Commands.slash("ping", "Reports the ping of the bot"),
            // Weather command
            Commands.slash("weather", "Obtain weather information for a specified location")
                .addOptions(new OptionData(STRING, "location", "The location to search")
                    .setRequired(true)),

            // ====== Imageboard commands ======
            // Danbooru command
            Commands.slash("danbooru", "Query danbooru")
                .addOptions(new OptionData(STRING, "tag1", "The first tag to search")
                    .setRequired(true))
                .addOptions(new OptionData(STRING, "tag2", "The second tag to search")),
            // e621 command
            Commands.slash("e621", "Query e621")
                .addOptions(new OptionData(STRING, "tag1", "The first tag to search")
                    .setRequired(true))
                .addOptions(new OptionData(STRING, "tag2", "The second tag to search")),
            // Gelbooru command
            Commands.slash("gelbooru", "Query gelbooru")
                .addOptions(new OptionData(STRING, "tag1", "The first tag to search")
                    .setRequired(true))
                .addOptions(new OptionData(STRING, "tag2", "The second tag to search")),
            // Gyate Booru command
            Commands.slash("gyatebooru", "Query gyate booru")
                .addOptions(new OptionData(STRING, "tag1", "The first tag to search")
                    .setRequired(true))
                .addOptions(new OptionData(STRING, "tag2", "The second tag to search")),
            // Rule34 command
            Commands.slash("rule34", "Query Rule34")
                .addOptions(new OptionData(STRING, "tag1", "The first tag to search")
                    .setRequired(true))
                .addOptions(new OptionData(STRING, "tag2", "The second tag to search")),

            // ====== System commands ======            
            // Reload config command
            Commands.slash("reloadconfig", "Reloads config.properties")
                .addOptions(new OptionData(STRING, "password", "The master pasword (specified in config.properties)")
                    .setRequired(true)),
            // Shutdown command
            Commands.slash("shutdown", "Shuts down the bot")
                .addOptions(new OptionData(STRING, "password", "The master password (specified in config.properties)")
                    .setRequired(true))
        ).queue();

        Mofubot botInstance = new Mofubot(api, MASTER_PASSWORD);
        api.addEventListener(botInstance);
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        Message message = event.getMessage();
        MessageChannel textChannel = event.getChannel();
        Member member = event.getMember();
        User author = message.getAuthor();
        String content = message.getContentRaw();
        Guild guild = message.getGuild();

        if (event.getAuthor().isBot() || !event.isFromGuild()) 
            return;

        ResponseHandler.handleMessage(content, textChannel);
    }

    
    @Override 
    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
        if (event.getGuild() == null)
            return;
        switch (event.getName()) {
            // Admin
            case "ban":
                Ban.invoke(event);
                break;
            // Audio
            case "checkqueue":
                CheckQueue.invoke(event);
                break;
            case "clear":
                Clear.invoke(event);
                break;
            case "disconnect":
                Disconnect.invoke(event);
                break;
            case "play":
                Play.invoke(event);
                break;
            case "skip":
                Skip.invoke(event);
                break;
            // General
            case "foxfacts":
                FoxFacts.invoke(event);
                break;
            case "magic8ball":
                Magic8Ball.invoke(event);
                break;
            case "ping":
                Ping.invoke(event);
                break;
            case "weather":
                Weather.invoke(event);
                break;
            // Imageboard
            case "danbooru":
                Danbooru.invoke(event);
                break;
            case "e621":
                E621.invoke(event);
                break;
            case "gelbooru":
                Gelbooru.invoke(event);
                break;
            case "gyatebooru":
                GyateBooru.invoke(event);
                break;
            case "rule34":
                Rule34.invoke(event);
                break;
            // System
            case "shutdown":
                Shutdown.invoke(event, jda);
                break;
            // Default
            default:
                event.reply("Invalid command!").setEphemeral(true).queue();
        }
    }
}
