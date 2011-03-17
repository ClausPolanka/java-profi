package ch05_collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Beispielklasse Komparator für Person-Objekte, der sich aus verschiedenen Komparatoren zusammensetzen kann
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonUniversalComparator implements Comparator<Person>
{
    private final List<Comparator<Person>> comparators = new ArrayList<Comparator<Person>>();

    public PersonUniversalComparator(final List<Comparator<Person>> comparators)
    {
        comparators.addAll(comparators);
    }

    // Convenience
    public PersonUniversalComparator(final Comparator<Person> comparator1, 
                                     final Comparator<Person> comparator2)
    {
        comparators.add(comparator1);
        comparators.add(comparator2);
    }

    public int compare(final Person person1, final Person person2)
    {
        int ret = 0;

        for (final Comparator<Person> comparator : comparators)
        {
            ret = comparator.compare(person1, person2);
            if (ret != 0)
                break;
        }

        return ret;
    }
}