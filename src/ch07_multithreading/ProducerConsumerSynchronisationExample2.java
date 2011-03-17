package ch07_multithreading;

import java.util.LinkedList;
import java.util.List;

/**
 * Beispielprogramm zur Verdeutlichung des Producer-Consumer-Ansatzes
 * <br>
 * Kritische Bereiche werden hier über synchronized geschützt.
 * Kommunikation geschieht über Pooling auf gemeinsame Datenstruktur items
 * <br>
 * Producer produziert im Takt von 500 ms
 * <br>
 * Consumer schaut im Takt von 1000 ms nach
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ProducerConsumerSynchronisationExample2
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

            while (true)
            {
                final Item newItem = new Item("Item " + counter);
                System.out.println("Producing ... " + newItem);
                SleepUtils.safeSleep(sleepTime);

                // exklusiv hinzufügen
                synchronized (items)
                {
                    items.add(newItem);
                    System.out.println("Produced " + newItem);
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

        public void run()
        {
            while (true)
            {
                // Status-Flag ist als lokale Variable immer Thread-sicher
                boolean noItems = true;
                while (noItems)
                {
                    // exklusiv zugreifen und auslesen
                    synchronized (items)
                    {
                        noItems = (items.size() == 0);
                        if (noItems)
                        {
                            System.out.println("Consumer waiting for items ...");                            
                        }
                    }
                    // Lock wieder freigeben
                    
                    // sleep nicht in synchronized, gibt Lock nicht frei
                    // Producer niemals wï¿½hrend Wartezeit Item erzeugen und ablegen
                    SleepUtils.safeSleep(sleepTime);
                }

                System.out.println("Consuming " + items.remove(0));
            }
        }
    }

    public static void main(final String[] args)
    {
        final List<Item> items = new LinkedList<Item>();

        new Thread(new Producer(items, 500)).start();
        new Thread(new Consumer(items, 1000)).start();
    }
}
