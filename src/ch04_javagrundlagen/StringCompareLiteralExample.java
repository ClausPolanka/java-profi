package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration von String-Literalen und String-Objekten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringCompareLiteralExample
{
    public static void main(final String[] args)
    {
        final String LITERAL = "TEST";
        final String STRINGOBJECT = new String("TEST");

        if (LITERAL == "TEST")
        {
            System.out.println("LITERAL == TEST");
        }
        if (STRINGOBJECT == "TEST")
        {
            System.out.println("STRINGOBJECT == TEST");
        }
        if (STRINGOBJECT == LITERAL)
        {
            System.out.println("STRINGOBJECT == LITERAL");
        }
        if (STRINGOBJECT.equals(LITERAL))
        {
            System.out.println("STRINGOBJECT equals LITERAL");
        }
    }

    private StringCompareLiteralExample()
    {
    }
}