package ch04_javagrundlagen;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Beispielprogramm zur Demonstration von Besonderheiten (getTime(()) mit der Calendar-Klasse
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DateAPIProblems3
{
    public static void main(final String[] args)
    {
        final Calendar calendar = new GregorianCalendar(1971, 1, 7);
        System.out.println(calendar.getTime()); // Sun Feb 07 00:00:00 CET 1971

        final Calendar calendar2 = new GregorianCalendar(1971, 1, 7, 21, 22);
        System.out.println(calendar2.getTime()); //Sun Feb 07 21:22:00 CET 1971 
    }

    private DateAPIProblems3()
    {
    }
}