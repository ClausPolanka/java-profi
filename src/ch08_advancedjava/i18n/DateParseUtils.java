package ch08_advancedjava.i18n;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import ch06_applikationsbausteine.RangeCheckUtils;
import ch06_applikationsbausteine.StreamUtils;

/**
 * Utility-Klasse zur Vereinfachung des Parsings von Datumswerten 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */

public final class DateParseUtils
{
    /**
    * tries to parse a given date input string with the passed date formats

    * @param dateAndTime  the string containing the date and time information, e.g. "7.2.71 15:21"
    * @param supportedDateFormats  an array of supported date formats 
    * 
    * @return   the parsed date
    * @throws   ParseException if the given string could not be parsed 
    * @throws   IllegalArgumentException if the given string or date format array is null or empty
    */
    public static Date parseDate(final String dateAndTime, final DateFormat... supportedDateFormats)
                    throws ParseException
    {
        RangeCheckUtils.assertStringParamNotNullOrEmpty("dateAndTime", dateAndTime);
        RangeCheckUtils.assertArrayParamNotNullOrEmpty("supportedDateFormats", supportedDateFormats);

        for (final DateFormat currentFormat : supportedDateFormats)
        {
            try
            {
                // Rückgabe des Formats bei erfolgreichem Parsing  
                return currentFormat.parse(dateAndTime);
            }
            catch (final ParseException e)
            {
                // Ignorieren und mit nächstem Format versuchen  
            }
        }

        // Kein Format erlaubt eine Umwandlung 
        throw new ParseException(dateAndTime, 0);
    }

    public static String buildErrorMessage(final String dateAndTime, final DateFormat... supportedDateFormats)
    {
        // Aufbereiten der Muster 
        final String[] patterns = new String[supportedDateFormats.length];
        for (int i = 0; i < supportedDateFormats.length; i++)
        {
            if (supportedDateFormats[i] instanceof SimpleDateFormat)
            {
                patterns[i] = "'" + ((SimpleDateFormat) supportedDateFormats[i]).toPattern() + "'";
            }
            else
            {
                patterns[i] = "'" + supportedDateFormats[i] + "'";
            }
        }

        return "Parsing error: value='" + dateAndTime + "'\nSupported formats: " + Arrays.toString(patterns);
    }

    public static DateFormat[] readDateFormatsFromFile(final String filename) throws IOException, FileNotFoundException
    {
        InputStream is = null;
        try
        {
            final Properties dateFormatProperties = new Properties();
            is = new FileInputStream(filename);
            dateFormatProperties.load(is);

            // Erzeugen von SimpleDateFormat-Objekten aus den Properties 
            final List<DateFormat> dateFormats = new ArrayList<DateFormat>();
            for (final Object value : dateFormatProperties.values())
            {
                final String formatString = (String) value;
                dateFormats.add(new SimpleDateFormat(formatString));
            }

            return dateFormats.toArray(new DateFormat[dateFormats.size()]);
        }
        finally
        {
            StreamUtils.safeClose(is);
        }
    }

    private DateParseUtils()
    {
    }
}