package org.mofubot.commands.game;

import jakarta.annotation.Nonnull;

import org.mofubot.structures.commands.GameCommand;
import org.mofubot.utilities.RandomNumberGenerator;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Magic8Ball implements GameCommand {
    private Magic8Ball() {};

    private static final String[] ANSWERS = {
        // Affirmative
        "It is certain :white_check_mark:",
        "It is decidedly so :white_check_mark:",
        "Without a doubt :white_check_mark:",
        "Yes definitely :white_check_mark:",
        "You may rely on it :white_check_mark:",
        "As I see it, yes :white_check_mark:",
        "Most likely :white_check_mark:",
        "Outlook good :white_check_mark:",
        "Yes :white_check_mark:",
        "Signs point to yes :white_check_mark:",
        // Neutral
        "Reply hazy, try again :large_orange_diamond:",
        "Ask again later :large_orange_diamond:",
        "Better not tell you now :large_orange_diamond:",
        "Cannot predict now :large_orange_diamond:",
        "Concentrate and ask again :large_orange_diamond:",
        // Negative
        "Don't count on it :x:",
        "My reply is no :x:",
        "My sources say no :x:",
        "Outlook not so good :x:",
        "Very doubtful :x:"
    };

    private static String getResponse() {
        int index = RandomNumberGenerator.generateRandomNumber(ANSWERS.length);
        return ANSWERS[index];
    }

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Magic 8 Ball command executed.");
        event.reply(Magic8Ball.getResponse()).queue();
    }
}
