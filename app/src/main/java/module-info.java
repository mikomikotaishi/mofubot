module org.mofubot {
    requires java.base;
    requires java.net.http;
    requires jakarta.annotation;
    requires com.google.gson;
    requires okhttp3;
    requires transitive net.dv8tion.jda;

    opens org.mofubot.core to com.google.gson;

    exports org.mofubot.audio;
    exports org.mofubot.clients;
    exports org.mofubot.commands.admin;
    exports org.mofubot.commands.audio;
    exports org.mofubot.commands.cryptography;
    exports org.mofubot.commands.game;
    exports org.mofubot.commands.general;
    exports org.mofubot.commands.imageboard;
    exports org.mofubot.commands.system;
    exports org.mofubot.core;
    exports org.mofubot.game.chess;
    exports org.mofubot.game.poker;
    exports org.mofubot.system;
}