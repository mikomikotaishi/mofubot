package org.mofubot.commands.general;

import javax.annotation.Nonnull;

import org.mofubot.structures.commands.BasicCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Ping implements BasicCommand {
    private Ping() {}

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Ping command invoked.");
        long startTime = System.currentTimeMillis();
        event.reply("Konkon!").queue(response -> {
            long endTime = System.currentTimeMillis();
            long latency = endTime - startTime;
            response.editOriginal("Konkon! (Latency: " + latency + "ms)").queue();
        });
    }
}
