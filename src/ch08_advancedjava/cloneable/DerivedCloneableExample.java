package ch08_advancedjava.cloneable;

/**
 * Beispielklasse zur Demonstration des Klonens und Vererbung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DerivedCloneableExample
{
    public static void main(String[] args)
    {
        final DerivedCloneable original = new DerivedCloneable(8, 15, "Test");
        // Cast wieder notwendig  
        final DerivedCloneable clone = (DerivedCloneable) original.clone();
        System.out.println("Original: " + original);
        System.out.println("Kopie: " + clone);
    }

    private DerivedCloneableExample()
    {
    }
}
