module;

#ifndef STD_MODULE
#include <array>
#include <string>
#endif

export module Board;

#ifdef STD_MODULE
import std;
#endif

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