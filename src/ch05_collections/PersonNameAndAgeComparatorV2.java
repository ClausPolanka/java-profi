package ch05_collections;

import java.util.Comparator;

/**
 * Beispielklasse Komparator für Person-Objekte nach Name und Alter
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonNameAndAgeComparatorV2 implements Comparator<Person>
{
    private final Comparator<Person> ageComparator  = new PersonAgeComparator();
    private final Comparator<Person> nameComparator = new PersonNameComparator();

    public int compare(final Person person1, final Person person2)
    {
        int ret = nameComparator.compare(person1, person2);

        if (ret == 0)
        {
            ret = ageComparator.compare(person1, person2);
        }

        return ret;
    }
}