package ch16_optimierungen;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import ch05_collections.LruLinkedHashMap;

/**
 * Beispielprogramm zur Demonstration des Einflusses von Caching mit
 * unterschiedlichen Cache-Größen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LruListCacheExample
{
    private final LruLinkedHashMap<String, Person> cacheMap;
    private final List<Person>                     persons;

    public LruListCacheExample(final List<Person> persons, final int cacheSize)
    {
        this.cacheMap = new LruLinkedHashMap<String, Person>(cacheSize);
        this.persons = persons;
    }

    public static void main(String[] args)
    {
        //PropertyConfigurator.configureAndWatch("config/log4j.properties", 5000);
        BasicConfigurator.configure();

        final int PERSON_COUNT = 10000;
        final List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < PERSON_COUNT; i++)
        {
            final Person newPerson = new Person("Person " + i, Gender.MALE, i);
            persons.add(newPerson);
        }

        final int[] cacheSizes = { 10, 20, 7 };
        for (int cacheSize : cacheSizes)
        {
            System.out.println("CacheSize = " + cacheSize);
            final LruListCacheExample cache = new LruListCacheExample(persons, cacheSize);

            performTests(cache, "findPersonByName", false);
            performTests(cache, "getPerson", true);
        }
    }

    public static void performTests(final LruListCacheExample cache, final String info, final boolean useCache)
    {
        final long[] maxIterationCount = { 1000, 10000, 100000 };

        for (long max : maxIterationCount)
        {
            System.out.println("Element count " + max);

            PerformanceUtils.startMeasure(info);
            for (long i = 0; i < max; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    final String name = "Person " + (j * 1000);
                    if (useCache)
                        cache.getPerson(name);
                    else
                        cache.findPersonByName(name);
                }
            }
            PerformanceUtils.stopMeasure(info);
            PerformanceUtils.printTimingResult(info);
        }
    }

    // ...

    public Person getPerson(final String name)
    {
        Person cachedPerson = cacheMap.get(name);

        if (cachedPerson == null)
        {
            final Person person = findPersonByName(name);
            if (person != null)
            {
                cacheMap.put(name, person);
                return person;
            }
        }

        return cachedPerson;
    }

    private Person findPersonByName(final String name)
    {
        for (final Person current : persons)
        {
            if (current.getName().equals(name))
            {
                return current;
            }
        }
        return null;
    }
}
