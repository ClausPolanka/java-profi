package ch05_collections.filtering;

/**
 * Beispielklasse zur Demonstration einzelner Filterkriterien
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SimplePerson
{
    /* private */ String name;    // Zugriff für Filter im Package erlauben 
    /* private */ int age;        // Zugriff für Filter im Package erlauben 
    
    public SimplePerson(final String name, final int age)
    {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString()
    {
        return "SimplePerson [age=" + age + ", name=" + name + "]";
    }
}