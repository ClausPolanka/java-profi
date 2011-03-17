package ch04_javagrundlagen;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Beispielprogramm zur Demonstration verschiedener String-Encodings
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringEncodings
{
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        final byte[] def = "ÄÖÜ To".getBytes();
        final byte[] dos = "ÄÖÜ To".getBytes("Cp850");
        final byte[] iso = "ÄÖÜ To".getBytes("ISO-8859-1");
        final byte[] utf8 = "ÄÖÜ To".getBytes("UTF-8");

        printBytes(def); // "Default:" [-60, -42, -36, 32, 84, 111]
        printBytes(dos); // "Cp850": [-114, -103, -102, 32, 84, 111]
        printBytes(iso); // "ISO-8859-1": [-60, -42, -36, 32, 84, 111]
        printBytes(utf8); // "UTF-8": [-61, -124, -61, -106, -61, -100, 32, 84, 111]
    }

    private static void printBytes(final byte[] bytes)
    {
        System.out.println(Arrays.toString(bytes));
    }

    private StringEncodings()
    {
    }
}
