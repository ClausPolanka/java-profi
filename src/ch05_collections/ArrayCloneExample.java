package ch05_collections;

import java.util.Arrays;

/**
 * Beispielprogramm zur Demonstration von Array-Kopien
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ArrayCloneExample
{
    private static final String[][] original = { { "0.0", "0.1" }, 
                                                 { "1.0", "1.1" } };
    
    private static final String[][] clone    = original.clone();

    public static void main(String[] args)
    {
        // Protokolliere Ausgangszustand 
        System.out.println("before modification:");
        System.out.println("original: " + Arrays.deepToString(original));
        System.out.println("clone:    " + Arrays.deepToString(clone));

        //  Verändere Einträge im Clone 
        System.out.println("after modification:");
        clone[0][1] = "New 0.1";
        clone[1] = new String[] { "New Sub-Array 1" };

        System.out.println("original: " + Arrays.deepToString(original));
        System.out.println("clone:    " + Arrays.deepToString(clone));
    }
    
    private ArrayCloneExample()
    {
    }
}