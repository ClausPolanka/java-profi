package ch16_optimierungen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import ch04_javagrundlagen.EqualsUtils;

/**
 * Beispielprogramm zur Demonstration des Einflusses von unterschiedlichen
 * Listen-Datenstrukturen und unterschiedlichen Operationen (add, remove, iterate)
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ListPerformanceTest
{
    private static final SimplePerson OBJ_TO_ADD = new SimplePerson("Test", 42);

    public static void main(String[] args)
    {
        final List<SimplePerson> arrayList = new ArrayList<SimplePerson>();
        performListTests("ArrayList", arrayList);

        final List<SimplePerson> linkedList = new LinkedList<SimplePerson>();
        performListTests("LinkedList", linkedList);

        final List<SimplePerson> vector = new Vector<SimplePerson>();
        performListTests("Vector", vector);

        final List<SimplePerson> arrayList100000 = new ArrayList<SimplePerson>(100000);
        performListTests("ArrayList PreSized(100000)", arrayList100000);
    }

    private static void performListTests(final String dsName, final List<SimplePerson> list)
    {
        final long[] maxCount = { 10000, 100000, 1000000 };

        for (final long max : maxCount)
        {
            System.out.println("Element count " + max);

            list.clear();
            PerformanceUtils.startMeasure(dsName + " add front");
            addPersonFront(list, max);
            PerformanceUtils.stopMeasure(dsName + " add front");
            PerformanceUtils.printTimingResult(dsName + " add front");

            PerformanceUtils.startMeasure(dsName + " remove front");
            removePersonFront(list, max);
            PerformanceUtils.stopMeasure(dsName + " remove front");
            PerformanceUtils.printTimingResult(dsName + " remove front");

            list.clear();
            PerformanceUtils.startMeasure(dsName + " add middle");
            addPersonMiddle(list, max);
            PerformanceUtils.stopMeasure(dsName + " add middle");
            PerformanceUtils.printTimingResult(dsName + " add middle");

            PerformanceUtils.startMeasure(dsName + " remove middle");
            removePersonMiddle(list, max);
            PerformanceUtils.stopMeasure(dsName + " remove middle");
            PerformanceUtils.printTimingResult(dsName + " remove middle");

            list.clear();
            PerformanceUtils.startMeasure(dsName + " add last");
            addPersonLast(list, max);
            PerformanceUtils.stopMeasure(dsName + " add last");
            PerformanceUtils.printTimingResult(dsName + " add last");

            PerformanceUtils.startMeasure(dsName + " iterate");
            iteratePersonLast(list);
            PerformanceUtils.stopMeasure(dsName + " iterate");
            PerformanceUtils.printTimingResult(dsName + " iterate");

            PerformanceUtils.startMeasure(dsName + " getAt middle");
            getAt(list, max);
            PerformanceUtils.stopMeasure(dsName + " getAt middle");
            PerformanceUtils.printTimingResult(dsName + " getAt middle");

            PerformanceUtils.startMeasure(dsName + " remove last");
            removePersonLast(list, max);
            PerformanceUtils.stopMeasure(dsName + " remove last");
            PerformanceUtils.printTimingResult(dsName + " remove last");
        }
    }

    private static void addPersonLast(final List<SimplePerson> list, final long count)
    {
        for (int i = 0; i < count; i++)
        {
            list.add(OBJ_TO_ADD);
        }
    }

    private static void addPersonMiddle(final List<SimplePerson> list, final long count)
    {
        for (int i = 0; i < count; i++)
        {
            list.add(list.size() / 2, OBJ_TO_ADD);
        }
    }

    private static void addPersonFront(final List<SimplePerson> list, final long count)
    {
        for (int i = 0; i < count; i++)
        {
            list.add(0, OBJ_TO_ADD);
        }
    }

    private static void removePersonLast(final List<SimplePerson> list, final long count)
    {
        for (int i = 0; i < count; i++)
        {
            list.remove(list.size() - 1);
        }
    }

    private static void removePersonMiddle(final List<SimplePerson> list, final long count)
    {
        for (int i = 0; i < count; i++)
        {
            list.remove(list.size() / 2);
        }
    }

    private static void removePersonFront(final List<SimplePerson> list, final long count)
    {
        for (int i = 0; i < count; i++)
        {
            list.remove(0);
        }
    }

    private static void iteratePersonLast(final List<SimplePerson> list)
    {
        final Iterator<SimplePerson> it = list.iterator();
        while (it.hasNext())
        {
            // access obj to avoid hot-spot-optimization
            final SimplePerson person = it.next();
            final int age = person.age;
        }
    }

    private static void getAt(final List<SimplePerson> list, final long count)
    {
        // access obj to avoid hot-spot-optimization
        final SimplePerson person = list.get((int) count / 2 - 1);
        final int age = person.age;
    }

    private static class SimplePerson
    {
        final String name;

        final int    age;

        public SimplePerson(final String name, final int age)
        {
            this.name = name;
            this.age = age;
        }

        public boolean equals(Object other)
        {
            if (other == null) // null sicher
                return false;

            if (this == other) // reflexiv
                return true;

            if (this.getClass() != other.getClass()) // prüfe auf gleichen Typ
                return false;

            final SimplePerson otherPerson = (SimplePerson) other;
            return equalsImpl(otherPerson);
        }

        private boolean equalsImpl(final SimplePerson otherPerson)
        {
            return this.age == otherPerson.age && EqualsUtils.nullSafeEquals(this.name, otherPerson.name);
        }
    }

    private ListPerformanceTest()
    {
    }
}