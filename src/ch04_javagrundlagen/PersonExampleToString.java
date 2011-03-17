package ch04_javagrundlagen;

import java.util.Date;

/**
 * Beispielprogramm zur Demonstration von Ausgaben mit toString()
 * <br>
 * Verbesserungen der Stringausgabe für Person-Objekte
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonExampleToString
{
    public static void main(String[] args)
    {
        final Person tom = new Person("Tom", new Date(), "Hamburg");
        System.out.println(tom);
        System.out.println(tom.toStringWithStringBuilder());
    }
    
    private PersonExampleToString()
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

        public String toStringWithStringBuilder()
        {
            final StringBuilder sb = new StringBuilder();

            sb.append(getClass().getSimpleName()).append(": ");
            sb.append("Name='").append(getName()).append("' ");
            sb.append("City='").append(getCity()).append("' ");
            sb.append("Birthday='").append(getBirthday()).append("' ");

            return sb.toString();
        }
    }
}