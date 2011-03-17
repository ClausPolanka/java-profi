package ch08_advancedjava.i18n.basics;

import static java.text.DateFormat.FULL;
import static java.text.DateFormat.LONG;
import static java.text.DateFormat.MEDIUM;
import static java.text.DateFormat.SHORT;
import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getDateTimeInstance;
import static java.text.DateFormat.getInstance;
import static java.text.DateFormat.getTimeInstance;

import java.util.Date;

/**
 * Beispielklasse zur Demonstration der Formatierung von Datumswerten mit unterschiedlichen Typen von
 * DateFormat-Instanzen 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DateFormatExample
{
    public static void main(String[] args)
    {
        // Aktuelles Datum erzeugen 
        final Date now = new Date();

        // Ausgabe mit toString()
        System.out.println("Date.toString()       " + now.toString());

        // Ausgabe mit DateFormat 
        System.out.println("\nDateFormat");
        System.out.println("getInstance()         " + getInstance().format(now));
        System.out.println("getTimeInstance()     " + getTimeInstance().format(now));
        System.out.println("getDateInstance()     " + getDateInstance().format(now));
        System.out.println("getDateTimeInstance() " + getDateTimeInstance().format(now));

        // Definition aller Varianten (int-Werte auf der Klasse DateFormat)  
        final int[] styles = { SHORT, MEDIUM, LONG, FULL };
        // Achtung: Index gem‰ﬂ Definition der DateFormat-Konstanten  
        // Daher ist die Reihenfolge hier anders als in styles  
        final String[] styleNames = { "FULL  ", "LONG  ", "MEDIUM", "SHORT " };

        // Zeige alle Varianten von DateFormat.getTimeInstance() 
        System.out.println("\nDateFormat.getTimeInstance()");
        for (final int currentStyle : styles)
        {
            System.out.println(styleNames[currentStyle] + "\t\t\t" + getTimeInstance(currentStyle).format(now));
        }

        // Zeige alle Varianten von DateFormat.getDateInstance()  
        System.out.println("\ngetDateInstance()");
        for (final int currentStyle : styles)
        {
            System.out.println(styleNames[currentStyle] + "\t\t\t" + getDateInstance(currentStyle).format(now));
        }

        // Zeige alle Varianten von DateFormat.getDateTimeInstance()  
        System.out.println("\nDateFormat.getDateTimeInstance()");
        for (final int currentDateStyle : styles)
        {
            for (final int currentTimeStyle : styles)
            {
                System.out.println(styleNames[currentDateStyle] + " / " + styleNames[currentTimeStyle] + "\t\t"
                                   + getDateTimeInstance(currentDateStyle, currentTimeStyle).format(now));
            }
        }
    }

    private DateFormatExample()
    {
    }
}