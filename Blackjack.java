import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the card game Blackjack
 * @author Dan Yee
 */
public class Blackjack {
    private Deck cardDeck;
    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;
    private int dealerHandValue;
    private int playerHandValue;
    private boolean showDealerFirst;

    public Blackjack() {
        this.cardDeck = new Deck();
        this.dealerHand = new ArrayList<Card>();
        this.playerHand = new ArrayList<Card>();
        this.dealerHandValue = 0;
        this.playerHandValue = 0;
        this.showDealerFirst = false;
    }

    /**
     * Get the current value of the dealer's hand
     * @return this.dealerHandValue - a reference to the total value of the dealer's hand
     */
    public int getDealerHandValue() {
        return this.dealerHandValue;
    }

    /**
     * Change the current value of the dealer's hand - For Internal Use Only
     * @param handValue - the new value of the dealer's hand
     */
    private void setDealerHandValue(int handValue) {
        this.dealerHandValue = handValue;
    }

    /**
     * Get the current value of the player's hand
     * @return this.playerHandValue - a reference to the total value of the player's hand
     */
    public int getPlayerHandValue() {
        return this.playerHandValue;
    }

    /**
     * Change the current value of the player's hand - For Internal Use Only
     * @param handValue - the new value of the player's hand
     */
    private void setPlayerHandValue(int handValue) {
        this.playerHandValue = handValue;
    }

    /**
     * Displays the dealers cards and the players cards
     */
    public void displayGame() {
        String dealerHand = "Dealer: ";
        String playerHand = "Player: ";

        if(this.showDealerFirst) {
            for(int i = 0; i < this.dealerHand.size(); i++)
                dealerHand += (this.dealerHand.get(i).toString() + " ");
            
            System.out.println(dealerHand);
            System.out.println("Dealer Value: " + this.dealerHandValue);
        } else {
            dealerHand += "? ";
            for(int i = 1; i < this.dealerHand.size(); i++)
                dealerHand += (this.dealerHand.get(i).toString() + " ");
            
            System.out.println(dealerHand);
        }
        for(int j = 0; j < this.playerHand.size(); j++) {
            playerHand += (this.playerHand.get(j).toString() + " ");
        }
        System.out.println(playerHand);
        System.out.println("Player Value: " + this.playerHandValue + "\n");
    }

    /**
     * Update the hand values of the dealer and the player
     */
    public void updateHandValues() {
        int dealerHandTotal = 0;
        int playerHandTotal = 0;

        for(Card card : this.dealerHand)
            dealerHandTotal += card.getValue();
        for(Card card : this.playerHand)
            playerHandTotal += card.getValue();
        
        this.setDealerHandValue(dealerHandTotal);
        this.setPlayerHandValue(playerHandTotal);
    }

    /**
     * Starts a new game by drawing two cards for the player and the dealer respectively
     */
    public void newGame() {
        this.cardDeck.buildDeck();
        this.showDealerFirst = false;
        this.dealerHand.clear();
        this.playerHand.clear();

        this.dealerHand.add(this.cardDeck.drawCard());
        this.dealerHand.add(this.cardDeck.drawCard());

        this.playerHand.add(this.cardDeck.drawCard());
        this.playerHand.add(this.cardDeck.drawCard());

        updateHandValues();
    }

    /**
     * Player hit function
     * @param id - 0 or 1 depending on if it is player or dealer
     */
    public void hitFunction(int id) {
        if(id == 0)
            this.dealerHand.add(this.cardDeck.drawCard());
        else if(id == 1)
            this.playerHand.add(this.cardDeck.drawCard());

        updateHandValues();
    }

    /**
     * Player stand function, triggers dealer to play for their hand
     */
    public void standFunction() throws InterruptedException {
        this.showDealerFirst = true;

        System.out.println();
        displayGame();
        while(this.dealerHandValue < 17) {
            System.out.println("The dealer has chosen to Hit.");
            this.hitFunction(0);
            displayGame();
            TimeUnit.SECONDS.sleep(1);
        }
        if(this.dealerHandValue > 21) {
            System.out.println("The dealer has busted!");
            return;
        }

        System.out.println("The Dealer has chosen to Stand.");
    }

    /**
     * Checks the status of the players hand after each hit and changes Aces to 1 if necessary
     * @return 1 if the player busted
     * @return 0 otherwise
     */
    public int checkPlayerStatus() {
        for(Card card : this.playerHand) {
            if(card.getCardName().equalsIgnoreCase("ACE") && card.getValue() == 11) {
                if(this.playerHandValue > 21) {
                    card.setValue(1);
                    updateHandValues();
                    break;
                }
            }
        }
        displayGame();
        if(this.playerHandValue > 21) {
            this.showDealerFirst = true;
            displayGame();
            return 1;
        } else if(this.playerHandValue == 21)
            return 2;
        return 0;
    }

    /**
     * Checks the final game status and determines who won the round
     * @return 1 if the player beat the dealer
     * @return -1 if the dealer beat the player
     * @return 0 if the dealer and the player tied
     */
    public int checkGameStatus() {
        if(this.playerHandValue > this.dealerHandValue || this.dealerHandValue > 21)
            return 1;
        else if(this.playerHandValue < this.dealerHandValue)
            return -1;
        else
            return 0;
    }
}
