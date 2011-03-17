package ch05_collections;

import java.util.Collection;

/**
 * Beispielprogramm zur Demonstration einer größenbeschränkten HashMap 
 * mithilfe der Callback-Methode removeEldestEntry().
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FixedSizeLinkedHashMapExample
{
    public static void main(final String[] args)
    {
        // Größenbeschränkung auf drei Elemente 
        final int MAX_ELEMENT_COUNT = 3;
        final FixedSizeLinkedHashMap<String, Customer> map = 
                                    new FixedSizeLinkedHashMap<String, Customer>(MAX_ELEMENT_COUNT);

        // Initial befüllen
        map.put("Erster", new Customer("E.", "Erster", "Stuhr", 11));
        map.put("Zweiter", new Customer("Z.", "Zweiter", "Hamburg", 22));
        map.put("M. Inden", new Customer("M.", "Inden", "Aachen", 39));
        printCustomerList("Initial", map.values());

        // Änderungen durchführen und ausgeben
        map.put("New1", new Customer("Mr.", "New1", "London", 44));
        printCustomerList("After insertion of 'Mr. New1'", map.values());

        map.put("New2", new Customer("Mr.", "New2", "San Francisco", 55));
        printCustomerList("After insertion of 'Mr. New2'", map.values());
    }

    private static void printCustomerList(final String title, final Collection<Customer> customers)
    {
        System.out.println(title);
        for (final Customer customer : customers)
            System.out.println(customer);
    }

    private FixedSizeLinkedHashMapExample()
    {
    }
}
