package org.mofubot.commands.general;

import java.util.Random;

import javax.annotation.Nonnull;

import org.mofubot.commands.structures.Command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Magic8Ball implements Command {
    private Magic8Ball() {};

    private static final String[] ANSWERS = {
        // Affirmative
        "It is certain",
        "It is decidedly so",
        "Without a doubt",
        "Yes definitely",
        "You may rely on it",
        "As I see it, yes",
        "Most likely",
        "Outlook good",
        "Yes",
        "Signs point to yes",
        // Neutral
        "Reply hazy, try again",
        "Ask again later",
        "Better not tell you now",
        "Cannot predict now",
        "Concentrate and ask again",
        // Negative
        "Don't count on it",
        "My reply is no",
        "My sources say no",
        "Outlook not so good",
        "Very doubtful"
    };
    private static final Random rand = new Random();

    private static String getResponse() {
        int index = rand.nextInt(ANSWERS.length);
        return ANSWERS[index];
    }

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Magic 8 Ball command executed.");
        event.reply(Magic8Ball.getResponse()).queue();
    }
}
