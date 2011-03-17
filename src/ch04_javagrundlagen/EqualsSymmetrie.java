package ch04_javagrundlagen;

/**
 * Beispiel zur Demonstration der Symmetrie bei equals()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class EqualsSymmetrie
{
    private static class Base
    {
    }

    private static class Sub extends Base
    {
    }

    public static void main(String[] args)
    {
        final Base x = new Base();
        final Sub y = new Sub();

        // instanceof prüfen
        System.out.println("y is-a Base: " + (y instanceof Base)); // true 
        System.out.println("x is-a Sub: " + (x instanceof Sub)); // false 

        // getClass() prï¿½fen, Ergebnis: false
        System.out.println("getClass(): " + (x.getClass() == y.getClass()));
    }

    private EqualsSymmetrie()
    {
    }
}
