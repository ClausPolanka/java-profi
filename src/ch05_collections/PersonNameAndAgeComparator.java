package ch05_collections;

import java.util.Comparator;

/**
 * Beispielklasse Komparator für Person-Objekte nach Name und Alter
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonNameAndAgeComparator implements Comparator<Person>
{
    public int compare(final Person person1, final Person person2)
    {
        int ret = person1.getName().compareTo(person2.getName());

        if (ret == 0)
        {
            if (person1.getAge() < person2.getAge())
            {
                ret = -1;
            }
            if (person1.getAge() > person2.getAge())
            {
                ret = 1;
            }
        }

        return ret;
    }
}
