package ch05_collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Beispielprogramm zur Demonstration der typsicheren Iteration über ein String[],
 * welches zuvor in eine Collection<Sting> gewandelt wird.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class IterationExample
{
    public static void main(final String[] args)
    {
        final String[] namesArray = { "Durchlauf", "mit", "Iterator" };
        final Collection<String> names = Arrays.asList(namesArray);

        final Iterator<String> itNames = names.iterator();
        while (itNames.hasNext())
        {
            System.out.println(itNames.next());
        }
    }

    private IterationExample()
    {
    }
}