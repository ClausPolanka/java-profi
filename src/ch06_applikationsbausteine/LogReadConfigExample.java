package ch06_applikationsbausteine;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Beispiel für das Einlesen und Überwachen der log4j-Konfiguration
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LogReadConfigExample
{
    private static final Logger logger    = Logger.getLogger(LogReadConfigExample.class);

    private static final long   TWO_SECS  = 2000;
    private static final long   FIVE_SECS = 5000;

    private LogReadConfigExample()
    {
    }

    public static void main(final String[] args)
    {
        PropertyConfigurator.configureAndWatch("config/log4j.properties", FIVE_SECS);
        logger.info("LogReadConfigExample started");

        while (!Thread.currentThread().isInterrupted())
        {
            // Log at all levels, filter by adjusting log4j.properties 
            logger.debug("DEBUG");
            logger.info("INFO");
            logger.warn("WARN");
            logger.error("ERROR");
            logger.fatal("FATAL");

            try
            {
                Thread.sleep(TWO_SECS);
            }
            catch (final InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}