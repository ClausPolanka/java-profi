package ch04_javagrundlagen;

import java.util.Arrays;

/**
 * Beispielprogramm zur Demonstration regulärer Ausdrücke
 * <br>
 * Version 1
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RegExExample
{
    public static void main(final String[] args)
    {
        final String input = "#-# Wert1 #-# Wert2 #-# Wert3";
        final String delimiter = "#-#";

        final String[] tokens = input.split(delimiter);
        printTokens(tokens); // Tokens = '[,  Wert1 ,  Wert2 ,  Wert3]' 
    }

    private static void printTokens(final String[] tokens)
    {
        System.out.print("Tokens = '" + Arrays.asList(tokens) + "'");
    }

    private RegExExample()
    {
    }
}
