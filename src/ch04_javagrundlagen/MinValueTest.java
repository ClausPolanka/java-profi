package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration von Merkwürdigkeiten von MIN_VALUE
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class MinValueTest
{
    public static void main(final String[] args)
    {
        System.out.println(Byte.MIN_VALUE == -Byte.MIN_VALUE); //  false 
        System.out.println(Short.MIN_VALUE == -Short.MIN_VALUE); // false 
        System.out.println(Integer.MIN_VALUE == -Integer.MIN_VALUE); // true 
        System.out.println(Long.MIN_VALUE == -Long.MIN_VALUE); // true 
    }

    private MinValueTest()
    {
    }
}
