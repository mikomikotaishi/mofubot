module;

#ifndef STD_MODULE

#endif

export module chess.ChessEngine;

#ifdef STD_MODULE
import std;
#endif

extern "C" {
    const char* getBoardState();
}