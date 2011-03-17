package ch04_javagrundlagen;

import java.util.Date;

/**
 * Beispielprogramm zur Demonstration von Besonderheiten (Offset 1970) der Date-Klasse
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DateAPIProblems2
{
    public static void main(final String[] args)
    {
        System.out.println(new Date(-10000000)); // Wed Dec 31 22:13:20 CET 1969
    }

    private DateAPIProblems2()
    {
    }
}