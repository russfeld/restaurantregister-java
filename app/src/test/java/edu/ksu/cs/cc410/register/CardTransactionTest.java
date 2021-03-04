package edu.ksu.cs.cc410.register;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

/** 
 * Card Transaction Tests.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public class CardTransactionTest {
    
    /**
     * Test Approved.
     */
    @Test
    public void testApproved() {
        assertThat(CardTransactionResult.APPROVED.toString(), is("Approved"));
    }
    
    /**
     * Test Declined.
     */
    @Test
    public void testDeclined() {
        assertThat(CardTransactionResult.DECLINED.toString(), is("Declined"));
    }
    
    /**
     * Test Read Error.
     */
    @Test
    public void testReadError() {
        assertThat(CardTransactionResult.READ_ERROR.toString(), is("Card Read Error"));
    }
    
    /**
     * Test Insufficient Funds.
     */
    @Test
    public void testInsufficientFunds() {
        assertThat(CardTransactionResult.INSUFFICIENT_FUNDS.toString(), is("Insufficient Funds"));
    }
    
    /**
     * Test Incorrect Pin.
     */
    @Test
    public void testIncorrectPin() {
        assertThat(CardTransactionResult.INCORRECT_PIN.toString(), is("Incorrect PIN"));
    }
    
    /**
     * Test size of enum.
     */
    @Test
    public void testEnumSize() {
        assertThat(CardTransactionResult.values().length, is(5));
    }
}