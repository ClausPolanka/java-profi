package ch06_applikationsbausteine;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Beispiel für wenig informatives Logging von Exception
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ExceptionLoggingExample
{
    private static final Logger log = Logger.getLogger("ExceptionLoggingExample");

    public static void main(String[] args)
    {
        PropertyConfigurator.configureAndWatch("config/log4j.properties");

        try
        {
            methodThrowingException();
        }
        catch (final IOException e)
        {
            // SCHLECHT: nur String-Info ohne Stacktrace!          
            log.error("An I/O error occurred! " + e);
            log.error("An I/O error occurred! " + e.getMessage());
        }
    }

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