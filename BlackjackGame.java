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

        // Initial deal: two cards for each player and dealer
        for (Player player : getPlayers()) {
            BlackjackPlayer blackjackPlayer = (BlackjackPlayer) player;
            blackjackPlayer.hit(deck);
            blackjackPlayer.hit(deck);
        }
        dealer.hit(deck);
        dealer.hit(deck);

        // Players' turns
        for (Player player : getPlayers()) {
            BlackjackPlayer blackjackPlayer = (BlackjackPlayer) player;
            blackjackPlayer.play(deck);
        }

        // Dealer's turn
        dealerPlay();
    }

    private void dealerPlay() {
        System.out.println("\nDealer's turn...");
        System.out.println("Dealer's current hand: " + dealer.getHand());

        while (dealer.getTotalValue() < 17) {
            dealer.hit(deck);
            System.out.println("Dealer drew: " + dealer.getHand().get(dealer.getHand().size() - 1));
        }

        if (dealer.getTotalValue() > 21) {
            System.out.println("Dealer BUSTS with total value: " + dealer.getTotalValue());
        } else {
            System.out.println("Dealer stands with total value: " + dealer.getTotalValue());
        }
    }

    @Override
    public void declareWinner() {
        System.out.println("\nResults:");

        for (Player player : getPlayers()) {
            BlackjackPlayer blackjackPlayer = (BlackjackPlayer) player;
            System.out.println(blackjackPlayer);
        }
        System.out.println(dealer);

        for (Player player : getPlayers()) {
            BlackjackPlayer blackjackPlayer = (BlackjackPlayer) player;
            if (blackjackPlayer.isBusted()
                    || (!dealer.isBusted() && dealer.getTotalValue() > blackjackPlayer.getTotalValue())) {
                System.out.println(blackjackPlayer.getName() + " loses against the dealer.");
            } else if (dealer.isBusted() || blackjackPlayer.getTotalValue() > dealer.getTotalValue()) {
                System.out.println(blackjackPlayer.getName() + " wins against the dealer!");
            } else {
                System.out.println(blackjackPlayer.getName() + " ties with the dealer.");
            }
        }
    }
}
