package ch07_multithreading;

import java.util.concurrent.TimeUnit;

/**
 * Utility-Klasse zur Vereinfachung der Behandlung der InterruptedException
 * bei Thread.sleep()-Aufrufen 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SleepUtils
{
    public static void safeSleep(final TimeUnit timeUnit, final long duration)
    {
        safeSleep(timeUnit.toMillis(duration));
    }
    
    public static void safeSleep(final long durationInMilliSecs)
    {
        try
        {
            Thread.sleep(durationInMilliSecs);
        }
        catch (final InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    
    private SleepUtils()
    {        
    }
}
