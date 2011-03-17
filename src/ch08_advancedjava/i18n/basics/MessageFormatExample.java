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
public final class MessageFormatExample
{
    public static void main(final String[] args) throws ParseException
    {
        final String textTemplate = "Am {1} ist {0}. {2} Sekt bitte!";
        final MessageFormat mf = new MessageFormat(textTemplate);

        // Date(int year, int month, int date, int hrs, int min)  
        // ACHTUNG: auf das Jahr wird der Wert 1900 addiert => 109 == 2009  
        final Object[] arguments = { "Karneval", new Date(109, 10, 11, 11, 11), 3 };
        final String formattedText = mf.format(arguments);

        System.out.println("Eingabe: " + textTemplate);
        System.out.println("Formatiert: " + formattedText);
    }

    private MessageFormatExample()
    {
    }
}
