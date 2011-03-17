package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration der Unveränderlichkeit von String-Objekten
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
        test.concat(" ist ein schöner Tag!");

        System.out.println(test);
    }

    private StringImmutabiltyExample()
    {
    }
}