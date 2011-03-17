package ch07_multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Beispiel f�r die Nutzung eines ScheduledExecutorService zur
 * zeitgesteuerten Ausf�hrung von Aufgaben
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ScheduledExecutorExample
{
    public static void main(String[] args)
    {
        final int POOL_SIZE = 3;
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(POOL_SIZE);

        // Sofortige Ausf�hrung 
        executorService.schedule(new SampleMessageTask("OnceImmediately"), 0, TimeUnit.SECONDS);

        // Ausf�hrung nach f�nf Sekunden 
        executorService.schedule(new SampleMessageTask("OnceAfter5s"), 5, TimeUnit.SECONDS);

        // Ausf�hrung nach einer Minute 
        executorService.schedule(new SampleMessageTask("OnceAfter1min"), 1, TimeUnit.MINUTES);

        // Shutdown nach der Abarbeitung aller Tasks
        // Problematisch: verbraucht bis zu 50 % CPU, weil Ende gepollt wird??
        // executorService.shutdown();
    }

    public static class SampleMessageTask implements Runnable
    {
        private final String message;

        SampleMessageTask(final String message)
        {
            this.message = message;
        }

        public void run()
        {
            System.out.println(message);
        }
    }

    private ScheduledExecutorExample()
    {
    }
}
