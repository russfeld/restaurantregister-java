package edu.ksu.cs.cc410.restaurantregister;

import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.Math;
import java.util.HashMap;

/**
 * Class to represent a cash drawer.
 *
 * <p>Typical use is to instantiate a drawer and call <code>getCount()</code> and 
 * <code>getTotal()</code> methods to get the state of the drawer while closed.
 *
 * <p>To perform a transaction, call <code>open()</code> to open the drawer, providing
 * the amount to be deposited. Then, use <code>addCount()</code> and <code>removeCount()</code>
 * to update the amounts of each denomination in the drawer as the customer provides cash and 
 * change is made and given back to the customer. Finally, call <code>close()</code> to
 * close the drawer. The class will verify that the amount in the drawer reflects the original 
 * amount plus the transaction amount
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 * @see edu.ksu.cs.cc410.restaurantregister.CashDenomination
 */
public class CashDrawer {
    
    private HashMap<CashDenomination, Integer> contents;
    private boolean open;
    private long updatedTotal;
    
    /**
     * Constructor to initialize the cash drawer.
     */
    public CashDrawer() {
        this.contents = new HashMap<>();
        for (CashDenomination denom : CashDenomination.values()) {
            contents.put(denom, 10);
        }
        this.open = false;
        this.updatedTotal = 0;
    }
    
    /**
     * Get the count of the denomination in the drawer.
     *
     * @param denom the denomination to check
     * @return the count of that denomination in the drawer
     * @throws IllegalStateException if the drawer is not closed
     */
    public int getCount(CashDenomination denom) {
        if (!this.open) {
            return this.contents.get(denom);
        } else {
            throw new IllegalStateException("Cash drawer must be closed to count.");
        }
    }
    
    /**
     * Get the total value of cash in the drawer.
     *
     * @return the total value of the cash.
     * @throws IllegalStateException if the drawer is not closed
     */
    public double getTotal() {
        if (!this.open) {
            double sum = 0.0;
            for (CashDenomination denom : CashDenomination.values()) {
                sum += this.contents.get(denom) * denom.getValue();
            }
            return sum;
        } else {
            throw new IllegalStateException("Cash drawer must be closed to count.");
        }
    }
    
    /**
     * Add to the count of the denomination in the drawer.
     *
     * @param denom the denomination to add to
     * @param count the count to add
     * @throws IllegalStateException if the drawer is not open
     */
    public void addCount(CashDenomination denom, int count) {
        if (this.open) {
            this.contents.put(denom, this.contents.get(denom) + count);
        } else {
            throw new IllegalStateException("Cash drawer must be open to modify.");
        }
    }
    
    /**
     * Remove from the count of the denomination in the drawer.
     *
     * @param denom the denomination to remove from
     * @param count the count to remove
     * @throws IllegalStateException if the drawer is not open
     */
    public void removeCount(CashDenomination denom, int count) {
        if (this.open) {
            this.contents.put(denom, this.contents.get(denom) - count);
        } else {
            throw new IllegalStateException("Cash drawer must be open to modify.");
        }
    }
    
    /**
     * Open the cash drawer.
     *
     * @param amount the amount to be deposited
     */
    public void open(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must not be negative.");
        }
        this.updatedTotal = Math.round((this.getTotal() + amount) * 100.0);
        this.open = true;
    }
    
    /**
     * Close the cash drawer.
     */
    public void close() {
        this.open = false;
        double total = this.getTotal();
        if (this.updatedTotal != Math.round(total * 100.0)) {
            this.open = true;
            throw new IllegalStateException("Cash drawer contents incorrect.");
        }
        this.updatedTotal = 0;
    }
    
}