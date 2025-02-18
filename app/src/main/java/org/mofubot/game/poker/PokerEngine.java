package org.mofubot.game.poker;

import java.lang.foreign.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Interface to the poker engine.
 * This class is used to load the poker engine library.
 */
public class PokerEngine {
    static {
        String os = System.getProperty("os.name").toLowerCase();
        String libName = os.contains("win") ? "poker.dll" : "libpoker.so";
        Path libPath = Paths.get("app/src/main/resources", libName).toAbsolutePath();
        System.load(libPath.toString());
    }
}
