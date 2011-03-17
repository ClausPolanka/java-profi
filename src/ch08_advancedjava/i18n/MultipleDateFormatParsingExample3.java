package ch08_advancedjava.i18n;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Beispielklasse zur Demonstration des Parsings von Datums mit unterschiedlichen SimpleDateFormat-Instanzen 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class MultipleDateFormatParsingExample3
{
    private static final String DATE_FORMAT_PROPERTY_FILE = "config/resources/DateFormat.properties";

    public static void main(final String[] args)
    {
        try
        {
            final DateFormat[] supportedFormats = DateParseUtils.readDateFormatsFromFile(DATE_FORMAT_PROPERTY_FILE);
            final String silvester = "31.12.2009 18:00";
            try
            {
                final Date date = DateParseUtils.parseDate(silvester, supportedFormats);
                System.out.println("Parsed '" + silvester + "' into Date " + date);
            }
            catch (final ParseException ex)
            {
                System.out.println(DateParseUtils.buildErrorMessage(silvester, supportedFormats));
            }
        }
        catch (final IOException ex)
        {
            System.out.println("No DateFormat-File: " + DATE_FORMAT_PROPERTY_FILE);
        }
    }

    private MultipleDateFormatParsingExample3()
    {
    }
}