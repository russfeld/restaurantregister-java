package edu.ksu.cs.cc410.restaurantregister;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import org.junit.jupiter.api.Test;

/** 
 * Card Reader Tests.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public class CashDrawerTest {
    
    /**
     * Test constructor.
     */
    @Test
    public void testConstructorPopulatesDrawer() {
        CashDrawer drawer = new CashDrawer();
        for (CashDenomination denom : CashDenomination.values()) {
            assertThat(drawer.getCount(denom), is(10));
        }
    }
    
    /**
     * Test total method.
     */
    @Test
    public void testTotalInitiallyCorrect() {
        CashDrawer drawer = new CashDrawer();
        double sum = 0.0;
        for (CashDenomination denom : CashDenomination.values()) {
            sum += denom.getValue();
        }
        assertThat(sum, is(187.91));
        assertThat(drawer.getTotal(), is(10 * sum));
    }
    
    /**
     * Test must open drawer to modify.
     */
    @Test
    public void testMustOpenDrawerToModify() {
        CashDrawer drawer = new CashDrawer();
        Exception e = assertThrows(IllegalStateException.class, () ->
                     drawer.addCount(CashDenomination.PENNY, 1));
        assertThat(e.getMessage(), is("Cash drawer must be open to modify."));
        e = assertThrows(IllegalStateException.class, () ->
                     drawer.removeCount(CashDenomination.PENNY, 1));
        assertThat(e.getMessage(), is("Cash drawer must be open to modify."));
    }
    
    /**
     * Test must close drawer to count.
     */
    @Test
    public void testMustCloseDrawerToCount() {
        CashDrawer drawer = new CashDrawer();
        drawer.open(0.0);
        Exception e = assertThrows(IllegalStateException.class, () ->
                     drawer.getCount(CashDenomination.PENNY));
        assertThat(e.getMessage(), is("Cash drawer must be closed to count."));
        e = assertThrows(IllegalStateException.class, () ->
                     drawer.getTotal());
        assertThat(e.getMessage(), is("Cash drawer must be closed to count."));
    }
    
    /**
     * Open drawer amount must not be negative.
     */
    @Test
    public void testOpenDrawerAmountNegative() {
        CashDrawer drawer = new CashDrawer();
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                    drawer.open(-0.01));
        assertThat(e.getMessage(), is("Amount must not be negative."));
    }
    
    /**
     * Cash amount changed must balance.
     */
    @Test
    public void testCashAmountChangedBalance() {
        CashDrawer drawer = new CashDrawer();
        final double total = drawer.getTotal();
        drawer.open(187.91);
        for (CashDenomination denom : CashDenomination.values()) {
            drawer.addCount(denom, 1);
        }
        assertDoesNotThrow(() -> drawer.close());
        assertThat(drawer.getTotal(), is(closeTo(total + 187.91, 0.001)));
    }
    
    /**
     * Cash amount changed must balance with change given.
     */
    @Test
    public void testCashAmountChangedMakeChange() {
        CashDrawer drawer = new CashDrawer();
        final double total = drawer.getTotal();
        drawer.open(187.91);
        double cashIn = 200.00;
        drawer.addCount(CashDenomination.HUNDRED_DOLLAR_BILL, 2);
        cashIn -= 0.01 * 4;
        drawer.removeCount(CashDenomination.PENNY, 4);
        cashIn -= 0.05 * 1;
        drawer.removeCount(CashDenomination.NICKEL, 1);
        cashIn -= 1.0 * 1;
        drawer.removeCount(CashDenomination.DOLLAR_COIN, 1);
        cashIn -= 1.0 * 1;
        drawer.removeCount(CashDenomination.ONE_DOLLAR_BILL, 1);
        cashIn -= 10.0 * 1;
        drawer.removeCount(CashDenomination.TEN_DOLLAR_BILL, 1);
        assertThat(cashIn, is(closeTo(187.91, 0.001)));
        assertDoesNotThrow(() -> drawer.close());
        assertThat(drawer.getTotal(), is(closeTo(total + 187.91, 0.001)));
    }
    
    /**
     * Cash amount changed will throw exception when incorrect.
     */
    @Test
    public void testCashAmountChangedWrongThrows() {
        CashDrawer drawer = new CashDrawer();
        drawer.open(187.91);
        for (CashDenomination denom : CashDenomination.values()) {
            drawer.addCount(denom, 1);
        }
        drawer.addCount(CashDenomination.PENNY, 1);
        Exception e = assertThrows(IllegalStateException.class, () -> drawer.close());
        assertThat(e.getMessage(), is("Cash drawer contents incorrect."));
    }
    
    
}