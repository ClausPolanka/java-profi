package ch05_collections;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration der Methoden nCopies() und frequency()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AlgorithmsExample
{
    private static final Person MALE     = new Person("Male", "Bremen", 42);
    private static final Person FEMALE   = new Person("Female", "New York", 43);
    private static final Person MISTER_X = new Person("Mister X", "Sydney", 44);

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
    
    public static void main(String[] args)
    {
        final List<Person> persons = initPersonList();

        final int maleCount = Collections.frequency(persons, MALE);
        System.out.println("Male Persons: " + maleCount);
        System.out.println("All Persons: " + persons);
    }
    
    private AlgorithmsExample()
    {        
    }
}