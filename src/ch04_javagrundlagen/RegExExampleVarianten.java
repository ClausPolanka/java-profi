package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration regulärer Ausdrücke
 * <br>
 * Auswertung von Varianten/ Alternativen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RegExExampleVarianten
{
    public static void main(final String[] args)
    {
        final String input = "#1# Wert1 #5# Wert5 #7# Wert7";
        final String delimiter = "#(1|2|3|4|5|6|7|8|9)#";

        final String[] tokens = input.split(delimiter);
        StringUtils.printTokens(tokens);
    }

    private RegExExampleVarianten()
    {
    }
}
