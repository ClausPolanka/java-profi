package ch03_oodesign.generics;

import java.util.Date;

/**
 * Hilfsklasse zur Demonstration von verschiedenen Aspekten bei Generics
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Person
{
    private String name;
    private Date   birthday;
    private String city;

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

    public void setName(String name)
    {
        this.name = name;
    }
    
    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public final String getCity()
    {
        return city;
    }
    
    public void setCity(String city)
    {
        this.city = city;
    }


    public boolean equals(Object other)
    {
        if (other == null) // null safe
            return false;

        if (this == other) // reflexive
            return true;

        // compare only objects of same type
        if (this.getClass().equals(other.getClass()))
            return false;

        final Person otherPerson = (Person) other;
        return equalsImpl(otherPerson);
    }

    private boolean equalsImpl(final Person otherPerson)
    {
        return this.getName().equals(otherPerson.getName()) && this.getBirthday().equals(otherPerson.getBirthday())
               && this.getCity().equals(otherPerson.getCity());
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
        buf.append("Birthday='");
        buf.append(getBirthday());
        buf.append("'");

        return buf.toString();        
    }
}
