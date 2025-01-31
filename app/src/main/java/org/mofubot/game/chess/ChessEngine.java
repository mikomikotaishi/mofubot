package org.mofubot.game.chess;

public class ChessEngine {
    static {
        System.loadLibrary("chessengine");
    }

    public native void initialise();

    public native String getBestMove(String fen);

    public native void makeMove(String fen);
}
