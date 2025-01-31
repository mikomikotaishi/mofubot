package org.mofubot.commands.audio;

import javax.annotation.Nonnull;

import org.mofubot.audio.BotAudio;
import org.mofubot.commands.structures.AudioCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Skip implements AudioCommand {
    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Skip command invoked.");
        long guildId = event.getGuild().getIdLong();
        BotAudio botAudio = BotAudio.getInstance(guildId);

        if (botAudio.getAudioPlayer().getPlayingTrack() == null) {
            event.reply("There is no track currently playing.").queue();
            return;
        }

        botAudio.getScheduler().skip();
        event.reply("Skipped the current track.").queue();
    }
}