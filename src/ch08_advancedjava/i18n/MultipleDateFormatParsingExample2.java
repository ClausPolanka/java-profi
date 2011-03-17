package ch08_advancedjava.i18n;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Beispielklasse zur Demonstration des Parsings von Datums mit unterschiedlichen SimpleDateFormat-Instanzen 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class MultipleDateFormatParsingExample2
{
    private static final DateFormat df1       = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
    private static final DateFormat df2       = new SimpleDateFormat("dd.MM.yy HH:mm");
    private static final DateFormat df3       = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static final DateFormat df4       = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private static final String     SILVESTER = "31.12.2009";

    public static void main(final String[] args)
    {
        final DateFormat[] supportedFormats = new DateFormat[] { df1, df2, df3, df4 };

        try
        {
            final Date date = DateParseUtils.parseDate(SILVESTER, supportedFormats);
            System.out.println("Parsed '" + SILVESTER + "' into date " + date);
        }
        catch (final ParseException ex)
        {
            System.out.println(DateParseUtils.buildErrorMessage(SILVESTER, supportedFormats));
        }
    }

    private MultipleDateFormatParsingExample2()
    {
    }
}