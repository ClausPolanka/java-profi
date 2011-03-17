package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration regulärer Ausdrücke
 * <br>
 * Definition von Wertebereichen mithilfe von Spezialzeichen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RegExExampleRanges2
{
    public static void main(final String[] args)
    {
        final String input = "#27# Wert27 ##228## Wert228 #3# Wert3";
        final String delimiter2 = "(#+\\d+#+)";

        final String[] tokens2 = input.split(delimiter2);
        StringUtils.printTokens(tokens2);
    }

    private RegExExampleRanges2()
    {
    }
}
