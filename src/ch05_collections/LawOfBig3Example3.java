package ch05_collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Beispielprogramm zur Demonstration des Zusammenspiels der Methoden
 * equals(), hashCode() und compareTo() bei der Speicherung in HashSets und TreeSets.
 * 
 * Variante 3: compareTo(), equals() und hashCode() implementiert
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LawOfBig3Example3
{
    private static class SimplePerson implements Comparable<SimplePerson>
    {
        final String name;

        SimplePerson(final String name)
        {
            this.name = name;
        }

        @Override
        public int compareTo(final SimplePerson o)
        {
            return name.compareTo(o.name);
        }

        @Override
        public boolean equals(final Object other)
        {
            if (other == null) // Null-Akzeptanz               
                return false;

            if (this == other) // Reflexivitšt      
                return true;

            if (this.getClass() != other.getClass()) // Typgleichheit      
                return false;

            final SimplePerson otherPerson = (SimplePerson) other;
            return compareTo(otherPerson) == 0; // Vergleich mit compateTo() 
        }

        @Override
        public int hashCode()
        {
            return name.hashCode();
        }
    }

    public static void main(String[] args)
    {
        // equals + hashCode basiert auf Namen
        final Set<SimplePerson> hashSet = new HashSet<SimplePerson>();
        hashSet.add(new SimplePerson("Test"));
        hashSet.add(new SimplePerson("Test"));

        System.out.println("HashSet3 size = " + hashSet.size()); // Size = 1

        final Set<SimplePerson> treeSet = new TreeSet<SimplePerson>();
        treeSet.add(new SimplePerson("Test"));
        treeSet.add(new SimplePerson("Test"));

        System.out.println("TreeSet3 size = " + treeSet.size()); // Size = 1  
    }

    private LawOfBig3Example3()
    {
    }
}