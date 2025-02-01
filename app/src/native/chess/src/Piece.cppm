module;

#ifndef STD_MODULE

#endif

export module Piece;

#ifdef STD_MODULE
import std;
#endif

export enum class PieceType { 
    Empty, 
    Pawn, 
    Knight, 
    Bishop, 
    Rook, 
    Queen, 
    King 
};

export enum class PieceColour {
    None,
    Black,
    White
};

export class Piece {
private:
    PieceType type;
    PieceColour colour;
public:
    Piece(PieceType type = PieceType::Empty, PieceColour colour = PieceColour::None):
        type{type}, colour{colour} {}
    
};