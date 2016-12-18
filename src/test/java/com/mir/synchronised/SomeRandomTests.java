package com.mir.synchronised;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Mir on 10/09/2016.
 */
public class SomeRandomTests  {

    @Ignore
    @Test
    public void test () throws IOException {
        String address = "http://bbc.co.uk";
        URL locator = new URL (address);
        Scanner in = new Scanner (locator.openStream());
        in.useDelimiter("[^A-Za-z]+");

        while (in.hasNext()) {
            String line = in.next();
            System.out.println (line);
        }
        in.close();
    }

    @Test
    public void test1 () {
        String str = "Hello 1 2 this is a TeST [] , + ::: BYE";
        Scanner in = new Scanner (str);
        in.useDelimiter("[^a-z]+");

        while (in.hasNext()) {
            System.out.println (in.next());
        }
    }

    @Ignore
    @Test
    public void test2 () {


    }


}
