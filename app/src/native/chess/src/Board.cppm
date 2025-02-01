module;

#include <array>
#include <string>

export module Board;

import std;

import Move;
import Piece;

export class Board {
private:
    std::array<std::array<Piece, 8>, 8> board;
    bool whiteToMove;
public:
    Board(): 
        whiteToMove{true} {
        initialiseBoard();
    }
    std::string getFEN() const {
        
    }
};