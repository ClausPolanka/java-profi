package ch07_multithreading;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Beispiel für die parallele Abarbeitung mit einem ExecutorService
 * <br>
 * Zwei Jobs vom Typ CalcDurationInMs mit 5 und 10 Sekunden Wartezeit 
 * werden angestossen und dann wird auf deren Ende gewartet
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ExecutorServiceExample
{
    public static void main(String[] args)
    {
        final int POOL_SIZE = 3;
        final ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

        // Definition und Start zweier Tasks 
        final Future<Long> future1 = executorService.submit(new CalcDurationInMs(TimeUnit.SECONDS, 5));
        final Future<Long> future2 = executorService.submit(new CalcDurationInMs(TimeUnit.SECONDS, 10));

        System.out.println("Start: " + new Date());
        try
        {
            // synchron auf das Ende von Task 1 warten 
            final Long result1 = future1.get();
            System.out.println("After Job 1: " + new Date());
            System.out.println(result1);

            // Zugriff nach 5s sollte false liefern
            System.out.println("isDone? Job 2: " + future2.isDone());

            // synchron auf das Ende von Task 2 warten
            final Long result2 = future2.get();
            System.out.println("After Job 2: " + new Date());
            System.out.println(result2);
        }
        catch (final InterruptedException e)
        {
            // Kann in diesem Beispiel nicht auftreten 
        }
        catch (final ExecutionException e)
        {
            // Kann in diesem Beispiel nicht auftreten, s. o. 
        }

        // Aufruf, um Thread-Pool zu beenden und JVM-Terminierung zu ermöglichen 
        executorService.shutdown();
    }

    private ExecutorServiceExample()
    {
    }
}
