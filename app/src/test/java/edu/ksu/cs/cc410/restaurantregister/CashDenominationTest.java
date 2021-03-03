package edu.ksu.cs.cc410.restaurantregister;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

/** 
 * Cash Denomination Tests.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public class CashDenominationTest {
    
    /**
     * Test Penny.
     */
    @Test
    public void testPenny() {
        assertThat(CashDenomination.PENNY.toString(), is("Penny"));
        assertThat(CashDenomination.PENNY.getValue(), is(closeTo(0.01, 0.00001)));
    }
    
    /**
     * Test Nickel.
     */
    @Test
    public void testNickel() {
        assertThat(CashDenomination.NICKEL.toString(), is("Nickel"));
        assertThat(CashDenomination.NICKEL.getValue(), is(closeTo(0.05, 0.00001)));
    }
    
    /**
     * Test Dime.
     */
    @Test
    public void testDime() {
        assertThat(CashDenomination.DIME.toString(), is("Dime"));
        assertThat(CashDenomination.DIME.getValue(), is(closeTo(0.10, 0.00001)));
    }
    
    /**
     * Test Quarter.
     */
    @Test
    public void testQuarter() {
        assertThat(CashDenomination.QUARTER.toString(), is("Quarter"));
        assertThat(CashDenomination.QUARTER.getValue(), is(closeTo(0.25, 0.00001)));
    }
    
    /**
     * Test Half Dollar.
     */
    @Test
    public void testHalfDollar() {
        assertThat(CashDenomination.HALF_DOLLAR.toString(), is("Half Dollar"));
        assertThat(CashDenomination.HALF_DOLLAR.getValue(), is(closeTo(0.50, 0.00001)));
    }
    
    /**
     * Test Dollar Coin.
     */
    @Test
    public void testDollarCoin() {
        assertThat(CashDenomination.DOLLAR_COIN.toString(), is("Dollar Coin"));
        assertThat(CashDenomination.DOLLAR_COIN.getValue(), is(closeTo(1.00, 0.00001)));
    }
    
    /**
     * Test One Dollar Bill.
     */
    @Test
    public void testOneDollarBill() {
        assertThat(CashDenomination.ONE_DOLLAR_BILL.toString(), is("$1 Bill"));
        assertThat(CashDenomination.ONE_DOLLAR_BILL.getValue(), is(closeTo(1.00, 0.00001)));
    }
    
    /**
     * Test Five Dollar Bill.
     */
    @Test
    public void testFiveDollarBill() {
        assertThat(CashDenomination.FIVE_DOLLAR_BILL.toString(), is("$5 Bill"));
        assertThat(CashDenomination.FIVE_DOLLAR_BILL.getValue(), is(closeTo(5.00, 0.00001)));
    }
    
    /**
     * Test Ten Dollar Bill.
     */
    @Test
    public void testTenDollarBill() {
        assertThat(CashDenomination.TEN_DOLLAR_BILL.toString(), is("$10 Bill"));
        assertThat(CashDenomination.TEN_DOLLAR_BILL.getValue(), is(closeTo(10.00, 0.00001)));
    }
    
    /**
     * Test Twenty Dollar Bill.
     */
    @Test
    public void testTwentyDollarBill() {
        assertThat(CashDenomination.TWENTY_DOLLAR_BILL.toString(), is("$20 Bill"));
        assertThat(CashDenomination.TWENTY_DOLLAR_BILL.getValue(), is(closeTo(20.00, 0.00001)));
    }
    
    /**
     * Test Fifty Dollar Bill.
     */
    @Test
    public void testFiftyDollarBill() {
        assertThat(CashDenomination.FIFTY_DOLLAR_BILL.toString(), is("$50 Bill"));
        assertThat(CashDenomination.FIFTY_DOLLAR_BILL.getValue(), is(closeTo(50.00, 0.00001)));
    }
    
    /**
     * Test Hundred Dollar Bill.
     */
    @Test
    public void testHundredDollarBill() {
        assertThat(CashDenomination.HUNDRED_DOLLAR_BILL.toString(), is("$100 Bill"));
        assertThat(CashDenomination.HUNDRED_DOLLAR_BILL.getValue(), is(closeTo(100.00, 0.00001)));
    }
    
    /**
     * Test size of enum.
     */
    @Test
    public void testEnumSize() {
        assertThat(CashDenomination.values().length, is(12));
    }
}