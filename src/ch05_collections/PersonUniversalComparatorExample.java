package ch05_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration des Einsatz des universellen, zusammengesetzten Komparators
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonUniversalComparatorExample
{
    public static void main(String[] args)
    {
        // Bereitstellen der Daten      
        final List<Person> customers = new ArrayList<Person>();

        customers.add(new Person("S. Herr", "Stuhr", 28));
        customers.add(new Person("M. Frau", "Kiel", 33));
        customers.add(new Person("R. Muster", "Osterr�nfeld", 42));
        customers.add(new Person("H. Mann", "Oldenburg", 33));

        // Definition von Einzel-Comparatoren 
        final Comparator<Person> ageComparator = new PersonAgeComparator();
        final Comparator<Person> nameComparator = new PersonNameComparator();

        // Definition zusammengesetzter Comparatoren 
        final Comparator<Person> ageAndNameComparator = new PersonUniversalComparator(ageComparator, nameComparator);
        final Comparator<Person> nameAndAgeComparator = new PersonUniversalComparator(nameComparator, ageComparator);

        // Einsatz der Comparatoren
        final List<Person> ageAndNameSortedList = new ArrayList<Person>(customers);
        Collections.sort(ageAndNameSortedList, ageAndNameComparator);
        printCustomerList("Sorted by Age And Name:", ageAndNameSortedList);

        final List<Person> nameAndAgeSortedList = new ArrayList<Person>(customers);
        Collections.sort(nameAndAgeSortedList, nameAndAgeComparator);
        printCustomerList("Sorted by Name And Age:", nameAndAgeSortedList);
    }

    private static void printCustomerList(final String title, final List<Person> myCustomers)
    {
        System.out.println(title);
        for (final Person customer : myCustomers)
            System.out.println(customer);
    }
}