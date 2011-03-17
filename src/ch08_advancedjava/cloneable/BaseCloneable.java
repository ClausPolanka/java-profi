package ch08_advancedjava.cloneable;

/**
 * Beispielklasse zur Demonstration des Klonens
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class BaseCloneable implements Cloneable
{
    private final int  id;    
    /* private */ long value;   // Zum späteren Zugriff im Package 

    public BaseCloneable(final int id, final long value)
    {
        this.id = id;
        this.value = value;
    }

    // Üœberschreiben zur Erweiterung der Sichtbarkeit 
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        // Aufruf des Standardautomatismus, für primitive Attribute ausreichend 
        return super.clone();
    }    
    // ...
    @Override
    public String toString()
    {
        return "BaseCloneable [id=" + id + ", value=" + value + "]";
    }
}