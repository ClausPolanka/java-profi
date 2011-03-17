package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration der format()-Methode von String-Objekten
 * <br>
 * Abwandlung mit Varargs
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringFormatVarArgsExample
{
    public static void main(final String[] args)
    {
        final String str = String.format("Hi %s. Es ist %d Uhr.", "Mike", 12);
        System.out.println(str); // Hi Mike. Es ist 12 Uhr.   

        System.out.printf("Hi %s. Es ist %d Uhr.", "Mike", 12);
    }
    
    private StringFormatVarArgsExample()
    {
    }
}