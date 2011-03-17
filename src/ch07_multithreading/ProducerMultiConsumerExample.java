package ch07_multithreading;

import java.util.LinkedList;
import java.util.List;

import ch07_multithreading.ProducerConsumerExample.Producer;

/**
 * Beispielprogramm zur Verdeutlichung des Producer-Consumer-Ansatzes,
 * Erweiterung auf 1 Producer und 3 Consumer
 * <br>
 * Kritische Bereiche werden hier �ber synchronized gesch�tzt.
 * Kommunikation geschieht �ber gemeinsame Datenstruktur items
 * mithilfe von wait() und notifyAll()
 * <br>
 * Producer produziert im Takt von 1000 ms
 * <br>
 * 3 Consumer schauen im Takt von 100 ms nach
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ProducerMultiConsumerExample
{
    public static class Consumer implements Runnable
    {
        private final List<Item> items;
        private final long       sleepTime;
        private final String     consumerName;

        public Consumer(final List<Item> items, final long sleepTime, final String consumerName)
        {
            this.items = items;
            this.sleepTime = sleepTime;
            this.consumerName = consumerName;
        }

        /** create an item every 2 seconds */
        public void run()
        {
            while (!Thread.currentThread().isInterrupted())
            {
                synchronized (items)
                {
                    try
                    {
                        System.out.println(consumerName + " waiting ...");

                        waitForItemsAvailable(items);

                        // Zugriff immer durch waitForItemsAvailable() sicher 
                        final Item item = items.remove(0);
                        System.out.println(consumerName + " consuming " + item);

                        SleepUtils.safeSleep(sleepTime);
                    }
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    private static void waitForItemsAvailable(final List<Item> items) throws InterruptedException
    {
        while (items.size() == 0)
            items.wait();
    }

    public static void main(String[] args)
    {
        final List<Item> items = new LinkedList<Item>();

        new Thread(new Producer(items, 1000)).start();

        new Thread(new Consumer(items, 100, "Consumer 1")).start();
        new Thread(new Consumer(items, 100, "Consumer 2")).start();
        new Thread(new Consumer(items, 100, "Consumer 3")).start();
    }
    
    private ProducerMultiConsumerExample()
    {        
    }
}
