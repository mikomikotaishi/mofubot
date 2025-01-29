package org.mofubot.commands;

import java.util.Random;

public class Magic8Ball {
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

    public static String ask() {
        int index = rand.nextInt(ANSWERS.length);
        return ANSWERS[index];
    }
}
