package ch05_collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Beispielklasse Komparator für Person-Objekte nach Alter und Name
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonAgeAndNameComparatorChained implements Comparator<Person>
{
    private final Comparator<Person>       ageComparator  = new PersonAgeComparator();
    private final Comparator<Person>       nameComparator = new PersonNameComparator();

    private final List<Comparator<Person>> comparators    = new ArrayList<Comparator<Person>>();

    public PersonAgeAndNameComparatorChained()
    {
        comparators.add(ageComparator);
        comparators.add(nameComparator);
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