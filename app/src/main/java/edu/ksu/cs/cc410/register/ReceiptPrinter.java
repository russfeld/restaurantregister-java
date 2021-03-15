package edu.ksu.cs.cc410.register;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.time.LocalDateTime;   
import java.time.format.DateTimeFormatter;  
 

/**
 * Class to print a receipt.
 *
 * <p>Typical usage is to instantiate a receipt printer along with the application.
 *
 * <p>To print a receipt, call the <code>startReceipt()</code> method first, then 
 * print each line using the <code>printLine()</code> method. Each line on the receipt
 * must be no longer than 40 characters. Newlines will be inserted by the class. Finally, 
 * call <code>endReceipt()</code> to finalize the receipt before starting a new one.
 *
 * <p>The <code>close()</code> method should be called when the application is closed.
 *
 * <p>Any <code>IOException</code> thrown by the underlying file writer will be thrown.
 *
 * @author Russell Feldhausen russfeld@ksu.edu
 * @version 0.1
 */
public class ReceiptPrinter {
    
    private DateTimeFormatter format;
    private BufferedWriter writer;
    private boolean started;
    
    /**
     * Constructor.
     *
     * @throws IOException if the file cannot be opened
     */
    public ReceiptPrinter() throws IOException {
        this.format = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss");
        this.writer = new BufferedWriter(new FileWriter("receipt.txt", true));
        this.started = false;
    }
    
    /**
     * Start printing a receipt.
     *
     * @throws IOException if the file cannot be written
     * @throws IllegalStateException if the receipt has already been started
     */
    public void startReceipt() throws IOException {
        if (this.started) {
            throw new IllegalStateException("Receipt already started.");
        }
        this.started = true;
        writer.write("========================================\n");
        writer.write("*** RECEIPT START: " + this.format.format(LocalDateTime.now()) + " ***\n");
        writer.write("========================================\n");
    }
    
    /**
     * Add a line to the receipt.
     *
     * @param text the line to print
     * @throws IllegalArgumentException if the text is longer than 40 chars
     * @throws IllegalStateException if the receipt is not started
     * @throws IOException if the file cannot be written
     */
    public void printLine(String text) throws IOException {
        if (!this.started) {
            throw new IllegalStateException("Receipt not started.");
        }
        if (text.length() > 40) {
            throw new IllegalArgumentException("Text longer than 40 characters.");
        }
        this.writer.write(text + "\n");
    }
    
    /**
     * Ends a receipt.
     *
     * @throws IllegalStateException if the receipt is not started
     * @throws IOException if the file cannot be written.
     */
    public void endReceipt() throws IOException {
        if (!this.started) {
            throw new IllegalStateException("Receipt not started.");
        }
        this.started = false;
        writer.write("========================================\n");
        writer.write("**** RECEIPT END: " + this.format.format(LocalDateTime.now()) + " ****\n");
        writer.write("========================================\n");
        writer.flush();
    }
    
    /**
     * Closes the file.
     *
     * @throws IOException if the file cannot be closed.
     */
    public void close() throws IOException {
        writer.close();
    }
    
}