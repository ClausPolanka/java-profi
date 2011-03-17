package ch05_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration der Nutzung des Interfaces Comparable
 * zur Realisierung von equals()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonUsingComparableForEquals implements Comparable<PersonUsingComparableForEquals>
{
    private final String name;
    private final String city;
    private final int    age;

    public PersonUsingComparableForEquals(final String name, final String city, final int age)
    {
        if (name == null)
            throw new IllegalArgumentException("Passed parameter name must not be null!");
        if (city == null)
            throw new IllegalArgumentException("Passed parameter city must not be null!");

        this.name = name;
        this.city = city;
        this.age = age;
    }

    public final String getName()
    {
        return name;
    }

    public final String getCity()
    {
        return city;
    }

    public final int getAge()
    {
        return age;
    }

    public String toString()
    {
        final StringBuffer buf = new StringBuffer();

        buf.append("Person: ");

        buf.append("Name='");
        buf.append(getName());
        buf.append("' ");
        buf.append("City='");
        buf.append(getCity());
        buf.append("' ");
        buf.append("Age='");
        buf.append(getAge());
        buf.append("'");

        return buf.toString();
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        final PersonUsingComparableForEquals other = (PersonUsingComparableForEquals) obj;
        return compareTo(other) == 0;       // Vergleich mit compateTo() 
    }

    @Override
    public int compareTo(final PersonUsingComparableForEquals otherPerson)
    {
        int ret = getName().compareTo(otherPerson.getName());

        if (ret == 0)
        {
            ret = getCity().compareTo(otherPerson.getCity());
        }

        if (ret == 0)
        {
            if (getAge() < otherPerson.getAge())
            {
                ret = -1;
            }
            if (getAge() > otherPerson.getAge())
            {
                ret = 1;
            }
        }

        return ret;
    }

    public static void main(String[] args)
    {
        final List<PersonUsingComparableForEquals> customers = new ArrayList<PersonUsingComparableForEquals>();

        customers.add(new PersonUsingComparableForEquals("Müller", "Bremen", 27));
        customers.add(new PersonUsingComparableForEquals("Müller", "Kiel", 37));
        customers.add(new PersonUsingComparableForEquals("Müller", "Bremen", 47));
        customers.add(new PersonUsingComparableForEquals("Meyer", "Oldenburg", 22));

        System.out.println(customers);
        Collections.sort(customers);
        System.out.println(customers);
    }
}
