package ch03_oodesign.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Beispiel für Typsicherheit durch den Einsatz von Generics
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class NewStyleList
{
    public static void main(String[] args)
    {
        final List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Max", new Date(), "Musterstadt"));
        personList.add(new Person("Moritz", new Date(), "Musterstadt"));
        // personList.add(new Dog(''Sarah vom Auetal''));  // Compile-Error

        for (int i = 0; i < personList.size(); i++)
        {
            final Person person = personList.get(i);
            System.out.println(person.getName() + " aus " + person.getCity());
        }
    }

    private NewStyleList()
    {
    }
}