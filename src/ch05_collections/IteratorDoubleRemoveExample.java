package ch05_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration des L�schens aus einer Collection w�hrend einer Iteration.
 * durch Aufruf der Methode remove() des Iterators.
 * Allerdings wird hier bewu�t ein zweiter Aufruf durchgef�hrt ohne zuvor next() aufzurufen.
 * Das f�hrt zu einer java.lang.IllegalStateException.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class IteratorDoubleRemoveExample
{
    private static void removeEntriesWithPrefix(final List<String> entries, final String prefix)
    {
        final Iterator<String> it = entries.iterator();
        while (it.hasNext())
        {
            final String name = it.next();
            if (name.startsWith(prefix))
            {
                it.remove(); // KORREKT: Zugriff �ber remove() des Iterators
                it.remove(); // PROBLEM: it.next()-Aufruf fehlt
            }
        }
    }

    public static void main(final String[] args)
    {
        final String[] names = { "Andreas", "Carsten", "Clemens", "Merten", "Michael", "Peter" };

        final List<String> namesList = new ArrayList<String>();
        namesList.addAll(Arrays.asList(names));

        removeEntriesWithPrefix(namesList, "M");
        System.out.println(namesList);
    }
}
