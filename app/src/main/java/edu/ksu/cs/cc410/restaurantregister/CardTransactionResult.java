package edu.ksu.cs.cc410.restaurantregister;

/**
 * Enumeration of credit card transaction results.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public enum CardTransactionResult {
    APPROVED("Approved"),
    DECLINED("Declined"),
    READ_ERROR("Card Read Error"),
    INSUFFICIENT_FUNDS("Insufficient Funds"),
    INCORRECT_PIN("Incorrect PIN");
    
    /** A descriptive name for the result. */
    private final String description;
    
    /**
     * Construct a new result with the given description.
     *
     * @param value the description of the result
     */
    private CardTransactionResult(String value) {
        this.description = value;
    }
    
    /**
     * Return a string representation of the result.
     *
     * @return the description of the result
     */
    @Override
    public String toString() {
        return this.description;
    }
}