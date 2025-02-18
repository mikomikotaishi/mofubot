package org.mofubot.game.chess;

import java.lang.foreign.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChessEngine {
    static {
        String os = System.getProperty("os.name").toLowerCase();
        String libName = os.contains("win") ? "chess.dll" : "libchess.so";
        Path libPath = Paths.get("app/src/main/resources", libName).toAbsolutePath();
        System.load(libPath.toString());
    }
}
