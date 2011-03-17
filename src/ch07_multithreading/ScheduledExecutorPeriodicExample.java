package ch07_multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ch07_multithreading.ScheduledExecutorExample.SampleMessageTask;

/**
 * Beispiel für die Nutzung eines ScheduledExecutorService zur
 * zeitgesteuerten, periodischen Ausführung von Aufgaben 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ScheduledExecutorPeriodicExample
{
    public static void main(String[] args)
    {
        final int POOL_SIZE = 3;
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(POOL_SIZE);

        // Eingeplante Ausführung mit INITIAL_DELAY und Verzögerung DELAY 
        final long INITIAL_DELAY_ONE_SEC = 1;
        final long DELAY_30_SECS = 30;
        executorService.scheduleWithFixedDelay(new SampleMessageTask("PeriodicRefreshing"), INITIAL_DELAY_ONE_SEC,
                                               DELAY_30_SECS, TimeUnit.SECONDS);

        // Eingeplante Ausführung mit INITIAL_DELAY und Takt RATE
        final long RATE_10_SECS = 10;
        executorService.scheduleAtFixedRate(new SampleMessageTask("10s FixedRateExecution"), INITIAL_DELAY_ONE_SEC,
                                            RATE_10_SECS, TimeUnit.SECONDS);
        
        // Shutdown nicht sinnvoll, wenn eine periodische Ausführung gewünscht ist
        // executorService.shutdown();
    }

    private ScheduledExecutorPeriodicExample()
    {
    }
}
