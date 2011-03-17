package ch10_badsmells;

import org.apache.log4j.Logger;

/**
 * Beispiel für die falsche Initialisierungsreihenfolge
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LogInitExceptionExample
{
    private static final long   test_fail = init();
    private static final Logger log       = Logger.getLogger(LogInitExceptionExample.class);

    private static long init()
    {
        log.info("init()");

        return 4712;
    }

    public static void main(final String[] args)
    {
        System.out.println("LogInitExceptionExample");
    }
}
