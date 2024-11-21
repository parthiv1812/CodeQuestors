package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackPlayer extends Player {
    private ArrayList<Card> hand;
    private boolean isBusted = false;

    public BlackjackPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }

    public void hit(GroupOfCards deck) {
        if (!isBusted) {
            hand.add(deck.draw());
        }
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
        if (total > 21) {
            isBusted = true;
        }
        return total;
    }

    public boolean isBusted() {
        return isBusted;
    }

    public void play(GroupOfCards deck) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(getName() + " is playing...");

        while (true) {
            System.out.println("Current hand: " + hand + " | Total value: " + getTotalValue());
            if (getTotalValue() >= 21 || isBusted) {
                break;
            }

            System.out.print("Would you like to 'hit' or 'stand'? ");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("hit")) {
                hit(deck);
                System.out.println(getName() + " drew: " + hand.get(hand.size() - 1));
            } else if (action.equalsIgnoreCase("stand")) {
                break;
            } else {
                System.out.println("Invalid action. Please type 'hit' or 'stand'.");
            }
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return getName() + " | Hand: " + hand + " | Total value: " + getTotalValue() + (isBusted ? " (BUSTED)" : "");
    }
}
