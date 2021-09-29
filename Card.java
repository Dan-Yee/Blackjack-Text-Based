/**
 * Implementation of a Card Object
 * @author Dan Yee
 */
public class Card {
    private String cardName;
    private String displayName;
    private int cardValue;
    private int cardCount;

    public Card(String cardName, String displayName, int cardValue, int cardCount) {
        this.cardName = cardName;
        this.displayName = displayName;
        this.cardValue = cardValue;
        this.cardCount = cardCount;
    }

    /**
     * Get the value of the specific card for accumulated total
     * @return this.cardValue - a reference to the value of the card
     */
    public int getValue() {
        return this.cardValue;
    }

    /**
     * Change the value of a card - Only used for Aces
     * @param cardValue - the new value of the card which should be 1 (Ace defaults to 11)
     */
    public void setValue(int cardValue) {
        this.cardValue = cardValue;
    }

    /**
     * Get the current running count of a specific card
     * @return card.cardCount - a reference to the count associated with the card
     */
    public int getCardCount() {
        return this.cardCount;
    }

    /**
     * Lower the total of a specific card by 1 to denote that it has been drawn from the deck
     */
    public void decrementCardCount() {
        this.cardCount = this.cardCount - 1;
    }

    /**
     * Get the name of the card - Used for comparison of cards to see if Ace is present before bust
     * @return this.cardName - a reference to the name of the card
     */
    public String getCardName() {
        return this.cardName;
    }

    /**
     * Text based display of the card for the player
     */
    public String toString() {
        return this.displayName;
    }
}