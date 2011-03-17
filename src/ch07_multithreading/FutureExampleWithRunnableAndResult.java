package ch07_multithreading;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Beispiel für die Abarbeitung eines Runnable mit einem
 * Executorservice
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FutureExampleWithRunnableAndResult
{
    public static final class ModifyingTask implements Runnable
    {
        private final List<Integer> result;

        ModifyingTask(final List<Integer> result)
        {
            this.result = result;
        }

        @Override
        public void run()
        {
            result.add(Integer.valueOf(4711));
        }

        public List<Integer> getResult()
        {
            return result;
        }
    }

    public static void main(String[] args)
    {
        final int POOL_SIZE = 3;
        final ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);

        final List<Integer> result = new ArrayList<Integer>();
        final Future<List<Integer>> future = executorService.submit(new ModifyingTask(result), result);

        try
        {
            System.out.println("isDone? " + future.isDone());

            final List<Integer> calculatedResult = future.get();
            System.out.println("After Job: " + new Date());
            System.out.println(calculatedResult);

        }
        catch (final InterruptedException e)
        {
            // Kann in diesem Beispiel nicht auftreten 
        }
        catch (final ExecutionException e)
        {
            // Kann in diesem Beispiel nicht auftreten, wird geworfen wenn 
            // versucht wird, auf ein Ergebnis eines Tasks zuzugreifen, der
            // mit einer Exception beendet wurde 
        }

        executorService.shutdown();
    }
}
