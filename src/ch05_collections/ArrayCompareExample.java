package ch05_collections;

import java.util.Arrays;

/**
 * Beispielprogramm zur Demonstration von Array-Vergleichen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ArrayCompareExample
{
    public static void main(String[] args)
    {
        final String[][] array1 = { { "0.0", "0.1" }, { "1.0", "1.1" } };
        final String[][] array2 = { { "0.0", "0.1" }, { "1.0", "1.1" } };

        final boolean arrayEquals = Arrays.equals(array1, array2);
        final boolean deepEquals = Arrays.deepEquals(array1, array2);

        System.out.println("equals = " + arrayEquals); // false !!! 
        System.out.println("deepEquals = " + deepEquals); // true 
    }

    private ArrayCompareExample()
    {
    }
}