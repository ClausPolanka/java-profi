package ch03_oodesign.varianz;

import java.util.Date;

import ch03_oodesign.generics.Person;

/**
 * Beispielprogramm zur Demonstration von Problemen der Kovarianz von Arrays
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ArrayStoreExceptionExample
{
    public static void main(String[] args)
    {
        final Object[] texts = new String[] { "Arrays", "sind", "kovariant" };
     
        texts[0] = "Achtung!";
        // Provoziert eine java.lang.ArrayStoreException
        texts[1] = new Person("Max", new Date(), "Musterstadt");
    }
    
    private ArrayStoreExceptionExample()
    {        
    }
}
