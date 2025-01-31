#pragma once

#include <string>

#include "ChessBoard.hpp"

class ChessEngine {
private:
    ChessBoard board;
public:
    void initialise();
    std::string getBestMove(const std::string& fen);
    void makeMove(const std::string& move);
} engine;