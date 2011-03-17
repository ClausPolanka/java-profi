package ch03_oodesign.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Beispiel für Typunsicherheiten ohne den Einsatz von Generics
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class OldStyleList
{
    public static void main(String[] args)
    {
        // Achtung: Nur zur Demonstration auf Generics verzichtet 
        final List personList = new ArrayList();
        personList.add(new Person("Max", new Date(), "Musterstadt"));
        personList.add(new Person("Moritz", new Date(), "Musterstadt"));
        personList.add(new Dog("Sarah vom Auetal"));

        for (int i = 0; i < personList.size(); i++)
        {
            // Explizite Typumwandlung notwendig zum Methodenaufruf
            final Person person = (Person) personList.get(i);
            System.out.println(person.getName() + " aus " + person.getCity());
        }
    }

    private OldStyleList()
    {
    }
}

class Dog
{
    private final String name;

    public Dog(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Dog [name='" + name + "']";
    }
}
