package ch08_advancedjava.i18n.basics;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Beispielklasse zur Demonstration des Parsings von Zahlen mit unterschiedlichen NumberFormat-Instanzen 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class NumberFormatParseExample
{
    public static void main(String[] args) throws ParseException
    {
        final NumberFormat DE_FORMAT = NumberFormat.getInstance(Locale.GERMANY);
        final NumberFormat FR_FORMAT = NumberFormat.getInstance(Locale.FRANCE);
        final NumberFormat US_FORMAT = NumberFormat.getInstance(Locale.US);

        final String[] values = new String[] { "123,456,789", "123.456.789" };
        for (final String number : values)
        {
            System.out.println("Value " + number);
            System.out.println("NumberInstance DE  " + DE_FORMAT.parse(number));
            System.out.println("NumberInstance FR  " + FR_FORMAT.parse(number));
            System.out.println("NumberInstance US  " + US_FORMAT.parse(number));
        }
    }

    private NumberFormatParseExample()
    {
    }
}
