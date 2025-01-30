package org.mofubot.commands.admin;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import org.mofubot.commands.structures.Command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Ban implements Command {
    private Ban() {};

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Ban command attempted.");

        Member member = event.getOption("user").getAsMember();
        User user = event.getOption("user").getAsUser();

        event.deferReply(true).queue();
        InteractionHook hook = event.getHook();
        hook.setEphemeral(true);
        if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            System.out.println("Attempted (failed) ban attempt by " + event.getUser().getGlobalName() + "(" + event.getUser().getId() + ")");
            hook.sendMessage("You do not have the required permissions to ban users from this server.").queue();
            return;
        }

        Member selfMember = event.getGuild().getSelfMember();
        if (!selfMember.hasPermission(Permission.BAN_MEMBERS)) {
            System.out.println("Attempted (failed) shutdown attempt by " + event.getUser().getGlobalName() + "(" + event.getUser().getId() + ")");
            hook.sendMessage("I don't have the required permissions to ban users from this server.").queue();
            return;
        }

        if (member != null && !selfMember.canInteract(member)) {
            System.out.println("Attempted (failed) shutdown attempt by " + event.getUser().getGlobalName() + "(" + event.getUser().getId() + ")");
            hook.sendMessage("This user is too powerful for me to ban.").queue();
            return;
        }

        int delDays = event.getOption("del_days", 0, OptionMapping::getAsInt);

        String reason = event.getOption("reason",
                () -> "Banned by " + event.getUser().getName(),
                OptionMapping::getAsString);

        event.getGuild().ban(user, delDays, TimeUnit.DAYS)
            .reason(reason)
            .flatMap(v -> hook.sendMessage("Banned user " + user.getName()))
            .queue();

        System.out.println("Ban executed by " + event.getUser().getGlobalName() + " (" + event.getUser().getId() + ") " 
            + "on " + user.getName() + " (" + user.getId() + ") ");
    }
}
