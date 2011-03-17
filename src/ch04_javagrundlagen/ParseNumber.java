package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration der Parsingfunktionalität der Wrapper-Klassen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ParseNumber
{
    private static boolean isFloatingNumber(final String numberAsText)
    {
        try
        {
            Double.parseDouble(numberAsText);
            return true;
        }
        catch (final NumberFormatException ex)
        {
            return false;
        }
    }

    public static void main(final String[] args)
    {
        System.out.println("isNumber(47) " + isFloatingNumber("47")); // true
        System.out.println("isNumber(47a) " + isFloatingNumber("47a")); // false

        System.out.println("isNumber(47.11) " + isFloatingNumber("47.11")); // true
        System.out.println("isNumber(47,11) " + isFloatingNumber("47,11")); // false
    }

    private ParseNumber()
    {
    }
}