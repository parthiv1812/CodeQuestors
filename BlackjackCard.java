package ca.sheridancollege.project;

public class BlackjackCard extends Card {
    private String suit;
    private String value;

    public BlackjackCard(String value, String suit) {
        this.suit = suit;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCardValue() {
        switch (value) {
            case "A":
                return 11; // Ace
            case "K":
            case "Q":
            case "J":
                return 10; // Face cards
            default:
                return Integer.parseInt(value); // Numeric cards
        }
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
