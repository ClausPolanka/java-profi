package ch16_optimierungen;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration des Einflusses von Auto-Boxing auf die Performance
 * <br>
 * Dazu werden jeweils 1.000.000 Objekte der Klassen Person bzw. PersonOptimzedEquals erzeugt und
 * in einer Liste gespeichert. Ein weiteres zu suchendes Objekt wird am Ende der Liste eingefügt.
 * Zum Ermitteln der Laufzeit erfolgt eine Suche in der Liste. Diese wird 1 Mal, 10 Mal und
 * 1.000 Mal durchgeführt, um aussagekräftige Werte zu erhalten. 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class OptimizedEqualsExample
{   
    public static void main(String[] args)
    {
        final long[] MAX_COUNTS = new long[] { 1, 10, 1000 };
        for (final long count : MAX_COUNTS)
        {
            System.out.println("Searching in 1.000.000 elements " + NumberFormat.getNumberInstance().format(count) + " times");
            measureEqualsDuration(count);
            measureOptimizedEqualsDuration(count);
        }
    }

    private static void measureEqualsDuration(final long count)
    {
        final Person dummy = new Person("Test", Gender.MALE, 4711);
        final Person wanted = new Person("Wanted", Gender.FEMALE, 2711);

        // Liste mit Dummy-Personen erzeugen, Kopie in ArrayList, um gewünschtes Element hinzuzufügen
        final List<Person> dummies = Collections.nCopies(1000000, dummy);
        final List<Person> persons = new ArrayList<Person>(dummies);
        persons.add(wanted);
        
        PerformanceUtils.startMeasure("Equals - With Auto-Boxing");
   
        final int foundAt = searchForWanted(count, wanted, persons);
        
        PerformanceUtils.stopMeasure("Equals - With Auto-Boxing");
        PerformanceUtils.printTimingResult("Equals - With Auto-Boxing");
        
        // Vermeide Weg-Optimierung der obigen Zuweisung
        System.out.println("foundAt " + foundAt);
    }
    
    private static void measureOptimizedEqualsDuration(final long count)
    {
        final PersonWithOptimizedEquals dummy = new PersonWithOptimizedEquals("Test", Gender.MALE, 4711);
        final PersonWithOptimizedEquals wanted = new PersonWithOptimizedEquals("Wanted", Gender.FEMALE, 2711);

        // Liste mit Dummy-Personen erzeugen, Kopie in ArrayList, um gewünschtes Element hinzuzufügen
        final List<PersonWithOptimizedEquals> dummies = Collections.nCopies(1000000, dummy);
        final List<PersonWithOptimizedEquals> persons = new ArrayList<PersonWithOptimizedEquals>(dummies);
        persons.add(wanted);
        
        PerformanceUtils.startMeasure("Equals - No Auto-Boxing");
   
        final int foundAt = searchForWanted(count, wanted, persons);
        
        PerformanceUtils.stopMeasure("Equals - No Auto-Boxing");
        PerformanceUtils.printTimingResult("Equals - No Auto-Boxing");
        
        // Vermeide Weg-Optimierung der obigen Zuweisung
        System.out.println("foundAt " + foundAt);
    }
    
    /**
     * Suche das übergebene Objekt wanted in der Liste, führe die Suche count Mal durch 
     */
    private static int searchForWanted(final long count, final Object wanted, final List<?> persons)
    {
        int foundAt = -1;
        for (long i = 0; i < count; i++)
        {
            foundAt = -1;
            foundAt = persons.indexOf(wanted);
        }
        return foundAt;
    }
    
    private OptimizedEqualsExample()
    {        
    }
}
