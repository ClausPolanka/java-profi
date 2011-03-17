package ch05_collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration der Binärsuche 
 * (mit natürlicher Ordnung und separat angegebenem Comparator)
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class BinarySearchExample
{
    public static void main(String[] args)
    {
        final List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);

        // Suche nach 7 und 14 
        System.out.println(primes); // [2, 3, 5, 7, 11, 13, 17]
        System.out.println(Collections.binarySearch(primes, 7)); // 3
        System.out.println(Collections.binarySearch(primes, 14)); // -7

        // Anderes Sortierkriterium anwenden, um Versagen der Suche zu provozieren 
        Collections.sort(primes, Collections.reverseOrder());
        System.out.println(primes); // [17, 13, 11, 7, 5, 3, 2]
        System.out.println(Collections.binarySearch(primes, 7)); // -1
        System.out.println(Collections.binarySearch(primes, 14)); // -9    
    }

    private BinarySearchExample()
    {
    }
}
