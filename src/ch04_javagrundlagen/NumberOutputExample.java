package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration von Besonderheiten bei der Ausgabe von Zahlen
 * mit Integer.toString()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class NumberOutputExample
{
    public static void main(final String[] args)
    {
        // Zur Basis 10 - Dezimaldarstellung 
        System.out.println("15=" + Integer.toString(15));

        // Zur Basis 2 - Binärdarstellung 
        System.out.println("15=" + Integer.toString(15, 2)); // 15=1111  

        // Zur Basis 4 
        System.out.println("15=" + Integer.toString(15, 4)); // 15=33 

        // Zur Basis 8 - Oktaldarstellung     
        System.out.println("15=" + Integer.toString(15, 8)); // 15=17 

        // Zur Basis 16 - Hexadezimaldarstellung     
        System.out.println("15=" + Integer.toString(15, 16)); // 15=f 
    }

    private NumberOutputExample()
    {
    }
}
