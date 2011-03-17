package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration regulärer Ausdrücke
 * <br>
 * Verbesserte Ausgabe
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RegExExampleImprovedPrint
{
    public static void main(final String[] args)
    {
        final String input = "#-# Wert1 #-# Wert2 #-# Wert3";
        final String delimiter = "#-#";

        final String[] tokens = input.split(delimiter);

        printTokens(tokens); // Tokens = '['', ' Wert1 ', ' Wert2 ', ' Wert3']'
    }

    private static void printTokens(final String[] tokens)
    {
        System.out.print("Tokens = '[");
        for (int i = 0; i < tokens.length; i++)
        {
            System.out.print("'" + tokens[i] + "'");

            // Komma anfügen, wenn noch nicht letztes Element 
            if (i != tokens.length - 1)
                System.out.print(", ");
        }
        System.out.println("]'");
    }

    private RegExExampleImprovedPrint()
    {
    }
}
