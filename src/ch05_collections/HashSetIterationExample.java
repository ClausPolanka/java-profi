package ch05_collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Beispielprogramm zur Demonstration der Iteration über die Elemente eines HashSets.
 * Scheinbar wird eine Ordnung hergestellt, tatsächlich ist dies eher zufällig.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class HashSetIterationExample
{

    public static void main(String[] args)
    {
        final Integer[] ints = new Integer[] { 3, 2, 1 };
        final Set<Integer> numberSet = new HashSet<Integer>(Arrays.asList(ints));

        System.out.println("Initial: " + numberSet); // 1, 2, 3
    }

    private HashSetIterationExample()
    {
    }
}
