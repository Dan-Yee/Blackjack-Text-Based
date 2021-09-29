import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Implementation of a Deck using Card Objects
 * @author Dan Yee
 */
public class Deck {
    private ArrayList<Card> cardDeck;
    private Random randomNumber;

    public Deck() {
        this.randomNumber = new Random();
        this.cardDeck = new ArrayList<Card>(13);
        buildDeck();
    }

    /**
     * Create the standard deck of 52 cards using 4 sets of 13 cards
     */
    public void buildDeck() {
        this.cardDeck.clear();

        this.cardDeck.add(new Card("ACE", "A", 11, 4));
        this.cardDeck.add(new Card("TWO", "2", 2, 4));
        this.cardDeck.add(new Card("THREE", "3", 3, 4));
        this.cardDeck.add(new Card("FOUR", "4", 4, 4));
        this.cardDeck.add(new Card("FIVE", "5", 5, 4));
        this.cardDeck.add(new Card("SIX", "6", 6, 4));
        this.cardDeck.add(new Card("SEVEN", "7", 7, 4));
        this.cardDeck.add(new Card("EIGHT", "8", 8, 4));
        this.cardDeck.add(new Card("NINE", "9", 9, 4));
        this.cardDeck.add(new Card("TEN", "10", 10, 4));
        this.cardDeck.add(new Card("JACK", "J", 10, 4));
        this.cardDeck.add(new Card("QUEEN", "Q", 10, 4));
        this.cardDeck.add(new Card("KING", "K", 10, 4));
    }

    /**
     * Checks the whole deck and removes any card that has been drawn four times
     */
    private void checkDeck() {
        Iterator<Card> checkDeck = this.cardDeck.iterator();

        while(checkDeck.hasNext()) {
            Card currentCard = checkDeck.next();
            if(currentCard.getCardCount() == 0)
                checkDeck.remove();
        }
    }

    /**
     * picks and returns a random card from the deck
     * @return randomCard - a randcom card picked from the deck
     */
    public Card drawCard() {
        int randomCardIndex = randomNumber.nextInt(this.cardDeck.size());
        Card randomCard = this.cardDeck.get(randomCardIndex);

        randomCard.decrementCardCount();
        checkDeck();

        return randomCard;
    }
}
