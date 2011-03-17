package ch04_javagrundlagen;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Beispielprogramm für eine verbesserte Aufbereitung von Datumswerten bei toString()-Ausgaben
 * für Person-Objekte
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonDateToStringExample
{
    public static void main(String[] args)
    {
        final Person person = new Person("NewBorn", new Date(), "Hamburg");
        System.out.println("Birthday: " + dateAsString(person.getBirthDay()));

        final Person person2 = new Person("Micha", new Date(71, 1, 7), "Bremen");
        System.out.println("Birthday: " + dateAsString(person2.getBirthDay()));
    }
    
    public static class Person
    {
        private final String name;
        private final String city;
        private final Date   birthday;

        public Person(final String name, final Date birthday, final String city)
        {
            if (name == null)
                throw new IllegalArgumentException("Passed parameter name must not be null!");
            if (city == null)
                throw new IllegalArgumentException("Passed parameter city must not be null!");
            if (birthday == null)
                throw new IllegalArgumentException("Passed parameter birthday must not be null!");

            this.name = name;
            this.city = city;
            this.birthday = birthday;
        }

        public final String getName()
        {
            return name;
        }

        public final String getCity()
        {
            return city;
        }

        public final Date getBirthDay()
        {
            return birthday;
        }

        public final int getAge()
        {
            final Calendar calNow = new GregorianCalendar();

            final Calendar calBirthDay = new GregorianCalendar();
            calBirthDay.setTime(birthday);

            // wenn Monat und Tag erreicht, dann keine Korrektur, hier mit DAY_OF_YEAR
            final int dayNow = calNow.get(Calendar.DAY_OF_YEAR);
            final int dayBirthDay = calBirthDay.get(Calendar.DAY_OF_YEAR);
            final int correction = (dayNow >= dayBirthDay) ? 0 : -1;

            final int years = calNow.get(Calendar.YEAR) - calBirthDay.get(Calendar.YEAR);
            return years + correction;
        }
    }

    public static String dateAsString(final Date date)
    {
        final Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);

        final StringBuilder result = new StringBuilder();

        result.append(cal.get(Calendar.DAY_OF_MONTH));
        result.append('-');
        result.append(cal.get(Calendar.MONTH) + 1);
        result.append('-');
        result.append(cal.get(Calendar.YEAR));
        result.append(' ');
        result.append(cal.get(Calendar.HOUR_OF_DAY));
        result.append(':');
        result.append(cal.get(Calendar.MINUTE));

        return result.toString();
    }
}