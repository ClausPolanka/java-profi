package ch04_javagrundlagen;

import java.util.StringTokenizer;

/**
 * Beispielprogramm für Asseretions 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AssertExample
{
    public static void main(String[] args)
    {
        // ACHTUNG: fehlendes Token Minor-Version  
        final String versions = "12. ";
        final StringTokenizer tokenizer = new StringTokenizer(versions, ".");

        final int tokenCount = tokenizer.countTokens();
        if (tokenCount > 1)
        {
            final String majorVersion = tokenizer.nextToken().trim();
            final String minorVersion = tokenizer.nextToken().trim();

            // Sicherstellen, dass Tokens einen Wert enthalten  
            assert majorVersion.length() > 0;
            assert minorVersion.length() > 0;

            System.out.println("Major: '" + majorVersion + "'");
            System.out.println("Minor: '" + minorVersion + "'");
            System.out.println("#Tokens: '" + tokenCount + "'");
        }
    }

    private AssertExample()
    {
    }
}
