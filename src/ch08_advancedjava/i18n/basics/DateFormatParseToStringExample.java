package ch08_advancedjava.i18n.basics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Beispielklasse zur Demonstration des Parsing von Datumswerten mit einem SimpleDateFormat 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DateFormatParseToStringExample
{
    public static void main(String[] args) throws ParseException
    {
        // Neues Date-Objekt erzeugen  
        final Date now = new Date();

        // In String wandeln und als Vergleichswert ausgeben 
        final String dateString = now.toString();
        System.out.println("Now: " + dateString);

        // Definition des passenden Datumsformats  
        final SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        // Versuche die Ausgabe von Date.toString() zu parsen  
        final Date parsed = format.parse(dateString);
        System.out.println("Parsed date: " + parsed);

        // Prüfe, ob das Format auch bei der Ausgabe korrekt arbeitet  
        System.out.println("Formatted date: " + format.format(now));
    }

    private DateFormatParseToStringExample()
    {
    }
}