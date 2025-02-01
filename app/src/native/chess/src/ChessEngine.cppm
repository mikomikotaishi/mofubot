module;

#ifndef STD_MODULE

#endif

export module ChessEngine;

#ifdef STD_MODULE
import std;
#endif

extern "C" {
    const char* getBoardState();
}