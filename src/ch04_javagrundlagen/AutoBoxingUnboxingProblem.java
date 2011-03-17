package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration von Merkwürdigkeiten beim Auto-Unboxing
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AutoBoxingUnboxingProblem
{
    public static void main(final String[] args)
    {
        final Integer i1 = new Integer(1);
        final Integer i2 = new Integer(1);

        System.out.println(i1 >= i2); // true  
        System.out.println(i1 <= i2); // true 
        System.out.println(i1 == i2); // false
    }

    private AutoBoxingUnboxingProblem()
    {
    }
}