package ch04_javagrundlagen;

import java.util.Date;

/**
 * Beispielprogramm für Berechnungen mithilfe der Date-Klasse
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DateCalculationExample
{
    public static void main(String[] args)
    {
        final long ONE_SEC_MSEC = 1000;
        final long ONE_MIN_MSEC = 60 * ONE_SEC_MSEC;
        final long ONE_HOUR_MSEC = 60 * ONE_MIN_MSEC;
        final long ONE_DAY_MSEC = 24 * ONE_HOUR_MSEC;
        final long ONE_WEEK_MSEC = 7 * ONE_DAY_MSEC;

        // Schaltjahr 2000 => Offset Jahr: 1900 
        final Date feb2000 = new Date(100, 1, 28);
        final Date oneDayAgo = new Date(feb2000.getTime() - ONE_DAY_MSEC);
        final Date oneWeekFromNow = new Date(feb2000.getTime() + ONE_WEEK_MSEC);

        System.out.println("oneDayAgo = " + oneDayAgo);
        System.out.println("feb2000 = " + feb2000);
        System.out.println("oneWeekFromNow = " + oneWeekFromNow);

        // oneDayAgo < feb2000 < oneWeekFromNow
        System.out.println(oneDayAgo.before(feb2000));
        System.out.println(feb2000.before(oneWeekFromNow));
    }

    private DateCalculationExample()
    {
    }
}
