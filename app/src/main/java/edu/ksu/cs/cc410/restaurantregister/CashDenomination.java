package edu.ksu.cs.cc410.restaurantregister;

/**
 * Enumeration of cash denomination values.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public enum CashDenomination {
    PENNY("Penny", 0.01),
    NICKEL("Nickel", 0.05),
    DIME("Dime", 0.10),
    QUARTER("Quarter", 0.25),
    HALF_DOLLAR("Half Dollar", 0.50),
    DOLLAR_COIN("Dollar Coin", 1.0),
    ONE_DOLLAR_BILL("$1 Bill", 1.0),
    FIVE_DOLLAR_BILL("$5 Bill", 5.0),
    TEN_DOLLAR_BILL("$10 Bill", 10.0),
    TWENTY_DOLLAR_BILL("$20 Bill", 20.0),
    FIFTY_DOLLAR_BILL("$50 Bill", 50.0),
    HUNDRED_DOLLAR_BILL("$100 Bill", 100.0);
    
    
    /** A descriptive name for the cash value. */
    private final String description;
    /** A monetary value. */
    private final double value;
    
    /**
     * Construct a new denomination with the given description.
     *
     * @param value the description of the denomination
     */
    private CashDenomination(String description, double value) {
        this.description = description;
        this.value = value;
    }
    
    /**
     * Get the associated value.
     *
     * @return the value of this denomination
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     * Return a string representation of the denomination.
     *
     * @return the description of the denomination
     */
    @Override
    public String toString() {
        return this.description;
    }
}