package ch07_multithreading;

/**
 * Beispielprogramm zur Verdeutlichung des Producer-Consumer-Ansatzes,
 * Erweiterung auf 1 Producer und 3 Consumer und eine blockierende Datenstruktur
 * <br>
 * Kommunikation wird in der gemeinsamen Datenstruktur items gekapselt
 * <br>
 * Producer produziert im Takt von 1000 ms
 * <br>
 * 3 Consumer schauen im Takt von 100 ms nach
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ProducerMultiConsumerSizedExample
{
    public static class Producer implements Runnable
    {
        final FixedSizeContainer<Item> items;
        final long                     sleepTime;

        public Producer(final FixedSizeContainer<Item> items, final long sleepTime)
        {
            this.items = items;
            this.sleepTime = sleepTime;
        }

        public void run()
        {
            int counter = 0;

            while (!Thread.currentThread().isInterrupted())
            {
                try
                {
                    final Item newItem = new Item("Item " + counter);
                    System.out.println("Producing ... " + newItem);

                    SleepUtils.safeSleep(sleepTime);

                    items.putItem(newItem);
                    System.out.println("Produced " + newItem);

                    counter++;
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static class Consumer implements Runnable
    {
        private final FixedSizeContainer<Item> items;
        private final long                     sleepTime;
        private final String                   consumerName;

        public Consumer(final FixedSizeContainer<Item> items, final long sleepTime, final String consumerName)
        {
            this.items = items;
            this.sleepTime = sleepTime;
            this.consumerName = consumerName;
        }

        public void run()
        {
            while (!Thread.currentThread().isInterrupted())
            {
                try
                {
                    System.out.println(consumerName + " waiting ...");

                    // Zugriff immer durch Datenstruktur abgesichert 
                    final Item item = items.takeItem();
                    System.out.println(consumerName + " consuming " + item);

                    Thread.sleep(sleepTime);
                }
                catch (final InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        final int MAX_QUEUE_SIZE = 7;
        final FixedSizeContainer<Item> items = new FixedSizeListContainer<Item>(MAX_QUEUE_SIZE);

        new Thread(new Producer(items, 1000)).start();

        new Thread(new Consumer(items, 100, "Consumer 1")).start();
        new Thread(new Consumer(items, 100, "Consumer 2")).start();
        new Thread(new Consumer(items, 100, "Consumer 3")).start();
    }
    
    private ProducerMultiConsumerSizedExample()
    {        
    }
}
