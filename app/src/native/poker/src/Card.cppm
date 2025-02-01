module;

export module Card;

export enum class Rank: int {
    Two = 2,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King,
    Ace
};

export enum class Suit: int {
    Clubs,
    Diamonds,
    Hearts,
    Spades
};

export struct Card {
    Rank rank;
    Suit suit;

    constexpr bool operator==(const Card& other) {
        return rank == other.rank && suit == other.suit;
    }
};