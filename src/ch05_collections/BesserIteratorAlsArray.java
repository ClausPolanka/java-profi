package ch05_collections;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Beispielprogramm zur Demonstration der Unterschiede zwischen der Rückgabe eines Iterator bzw. Arrays
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class BesserIteratorAlsArray
{
    private static final String[] CAPITAL_CITIES = new String[] 
                                  { "Berlin", "London", "Paris", "Wien" };

    public static final String[] getCities()
    {
        return CAPITAL_CITIES;
    }

    public static final Iterator<String> getCityIterator()
    {
        return Arrays.asList(CAPITAL_CITIES).iterator();
    }

    public static void main(String[] args)
    {
        System.out.println("CITIES " + Arrays.toString(CAPITAL_CITIES));

        final String[] cities = getCities();
        // unerwartete Modifikation (auch im Original-Array!) 
        cities[1] = "London has changed!";

        System.out.println("CITIES " + Arrays.toString(cities));
        System.out.println("CITIES " + Arrays.toString(CAPITAL_CITIES));

        // keine Modifikation möglich 
        final Iterator<String> cityIterator = getCityIterator();
        while (cityIterator.hasNext())
            System.out.println(cityIterator.next());
    }
}
