package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration der Unver�nderlichkeit von String-Objekten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringImmutabiltyExample
{
    public static void main(final String[] args)
    {
        String test = "Heute";
        test.concat(" ist ein sch�ner Tag!");

        System.out.println(test);
    }

    private StringImmutabiltyExample()
    {
    }
}