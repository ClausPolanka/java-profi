package ch04_javagrundlagen;

import java.util.Date;

/**
 * Beispielprogramm zur Demonstration von Merkwürdigkeiten ((Offset 1900) der Date-Klasse
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DateAPIProblems1
{
    public static void main(final String[] args)
    {
        System.out.println(new Date(1971, 7, 2));
    }

    private DateAPIProblems1()
    {
    }
}