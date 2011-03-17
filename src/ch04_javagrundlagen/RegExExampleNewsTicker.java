package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration regulärer Ausdrücke
 * <br>
 * Verarbeitung von Spezialzeichen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RegExExampleNewsTicker
{
    public static void main(final String[] args)
    {
        final String input = "News: First News +++ Second News +++ End";
        final String delimiter = "\\+\\+\\+";

        final String[] tokens = input.split(delimiter);
        StringUtils.printTokens(tokens);
    }

    private RegExExampleNewsTicker()
    {
    }
}
