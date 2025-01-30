package org.mofubot.commands.control;

import javax.annotation.Nonnull;

import org.mofubot.commands.structures.JDAControl;
import org.mofubot.utilities.ConfigLoader;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Shutdown implements JDAControl {
    private Shutdown() {};

    public static void invoke(@Nonnull SlashCommandInteractionEvent event, @Nonnull JDA instance) {
        System.out.println("Shutdown command attempted.");
        String password = event.getOption("password").getAsString();
        if (password.equals(ConfigLoader.getShutdownPassword())) {
            event.reply("Shutting down bot.").queue();
            instance.shutdown();
        } else {
            System.out.println("Attempted (failed) shutdown attempt by " + event.getUser().getGlobalName() + "(" + event.getUser().getId() + ")");
            event.reply("Incorrect shutdown password!").queue();
        }
    }
}