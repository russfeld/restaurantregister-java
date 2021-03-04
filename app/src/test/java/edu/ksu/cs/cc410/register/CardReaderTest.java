package edu.ksu.cs.cc410.register;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

/** 
 * Card Reader Tests.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CardReaderTest {
    
    @Mock
    Random random;
    @InjectMocks
    CardReader reader;
    
    /**
     * Test declined result.
     */
    @Test
    public void testRunCardDeclined() {
        when(random.nextInt(anyInt())).thenReturn(0);
        assertThat(reader.runCard(), is(CardTransactionResult.DECLINED));
    }
    
    /**
     * Test read error result.
     */
    @Test
    public void testRunCardReadError() {
        when(random.nextInt(anyInt())).thenReturn(1);
        assertThat(reader.runCard(), is(CardTransactionResult.READ_ERROR));
    }
    
    /**
     * Test insufficient funds result.
     */
    @Test
    public void testRunCardInsufficientFunds() {
        when(random.nextInt(anyInt())).thenReturn(2);
        assertThat(reader.runCard(), is(CardTransactionResult.INSUFFICIENT_FUNDS));
    }
    
    /**
     * Test incorrect pin result.
     */
    @Test
    public void testRunCardIncorrectPin() {
        when(random.nextInt(anyInt())).thenReturn(3);
        assertThat(reader.runCard(), is(CardTransactionResult.INCORRECT_PIN));
    }
    
    /**
     * Test approved result.
     */
    @Test
    public void testRunCardApproved() {
        for (int i = 4; i < 10; i++) {
            when(random.nextInt(anyInt())).thenReturn(i);
            assertThat(reader.runCard(), is(CardTransactionResult.APPROVED));
        }
    }
}