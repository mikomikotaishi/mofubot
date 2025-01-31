#pragma once

#include <array>
#include <print>
#include <string>
#include <vector>

enum class Piece { Empty, Pawn, Knight, Bishop, Rook, Queen, King };
enum class Colour { None, Black, White };

struct Square {
    Piece piece;
    Colour colour;
};

class ChessBoard {
private:
    std::array<std::array<Square, 8>, 8> board;
public:
    ChessBoard();
    void initialise();
    void loadFen(const std::string& fen);
    void printBoard();
};