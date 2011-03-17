package ch05_collections;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Beispielprogramm zur Demonstration der Zugriffsmethoden der Klasse TreeMap. 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class TreeMapExample
{
    public static void main(String[] args)
    {
        final Customer max = new Customer("Max", "Muster", "Bremen", 47);
        final Customer moritz = new Customer("Moritz", "Muster", "Aachen", 39);

        final NavigableMap<String, Customer> customers = new TreeMap<String, Customer>();
        customers.put("Max", max);
        customers.put("Moritz", moritz);

        System.out.println("floor   Ma: " + customers.floorKey("Ma"));
        System.out.println("higher  Ma: " + customers.higherKey("Ma"));
        System.out.println("lower   Mc: " + customers.lowerKey("Mc"));
        System.out.println("ceiling Mc: " + customers.ceilingEntry("Mc"));
    }
    
    private TreeMapExample()
    {
    }
}
