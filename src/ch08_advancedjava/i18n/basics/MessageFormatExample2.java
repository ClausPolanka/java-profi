package ch08_advancedjava.i18n.basics;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Beispielklasse zur Demonstration der Formatierung von Texten mit der Klasse MessageFormat
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class MessageFormatExample2
{
    public static void main(String[] args) throws ParseException
    {
        final Object[] arguments = { "MessageFormat", 2, 3.1415, new Date() };
        final String textTemplate = "Beispiel für die Klasse {0}. " + "\nWährung: {1, number, currency} "
                                    + "\nZahl: {2, number} " + "\nDatum und Uhrzeit: {3, date} um {3, time}";

        final String formattedText = MessageFormat.format(textTemplate, arguments);

        System.out.println("Eingabe: " + textTemplate);
        System.out.println();
        System.out.println("Formatiert: " + formattedText);
    }

    private MessageFormatExample2()
    {
    }
}
