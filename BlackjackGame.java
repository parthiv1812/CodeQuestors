package ca.sheridancollege.project;

public class BlackjackGame extends Game {
    private GroupOfCards deck;
    private BlackjackPlayer dealer;

    public BlackjackGame(String name) {
        super(name);
        this.deck = new GroupOfCards(52);
        this.dealer = new BlackjackPlayer("Dealer");
    }

    @Override
    public void play() {
        deck.shuffle();
        for (Player player : getPlayers()) {
            BlackjackPlayer blackjackPlayer = (BlackjackPlayer) player;
            blackjackPlayer.hit(deck);
            blackjackPlayer.hit(deck);
        }
        dealer.hit(deck);
        dealer.hit(deck);

        for (Player player : getPlayers()) {
            ((BlackjackPlayer) player).play();
        }

        dealerPlay();
    }

    private void dealerPlay() {
        while (dealer.getTotalValue() < 17) {
            dealer.hit(deck);
        }
    }

    @Override
    public void declareWinner() {
        System.out.println("\nResults:");
        for (Player player : getPlayers()) {
            BlackjackPlayer blackjackPlayer = (BlackjackPlayer) player;
            System.out.println(blackjackPlayer.getName() + ": " + blackjackPlayer.getTotalValue());
        }
        System.out.println("Dealer: " + dealer.getTotalValue());
    }
}
