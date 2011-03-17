package ch07_multithreading;

import java.util.Date;

import ch03_oodesign.Counter;

/**
 * Beispiel für die Ausführung von Threads basierend auf Runnable bzw. auf Thread
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ThreadExample
{
    public static void main(final String[] args)
    {
        final Thread derivedThread = new CountingThread();
        derivedThread.start();

        final Thread threadWithRunnable = new Thread(new DatePrinter());
        threadWithRunnable.start();
    }

    public static class CountingThread extends Thread
    {
        final Counter counter = new Counter();

        public void run()
        {
            while (counter.currentValue() < 50)
            {
                System.out.println(counter.currentValue());
                counter.increment();
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    /* ausnahmesweise hier ignorieren */
                }
            }
        }
    }

    public static class DatePrinter implements Runnable
    {
        public void run()
        {
            for (int i = 0; i < 10; i++)
            {
                System.out.println(new Date());
                try
                {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e)
                {
                    /* ausnahmesweise hier ignorieren */
                }
            }
        }
    }
}