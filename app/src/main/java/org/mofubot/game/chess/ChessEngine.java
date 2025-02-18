package org.mofubot.game.chess;

import java.lang.foreign.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Interface to the chess engine.
 * This class is used to load the chess engine library.
 */
public class ChessEngine {
    static {
        String os = System.getProperty("os.name").toLowerCase();
        String libName = os.contains("win") ? "chess.dll" : "libchess.so";
        Path libPath = Paths.get("app/src/main/resources", libName).toAbsolutePath();
        System.load(libPath.toString());
    }
}
