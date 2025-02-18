module;

#ifndef STD_MODULE
#include <algorithm>
#include <map>
#include <stdexcept>
#include <vector>
#endif

export module poker.HandEvaluator;

#ifdef STD_MODULE
import std;
#endif

import poker.Card;

export class HandEvaluator {
public:
    static int evaluateHand(const std::vector<Card>& hand) {
        if (hand.size() != 5)
            throw std::runtime_error("Invalid hand size!");
        std::map<Rank, int> rankCount;
        std::map<Suit, int> suitCount;
        for (const Card& card: hand) {
            rankCount[card.rank]++;
            suitCount[card.suit]++;
        }
        bool isFlush = std::any_of(suitCount.begin(), suitCount.end(), [](auto& pair) -> bool {
            return pair.second >= 5;
        });
        std::vector<int> ranks;
        for (const auto& pair: rankCount)   
            ranks.push_back(static_cast<int>(pair.first));
        std::sort(ranks.begin(), ranks.end());
        bool isStraight = false;
        for (size_t i = 0; i + 4 < ranks.size(); ++i) {
            if (ranks[i + 1] == ranks[i] + 1 &&
                ranks[i + 2] == ranks[i] + 2 &&
                ranks[i + 3] == ranks[i] + 3 &&
                ranks[i + 4] == ranks[i] + 4) {
                isStraight = true;
                break;
            }
        }
        if (isFlush && isStraight)
            return 9;
        else if (isFlush)
            return 6;
        else if (isStraight)
            return 5;
        else
            return 0;
    }
};