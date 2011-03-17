package ch04_javagrundlagen;

import java.util.Date;

/**
 * Beispielklasse zur Demonstration der Implementierung von equals()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Person
{
    private final String name;
    private final Date birthday;
    private final String city;

    public Person(final String name, final Date birthday, final String city)
    {
        if ( name == null || birthday == null || city == null)
            throw new IllegalArgumentException("parameters 'name', 'birthday' and 'city' must not be null!");
            
        this.name = name;
        this.birthday = birthday;
        this.city = city;
    }

    public final String getName()       { return name; }
    public final Date   getBirthday()   { return birthday; }
    public final String getCity()       { return city; }
   
       
    public boolean equals(Object other)
    {
        if (other == null)          // null safe
            return false;
            
        if (this == other)          // reflexive
            return true;
            
        if (this.getClass() != other.getClass())   // same type ?
            return false;      
        
        final Person otherPerson = (Person)other;
        return equalsImpl(otherPerson);
    }
    
    private boolean equalsImpl(final Person otherPerson)
    {
        return this.getName().equals(otherPerson.getName()) &&                
               this.getBirthday().equals(otherPerson.getBirthday()) &&
               this.getCity().equals(otherPerson.getCity());           
    }      
}