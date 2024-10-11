package ca.sheridancollege.project;

import java.util.ArrayList;

public class BlackjackPlayer extends Player {
    private ArrayList<Card> hand;

    public BlackjackPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }

    public void hit(GroupOfCards deck) {
        hand.add(deck.draw());
    }

    public int getTotalValue() {
        int total = 0;
        int aceCount = 0;
        for (Card card : hand) {
            BlackjackCard blackjackCard = (BlackjackCard) card;
            total += blackjackCard.getCardValue();
            if (blackjackCard.getValue().equals("A")) {
                aceCount++;
            }
        }
        while (total > 21 && aceCount > 0) {
            total -= 10; // Reducing ace from 11 to 1
            aceCount--;
        }
        return total;
    }

    @Override
    public void play() {
        System.out.println(getName() + " is playing...");
    }
}
