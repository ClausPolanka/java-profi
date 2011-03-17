package ch05_collections;

import java.util.Comparator;

/**
 * Beispielklasse Komparator für Person-Objekte nach Alter 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonAgeComparator implements Comparator<Person>
{
    public int compare(final Person person1, final Person person2)
    {
        if (person1.getAge() < person2.getAge())
        {
            return -1;
        }
        if (person1.getAge() > person2.getAge())
        {
            return 1;
        }

        return 0;
    }
}