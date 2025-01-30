package org.mofubot.commands.general;

import java.util.Random;

import javax.annotation.Nonnull;

import org.mofubot.commands.structures.Command;
import org.mofubot.system.RandomNumberGenerator;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FoxFacts implements Command {
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
