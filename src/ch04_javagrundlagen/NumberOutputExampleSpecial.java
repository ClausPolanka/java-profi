package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration von Besonderheiten bei der Ausgabe von Zahlen
 * mit Integer.toXXXString()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class NumberOutputExampleSpecial
{
    public static void main(final String[] args)
    {
        // Convenience 
        System.out.println("15=" + Integer.toBinaryString(15)); // 15=1111   
        System.out.println("15=" + Integer.toOctalString(15)); // 15=17 
        System.out.println("15=" + Integer.toHexString(15)); // 15=f 
        System.out.println("255=" + Integer.toHexString(255)); // 255=ff    
        System.out.println("-1=" + Integer.toHexString(-1)); // -1=ffffffff  
    }

    private NumberOutputExampleSpecial()
    {
    }

}
