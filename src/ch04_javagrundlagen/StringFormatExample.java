package ch04_javagrundlagen;

import java.io.IOException;

/**
 * Beispielprogramm zur Demonstration der format()-Methode von String-Objekten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringFormatExample
{
    public static void main(final String[] args) throws IOException
    {
        final Object[] sampleArgs = { "pi", 3.1415, 12345 };
        final String str = String.format("%S='%2.5f' Zahl='%,d'", sampleArgs);

        System.out.println(str); // PI='3,14150' Zahl='12.345' 
    }
    
    private StringFormatExample()
    {
    }
}