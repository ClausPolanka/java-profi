package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration von Merkwürdigkeiten beim Auto-Boxing
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AutoBoxingCacheExample
{
    public static void main(final String[] args)
    {
        // C = Cache, N = Neues Objekt 
        final Integer i1 = 7; // Auto-Boxing => C 
        final Integer i2 = 4711; // Auto-Boxing => N 

        System.out.println(i1 == new Integer(7)); //  false, C != N 
        System.out.println(i2 == new Integer(4711)); // false, N != N 
        System.out.println(i1 == Integer.valueOf(7)); // TRUE !!!, C == C  
        System.out.println(i2 == Integer.valueOf(4711)); // false, N != N 
    }

    private AutoBoxingCacheExample()
    {
    }
}
