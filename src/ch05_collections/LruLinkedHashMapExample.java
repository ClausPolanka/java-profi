package ch05_collections;

import java.util.Collection;

/**
 * Beispielprogramm zur Demonstration einer größenbeschränkten HashMap mithilfe der 
 * Callback-Methode removeEldestEntry(), die das älteste Element basierend auf der 
 * Zugriffsreihenfolge bestimmt.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LruLinkedHashMapExample
{
    public static void main(String[] args)
    {
        // Größenbeschränkung auf vier Elemente 
        final int MAX_ELEMENT_COUNT = 4;
        final LruLinkedHashMap<String, Customer> map = new LruLinkedHashMap<String, Customer>(MAX_ELEMENT_COUNT);

        map.put("A. Mustermann", new Customer("A.", "Mustermann", "Stuhr", 16));
        map.put("B. Mustermann", new Customer("B.", "Mustermann", "Hamburg", 32));
        map.put("C. Mustermann", new Customer("C.", "Mustermann", "Stuhr", 64));
        map.put("M. Inden", new Customer("M.", "Inden", "Kiel", 32));

        printCustomerList("Initial", map.values());

        // Zugriff auf alle bis auf M. Inden           
        map.get("A. Mustermann");
        map.get("B. Mustermann");
        map.get("C. Mustermann");

        // Neuer Eintrag sollte M. Inden ersetzen           
        map.put("Dummy", new Customer("D.", "Dummy", "Kiel", 96));

        printCustomerList("Nach Zugriffen", map.values());
    }

    private static void printCustomerList(final String title, final Collection<Customer> customers)
    {
        System.out.println(title);
        for (final Customer customer : customers)
            System.out.println(customer);
    }
}
