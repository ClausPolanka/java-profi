package ch05_collections;

import java.util.Comparator;

/**
 * Beispielklasse Komparator für Person-Objekte nach Alter und Name
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonAgeAndNameComparator3 implements Comparator<Person>
{
    private final Comparator<Person> ageComparator  = new PersonAgeComparator();
    private final Comparator<Person> nameComparator = new PersonNameComparator();

    public int compare(final Person person1, final Person person2)
    {
        int nRet = ageComparator.compare(person1, person2);

        if (nRet == 0)
        {
            nRet = nameComparator.compare(person1, person2);
        }

        return nRet;
    }
}