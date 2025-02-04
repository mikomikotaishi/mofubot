module org.mofubot {
    requires java.base;
    requires java.net.http;
    requires com.google.gson;
    requires okhttp3;
    requires net.dv8tion.jda;
    // requires dev.arbjerg.lavaplayer;
    // requires dev.lavalink.youtube.common;
    // requires org.junit.jupiter.api;
    // requires org.junit.platform.launcher;

    exports org.mofubot;
    exports org.mofubot.commands.admin;
    exports org.mofubot.commands.audio;
    exports org.mofubot.commands.game;
    exports org.mofubot.commands.general;
    exports org.mofubot.commands.imageboard;
    exports org.mofubot.commands.system;
    exports org.mofubot.system;
    exports org.mofubot.utilities;
    exports org.mofubot.utilities.calculations;
    exports org.mofubot.audio;
    exports org.mofubot.clients;
    exports org.mofubot.structures;
    exports org.mofubot.structures.commands;
}