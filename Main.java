package ca.sheridancollege.project;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame("Blackjack");

        BlackjackPlayer player1 = new BlackjackPlayer("Player 1");
        BlackjackPlayer player2 = new BlackjackPlayer("Player 2");

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        game.setPlayers(players);
        game.play();
        game.declareWinner();
    }
}
