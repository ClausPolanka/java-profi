package ch06_applikationsbausteine;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Einführendes Beispiel für Log-Ausgaben über log4j
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LoggingExample
{
    private static final Logger LOGGER = Logger.getRootLogger();

    public static void main(final String[] args)
    {
        // Standardkonfiguration mit Konsolenausgabe          
        BasicConfigurator.configure();

        // Log-Meldungen ausgeben          
        LOGGER.info("Info-Meldung aus LoggingExample.");
        LOGGER.error("Error-Meldung aus LoggingExample.");
    }
}