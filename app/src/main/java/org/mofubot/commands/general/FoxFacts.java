package org.mofubot.commands.general;

import jakarta.annotation.Nonnull;

import org.mofubot.structures.commands.BasicCommand;
import org.mofubot.utilities.RandomNumberGenerator;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FoxFacts implements BasicCommand {
    private FoxFacts() {}

    private static final String[] FACTS = {
        "Foxes are known to make up to 40 different sounds, some of which inlcude a scream-like howl, as well as chattering."
    };

    private static String getResponse() {
        int index = RandomNumberGenerator.generateRandomNumber(FACTS.length);
        return FACTS[index];
    }

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Fox fact command invoked.");
        event.reply(FoxFacts.getResponse()).queue();
    }
}
