package ch07_multithreading;

import org.apache.log4j.Logger;

/**
 * Beispiel für die Protokollierung von Exceptions 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LoggingUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
    private static final Logger log = Logger.getLogger(LoggingUncaughtExceptionHandler.class);

    private final Logger        loggerToUse;

    LoggingUncaughtExceptionHandler(final Logger loggerToUse)
    {
        this.loggerToUse = loggerToUse;
    }

    @Override
    public void uncaughtException(final Thread thread, final Throwable throwable)
    {
        loggerToUse.error("Unexpected exception occured: ", throwable);
    }

    public static void main(final String[] args) throws InterruptedException
    {
        // Ausgabe auf Logger umleiten
        Thread.setDefaultUncaughtExceptionHandler(new LoggingUncaughtExceptionHandler(log));

        ExceptionInThreadsExample.exceptionalMethod();
        throw new IllegalStateException("execute main() failed");
    }
}
