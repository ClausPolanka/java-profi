package ch05_collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Beispielprogramm zur Demonstration der Iteration �ber die Elemente eines HashSets.
 * Scheinbar wird eine Ordnung hergestellt, tats�chlich ist dies eher zuf�llig.
 * In diesem Beispiel wird diese Tatsache offensichtlich.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class HashSetIterationExample2
{
    public static void main(String[] args)
    {
        final Integer[] ints = new Integer[] { 3, 2, 1 };
        final Set<Integer> numberSet = new HashSet<Integer>(Arrays.asList(ints));

        System.out.println("Initial: " + numberSet); // 1, 2, 3 

        final Integer[] moreInts = new Integer[] { 11, 22, 33 };
        numberSet.addAll(Arrays.asList(moreInts));
        System.out.println("Add: " + numberSet); // 1, 2, 33, 3, 22, 11 
    }

    private HashSetIterationExample2()
    {
    }
}
