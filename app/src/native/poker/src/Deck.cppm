module;

#ifndef STD_MODULE
#include <algorithm>
#include <random>
#include <stdexcept>
#include <vector>
#endif

export module poker.Deck;

#ifdef STD_MODULE
import std;
#endif

import poker.Card;

export class Deck {
private:
    std::vector<Card> cards;
    std::mt19937 rng;
public:
    Deck():
        rng{std::random_device{}()} {
        for (int suit = 0; suit < 4; ++suit)
            for (int rank = 2; rank <= 14; ++rank)
                cards.push_back({ static_cast<Rank>(rank), static_cast<Suit>(suit) });
        shuffle();
    }

    void shuffle() {
        std::shuffle(cards.begin(), cards.end(), rng);
    }

    Card draw() {
        if (cards.empty())
            throw std::runtime_error("Deck is empty!");
        Card top = cards.back();
        cards.pop_back();
        return top;
    }
};