package ch04_javagrundlagen;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Beispielprogramm für Berechnungen mithilfe der Calendar-Klasse
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CalendarCalculationExample
{
    public static void main(String[] args)
    {
        // Schaltjahr 2000 => Berechnung bei Verschiebung um 1 Monat wichtig 
        final Calendar now = new GregorianCalendar(2000, Calendar.FEBRUARY, 28);

        // Einen Tag in die Vergangenheit 
        final Calendar oneDayAgo = (Calendar) now.clone();
        oneDayAgo.add(Calendar.DAY_OF_YEAR, -1);

        // Eine Woche in die Zukunft 
        final Calendar oneWeekFromNow = (Calendar) now.clone();
        oneWeekFromNow.add(Calendar.WEEK_OF_YEAR, +1);

        // Ausgabe als Date-Objekt 
        System.out.println("oneDayAgo = " + oneDayAgo.getTime());
        System.out.println("now = " + now.getTime());
        System.out.println("oneWeekFromNow = " + oneWeekFromNow.getTime());

        // Schaltjahr 2000 => oneDayAgo < now < oneWeekFromNow 
        System.out.println(oneDayAgo.before(now));
        System.out.println(now.before(oneWeekFromNow));
    }

    private CalendarCalculationExample()
    {
    }
}
