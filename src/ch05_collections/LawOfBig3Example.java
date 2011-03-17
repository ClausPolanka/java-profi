package ch05_collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Beispielprogramm zur Demonstration des Zusammenspiels der Methoden
 * equals(), hashCode() und compareTo() bei der Speicherung in HashSets und TreeSets.
 * 
 * Variante 1: nur compareTo() implementiert
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LawOfBig3Example
{
    private static class SimplePerson implements Comparable<SimplePerson>
    {
        private final String name;

        SimplePerson(final String name)
        {
            this.name = name;
        }

        @Override
        public int compareTo(final SimplePerson other)
        {
            return name.compareTo(other.name);
        }
    }

    public static void main(String[] args)
    {
        final Set<SimplePerson> hashSet = new HashSet<SimplePerson>();
        hashSet.add(new SimplePerson("Test"));
        hashSet.add(new SimplePerson("Test"));

        System.out.println("HashSet size = " + hashSet.size()); // Size = 2 

        final Set<SimplePerson> treeSet = new TreeSet<SimplePerson>();
        treeSet.add(new SimplePerson("Test"));
        treeSet.add(new SimplePerson("Test"));

        System.out.println("TreeSet size = " + treeSet.size()); // Size = 1               
    }

    private LawOfBig3Example()
    {
    }
}