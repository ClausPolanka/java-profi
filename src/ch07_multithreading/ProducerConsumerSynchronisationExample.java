package ch07_multithreading;

import java.util.LinkedList;
import java.util.List;

/**
 * Beispielprogramm zur Verdeutlichung des Producer-Consumer-Ansatzes
 * <br>
 * Kritische Bereiche werden hier über synchronized geschützt.
 * Kommunikation geschieht über Pooling auf gemeinsame Datenstruktur items
 * <br>
 * Producer produziert im Takt von 1 Sekunde
 * <br>
 * Consumer schaut im Takt von 500 ms nach
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ProducerConsumerSynchronisationExample
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
                // Ereugen eines Items 
                final Item newItem = new Item("Item " + counter);
                System.out.println("Producing ... " + newItem);

                SleepUtils.safeSleep(sleepTime);

                // Lock akquirieren, dann exklusiv zugreifen und hinzufügen 
                synchronized (items)
                {
                    items.add(newItem);
                    System.out.println("Produced " + newItem);
                }
                // Lock wird automatisch freigeben

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

        public void run()
        {
            while (!Thread.currentThread().isInterrupted())
            {
                // Status-Flag ist als lokale Variable immer Thread-sicher 
                boolean noItems = true;
                while (noItems)
                {
                    // Lock akquirieren, dann exklusiv zugreifen und auslesen 
                    synchronized (items)
                    {
                        noItems = (items.size() == 0);
                        if (noItems)
                        {
                            System.out.println("Consumer waiting for items ...");
                        }
                    }
                    // Lock wird automatisch freigeben

                    // sleep() nicht in synchronized aufrufen, Lock wird 
                    // nicht freigegeben => Producer könnte so niemals            
                    // während der Wartezeit Items erzeugen und ablegen 
                    SleepUtils.safeSleep(sleepTime);
                }

                System.out.println("Consuming " + items.remove(0));
            }
        }
    }

    public static void main(final String[] args)
    {
        final List<Item> items = new LinkedList<Item>();

        new Thread(new Producer(items, 1000)).start();
        new Thread(new Consumer(items, 500)).start();
    }
}
