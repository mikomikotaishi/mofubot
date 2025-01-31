package org.mofubot.commands.system;

import javax.annotation.Nonnull;

import org.mofubot.structures.commands.JDACommand;
import org.mofubot.system.ConfigLoader;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ReloadConfig implements JDACommand {
    private ReloadConfig() {}

    public static void invoke(@Nonnull SlashCommandInteractionEvent event, @Nonnull JDA instance) {
        System.out.println("Config reload command attempted.");
        String password = event.getOption("password").getAsString();
        if (password.equals(ConfigLoader.getMasterPassword())) {
            event.reply("Reloading config files!.").queue();
            ConfigLoader.reloadConfig();
        } else {
            System.out.println("Attempted (failed) config reload attempt by " + event.getUser().getGlobalName() + "(" + event.getUser().getId() + ")");
            event.reply("Incorrect master password!").queue();
        }
    }
}