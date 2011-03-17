package ch07_multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Beispiel für eine Implementierung des Callable-Interfaces
 * <br>
 * Einizger Sinn ist die Berechnung der Wartezeit
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CalcDurationInMs implements Callable<Long>
{
    private final TimeUnit timeUnit;
    private final long     duration;

    CalcDurationInMs(final TimeUnit timeUnit, final long duration)
    {
        this.timeUnit = timeUnit;
        this.duration = duration;
    }

    @Override
    public Long call() throws Exception
    {
        timeUnit.sleep(duration);

        return timeUnit.toMillis(duration);
    }
}
