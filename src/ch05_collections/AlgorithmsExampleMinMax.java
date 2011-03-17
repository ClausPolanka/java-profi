package ch05_collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration der Methoden min() und max()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AlgorithmsExampleMinMax
{
    private static final Person MALE     = new Person("Male", "Bremen", 42);
    private static final Person FEMALE   = new Person("Female", "New York", 43);
    private static final Person MISTER_X = new Person("Mister X", "Sydney", 44);

    public static void main(String[] args)
    {
        final List<Person> persons = initPersonList();

        // Bestimmung min() mit Comparable 
        final Person min = Collections.min(persons);
        System.out.println("Min: " + min);

        // Bestimmung max() mit Comparable 
        final Person max = Collections.max(persons);
        System.out.println("Max: " + max);

        // Bestimmung max() mit eigenem Komparator 
        final Comparator<Person> cityComparator = new Comparator<Person>()
        {
            public int compare(final Person person1, final Person person2)
            {
                return person1.getCity().compareTo(person2.getCity());
            }
        };
        final Person maxCity = Collections.max(persons, cityComparator);
        System.out.println("Max city: " + maxCity);
    }

    private static List<Person> initPersonList()
    {
        final List<Person> maleList = Collections.nCopies(2, MALE);
        final List<Person> femaleList = Collections.nCopies(3, FEMALE);

        final List<Person> persons = new LinkedList<Person>();
        persons.addAll(maleList);
        persons.addAll(femaleList);
        persons.add(MISTER_X);
        return persons;
    }

    private AlgorithmsExampleMinMax()
    {
    }
}