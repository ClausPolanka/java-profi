package ch06_applikationsbausteine;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

/**
 * Einführendes Beispiel, um die Verarbeitung mit Layout, Appender und
 * Log-Leveln kennezulernen 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LogConfigExample
{
    private static final Logger  logger  = Logger.getLogger(LogConfigExample.class);

    private static final String  LOGFILE = "LogDatei.log";
    private static final boolean APPEND  = true;

    public static void main(String[] args)
    {
        // Layout erzeugen 
        final SimpleLayout layout = new SimpleLayout();

        // ConsoleAppender dem Logger zuordnen 
        logger.addAppender(new ConsoleAppender(layout));

        // FileAppender erzeugen und dem Logger zuordnen 
        try
        {
            logger.addAppender(new FileAppender(layout, LOGFILE, APPEND));
        }
        catch (final IOException ex)
        {
            logger.warn("Can't create FileAppender for " + new File(LOGFILE).getAbsolutePath(), ex);
        }

        // Log-Level auf WARN und dann testweise Meldungen ausgeben          
        logger.setLevel(Level.WARN);

        logger.info("Filtered --- Not displayed");
        logger.warn("Warning should be printed");
    }
}