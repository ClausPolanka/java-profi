package ch07_multithreading;

import java.util.concurrent.TimeUnit;

import ch07_multithreading.ProducerMultiConsumerSizedExample.Consumer;
import ch07_multithreading.ProducerMultiConsumerSizedExample.Producer;

/**
 * Beispielprogramm zur Verdeutlichung des Producer-Consumer-Ansatzes,
 * Erweiterung auf 1 Producer und 3 Consumer und eine blockierende Datenstruktur
 * <br>
 * Kommunikation wird in der gemeinsamen Datenstruktur items gekapselt
 * <br>
 * Producer produziert im Takt von 100 ms
 * <br>
 * 3 Consumer schauen im Takt von 1000 ms nach
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ProducerMultiConsumerSizedExample2
{
    public static void main(String[] args)
    {
        final int MAX_QUEUE_SIZE = 7;
        final FixedSizeContainer<Item> items = new FixedSizeListContainer<Item>(MAX_QUEUE_SIZE);

        new Thread(new Producer(items, 100)).start();

        // warte 2 Sekunden, dadurch sieht man die Größenbeschränkung
        SleepUtils.safeSleep(TimeUnit.SECONDS, 2);

        new Thread(new Consumer(items, 1000, "Consumer 1")).start();
        new Thread(new Consumer(items, 1000, "Consumer 2")).start();
        new Thread(new Consumer(items, 1000, "Consumer 3")).start();
    }
    
    private ProducerMultiConsumerSizedExample2()
    {        
    }
}
