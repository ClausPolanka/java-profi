package ch05_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration des Einsatz des universellen, zusammengesetzten Komparators
 * in Kombination mit dem ReverseComparator aus dem Collections-Framework
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonReverseComparatorExample
{
    public static void main(String[] args)
    {
        final Comparator<Person> ageComparator = new PersonAgeComparator();
        final Comparator<Person> nameComparator = new PersonNameComparator();

        final Comparator<Person> ageAndNameComparator = new PersonUniversalComparator(ageComparator, nameComparator);

        //  #####################################################            
        final List<Person> customers = new ArrayList<Person>();

        customers.add(new Person("S. Herr", "Stuhr", 28));
        customers.add(new Person("M. Frau", "Kiel", 33));
        customers.add(new Person("R. Muster", "Osterröhnfeld", 42));
        customers.add(new Person("H. Mann", "Oldenburg", 33));

        final List<Person> ageAndNameSortedList = new ArrayList<Person>(customers);
        Collections.sort(ageAndNameSortedList, ageAndNameComparator);
        printCustomerList("Sorted by Age And Name:", ageAndNameSortedList);

        // $\parbox{10cm}{\bfseries Definition des ReverseComparators }$
        final Comparator<Person> reverseAgeAndNameComparator = Collections.reverseOrder(ageAndNameComparator);
        Collections.sort(ageAndNameSortedList, reverseAgeAndNameComparator);
        printCustomerList("Reverse by Age And Name:", ageAndNameSortedList);
    }

    private static void printCustomerList(final String title, final List<Person> myCustomers)
    {
        System.out.println(title);
        for (final Person customer : myCustomers)
            System.out.println(customer);
    }
}