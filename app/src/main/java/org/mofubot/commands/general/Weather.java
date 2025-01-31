package org.mofubot.commands.general;

import java.io.IOException;

import javax.annotation.Nonnull;

import org.mofubot.clients.WeatherClient;
import org.mofubot.structures.commands.APICommand;

import com.google.gson.JsonObject;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Weather implements APICommand {
    private Weather() {}

    private static final WeatherClient weatherClient = new WeatherClient();

    public static void invoke(@Nonnull SlashCommandInteractionEvent event) {
        System.out.println("Weather command invoked.");
        String location = event.getOption("location").getAsString();
        try {
            System.out.println("Attempting to retrieve data for location: " + location);
            JsonObject weatherData = weatherClient.getWeather(location);
            double temp = weatherData.get("temp").getAsDouble();
            event.reply("Current temperature in " + location + ": " + temp + "°C");
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to execute weather command due to missing weather token.");
            event.reply("Weather command unavailable currently.");
        } catch (IOException e) {
            System.err.println("Failed to retrieve weather data for location: " + location);
            event.reply("Error retrieving weather data for location: " + location);
        }
    }
}
