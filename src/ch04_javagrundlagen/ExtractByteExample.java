package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration der Extraktion von byte-Werten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ExtractByteExample
{
    public static void main(final String[] args)
    {
        final int value = 1 | 8 | 256 | 32768;
        System.out.println("Value = " + value);
        System.out.println("Binary Value = " + Integer.toBinaryString(value));

        final byte byte0 = extractByteValue(value);
        System.out.println("byte0 = " + byte0);

        System.out.println("value>>8 = " + (value >> 8));
        final byte byte1 = extractByteValue(value >> 8);
        System.out.println("byte1 = " + byte1);
    }

    public static byte extractByteValue(final int value)
    {
        return (byte) (value & 255);
    }
    
    private ExtractByteExample()
    {
    }
}