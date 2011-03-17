package ch04_javagrundlagen;

import java.util.Arrays;
import java.util.Date;

/**
 * Beispielprogramm zur Demonstration von Ausgaben mit toString()
 * <br>
 * Verbesserungen der Stringausgabe für Arrays
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class ArraysToStringExample
{
    public static void main(String[] args)
    {
        final Person tom = new Person("Tom", new Date(), "Hamburg");
        final Person jerry = new Person("Jerry", new Date(), "Kiel");
        final Object[] persons = new Object[] { tom, jerry };

        System.out.println("Object[]: " + Arrays.asList(persons)); // JDK 1.4
        System.out.println("Object[]: " + Arrays.toString(persons)); // JDK 5.0
    }

    private ArraysToStringExample()
    {
    }

    private static class Person
    {
        private final String name;
        private final Date   birthday;
        private final String city;

        public Person(final String name, final Date birthday, final String city)
        {
            if (name == null || birthday == null || city == null)
                throw new IllegalArgumentException("parameters 'name', 'birthday' and 'city' must not be null!");

            this.name = name;
            this.birthday = birthday;
            this.city = city;
        }

        public final String getName()
        {
            return name;
        }

        public final Date getBirthday()
        {
            return birthday;
        }

        public final String getCity()
        {
            return city;
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + ": " + "Name='" + getName() + "' " + "City='" + getCity() + "' "
                   + "Birthday='" + getBirthday() + "'";
        }
    }
}