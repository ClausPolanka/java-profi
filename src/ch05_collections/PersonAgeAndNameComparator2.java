package ch05_collections;

import java.util.Comparator;

/**
 * Beispielklasse Komparator für Person-Objekte nach Alter und Name
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonAgeAndNameComparator2 implements Comparator<Person>
{
    public int compare(final Person person1, final Person person2)
    {
        int nRet = new PersonAgeComparator().compare(person1, person2);

        if (nRet == 0)
        {
            nRet = new PersonNameComparator().compare(person1, person2);
        }

        return nRet;
    }
}