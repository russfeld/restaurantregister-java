package edu.ksu.cs.cc410.restaurantregister;

import java.util.Random;

/**
 * Class to represent a credit card reader.
 *
 * <p>Call the <code>runCard()</code> method to simulate using a credit card.
 *
 * <p>Once of the <code>CardTransactionResult</code> values will be returned.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 * @see edu.ksu.cs.cc410.restaurantregister.CardTransactionResult
 */
public class CardReader {
    
    private Random random;
    
    /**
     * Constructor.
     */
    public CardReader() {
        random = new Random();
    }
    
    /**
     * Method to run a credit card.
     *
     * @return a <code>CardTransactionResult</code> giving the outcome
     */
    public CardTransactionResult runCard() {
        switch (random.nextInt(10)) {
            case 0:
                return CardTransactionResult.DECLINED;
            case 1:
                return CardTransactionResult.READ_ERROR;
            case 2:
                return CardTransactionResult.INSUFFICIENT_FUNDS;
            case 3:
                return CardTransactionResult.INCORRECT_PIN;
            default:
                return CardTransactionResult.APPROVED;
        }
    }
}