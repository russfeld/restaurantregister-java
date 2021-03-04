package edu.ksu.cs.cc410.register;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedWriter;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.time.format.DateTimeFormatter;  
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

/** 
 * Receipt Printer Tests.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class ReceiptPrinterTest {
    
    @Mock
    DateTimeFormatter mockFormat;
    @Mock
    BufferedWriter mockWriter;
    @InjectMocks
    ReceiptPrinter printer;
    
    /**
     * Start receipt throws if already started.
     */
    @Test
    public void testStartReceiptThrowsIfStarted() throws Exception {
        printer.startReceipt();
        Exception e = assertThrows(IllegalStateException.class, () -> printer.startReceipt());
        assertThat(e.getMessage(), is("Receipt already started."));
    }
    
    /**
     * Print line throws if not started.
     */
    @Test
    public void testPrintLineThrowsIfNotStarted() throws Exception {
        Exception e = assertThrows(IllegalStateException.class, () -> printer.printLine(""));
        assertThat(e.getMessage(), is("Receipt not started."));
    }
    
    /**
     * Print line throws if line too long.
     */
    @Test
    public void testPrintLineThrowsIfLineTooLong() throws Exception {
        printer.startReceipt();
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                    printer.printLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertThat(e.getMessage(), is("Text longer than 40 characters."));
    }
    
    /**
     * End receipt throws if not started.
     */
    @Test
    public void testEndReceiptThrowsIfNotStarted() throws Exception {
        Exception e = assertThrows(IllegalStateException.class, () -> printer.endReceipt());
        assertThat(e.getMessage(), is("Receipt not started."));
    }
    
    /**
     * Close calls close on writer.
     */
    @Test
    public void testCloseCallsClose() throws Exception {
        printer.close();
        verify(mockWriter, times(1)).close();
    }
    
    /**
     * Start receipt prints correctly.
     */
    @Test
    public void testStartReceiptPrintsCorrectly() throws Exception {
        when(mockFormat.format(any())).thenReturn("20010101-01:01:01");
        InOrder order = inOrder(mockWriter);
        printer.startReceipt();
        order.verify(mockWriter).write("========================================\n");
        order.verify(mockWriter).write("*** RECEIPT START: 20010101-01:01:01 ***\n");
        order.verify(mockWriter).write("========================================\n");
    }
    
    /**
     * Print line prints correctly.
     */
    @Test
    public void testPrintLinePrintsCorrectly() throws Exception {
        printer.startReceipt();
        printer.printLine("This is a test line.");
        verify(mockWriter, times(1)).write("This is a test line.\n");
    }
    
    /**
     * End receipt prints correctly.
     */
    @Test
    public void testEndReceiptPrintsCorrectly() throws Exception {
        when(mockFormat.format(any())).thenReturn("20010101-01:01:01");
        printer.startReceipt();
        InOrder order = inOrder(mockWriter);
        printer.endReceipt();
        order.verify(mockWriter).write("========================================\n");
        order.verify(mockWriter).write("**** RECEIPT END: 20010101-01:01:01 ****\n");
        order.verify(mockWriter).write("========================================\n");
    }
}