package ch06_applikationsbausteine;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Beispiel für Logging von Exception mit Stacktrace
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ExceptionLoggingImproved
{
    private static final Logger LOGGER = Logger.getLogger(ExceptionLoggingImproved.class);

    public static void main(String[] args)
    {
        PropertyConfigurator.configureAndWatch("config/log4j.properties");

        try
        {
            methodThrowingException();
        }
        catch (final IOException e)
        {
            // String-Info MIT Stacktrace       
            LOGGER.error("An I/O error occurred!", e);
        }
    }

    // just to provide a call stack
    private static void methodThrowingException() throws IOException
    {
        first();
    }

    // just to provide a call stack
    private static void first() throws IOException
    {
        second();
    }

    private static void second() throws IOException
    {
        throw new IOException("Text");
    }
}