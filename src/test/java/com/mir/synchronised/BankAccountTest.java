package com.mir.synchronised;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by Mir on 08/09/2016.
 */
public class BankAccountTest {

    static private BankAccount acc;

    Date now = new Date();

    @BeforeClass
    public static void setup () {
        acc = new BankAccount(0);
    }

    @Test
    public void depositAndWithdrwalTest () {
        final double DEPOSIT_AMOUNT = 100.0;
        final double WITHDRWAL_AMOUNT = 100.0;
        final int REPEATS = 0;

        for (int i = 0; i < REPEATS; ++i) {
            Thread t2 = new Thread (new WithdrwalJob(acc, WITHDRWAL_AMOUNT));
            t2.start();

            Thread t1 = new Thread(new DepositJob(acc, DEPOSIT_AMOUNT));
            t1.start();
        }
    }

    @Test
    public void testString () {
        String s= "Hello World!";
        char[] s1 = s.toCharArray();
        System.out.println("String is " + String.valueOf(s1));
    }

    @Test
    public void testStandardOut () {

        PrintStream stdout = System.out;

        // redirect output stream to a Byte Array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut (ps);

        // run test
        System.out.print ("Hello World");

        // reset output stream
        System.setOut(stdout);

        // validate test result
        String result = null;
        try {
            result = baos.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals ("Hello World", result);
    }

    @AfterClass
    public static void tidyUp () {
        System.out.println("\nTotal balance is: " + acc.getBalance());
    }

    /**
     * Created by Mir on 10/09/2016.
     */
    public static class SomeRandomTests {
    }
}