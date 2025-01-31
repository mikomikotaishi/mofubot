#include "org_mofubot_game_chess_ChessEngine.h"
#include "ChessEngine.hpp"

extern "C" JNIEXPORT void JNICALL Java_org_mofubot_game_chess_ChessEngine_initialize(JNIEnv*, jobject) {
    engine.initialise();
}

extern "C" JNIEXPORT jstring JNICALL Java_org_mofubot_game_chess_ChessEngine_getBestMove(JNIEnv* env, jobject, jstring fen) {
    const char* fenChars = env->GetStringUTFChars(fen, nullptr);
    std::string bestMove = engine.getBestMove(fenChars);
    env->ReleaseStringUTFChars(fen, fenChars);
    return env->NewStringUTF(bestMove.c_str());
}

extern "C" JNIEXPORT void JNICALL Java_org_mofubot_game_chess_ChessEngine_makeMove(JNIEnv* env, jobject, jstring move) {
    const char* moveChars = env->GetStringUTFChars(move, nullptr);
    engine.makeMove(moveChars);
    env->ReleaseStringUTFChars(move, moveChars);
}