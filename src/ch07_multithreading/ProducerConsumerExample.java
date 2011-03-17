package ch07_multithreading;

import java.util.LinkedList;
import java.util.List;

/**
 * Beispielprogramm zur Verdeutlichung des Producer-Consumer-Ansatzes
 * <br>
 * Kritische Bereiche werden hier über synchronized geschützt.
 * Kommunikation geschieht über gemeinsame Datenstruktur items
 * mithilfe von wait() und notifyAll()
 * <br>
 * Producer produziert im Takt von 1000 ms
 * <br>
 * Consumer schaut im Takt von 500 ms nach
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ProducerConsumerExample
{
    public static class Producer implements Runnable
    {
        private final List<Item> items;
        private final long       sleepTime;

        public Producer(final List<Item> items, final long sleepTime)
        {
            this.items = items;
            this.sleepTime = sleepTime;
        }

        public void run()
        {
            int counter = 0;

            while (!Thread.currentThread().isInterrupted())
            {
                final Item newItem = new Item("Item " + counter);
                System.out.println("Producing ... " + newItem);

                SleepUtils.safeSleep(sleepTime);

                synchronized (items)
                {
                    items.add(newItem);
                    System.out.println("Produced " + newItem);
                    // Informiere wartende Threads
                    items.notifyAll();
                }

                counter++;
            }
        }
    }

    public static class Consumer implements Runnable
    {
        private final List<Item> items;
        private final long       sleepTime;

        public Consumer(final List<Item> items, final long sleepTime)
        {
            this.items = items;
            this.sleepTime = sleepTime;
        }

        // ACHTUNG: Problematische Realisierung !!!         
        public void run()
        {
            while (!Thread.currentThread().isInterrupted())
            {
                synchronized (items)
                {
                    try
                    {
                        System.out.println("Consumer waiting ...");
                        items.wait();
                        // ACHTUNG: Unsicherer Zugriff 
                        System.out.println("Consuming " + items.remove(0));
                    }
                    catch (final InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                    }
                }

                SleepUtils.safeSleep(sleepTime);
            }
        }
    }

    public static void main(String[] args)
    {
        final List<Item> items = new LinkedList<Item>();

        new Thread(new Producer(items, 1000)).start();
        new Thread(new Consumer(items, 500)).start();
    }
}
