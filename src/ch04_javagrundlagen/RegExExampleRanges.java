package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration regulärer Ausdrücke
 * <br>
 * Definition von Wertebereichen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RegExExampleRanges
{
    public static void main(final String[] args)
    {
        final String input = "#27# Wert27 ##228## Wert228 #3# Wert3";
        final String delimiter = "(#+[0-9]+#+)";

        final String[] tokens = input.split(delimiter);
        StringUtils.printTokens(tokens);
    }

    private RegExExampleRanges()
    {
    }
}
