package ch07_multithreading;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ch07_multithreading.ProducerConsumerExample.Producer;

/**
 * Beispielprogramm zur Verdeutlichung der Abstimmung von Threads
 * <br>
 * Es wird ein Producer gestartet und im main()-Thread
 * über join() zeitbeschränkt auf dessen Ende gewartet.

 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ProducerJoinExample
{
    public static void main(String[] args)
    {
        final List<Item> items = new LinkedList<Item>();
        final Thread producerThread = new Thread(new Producer(items, 1000));
        producerThread.start();

        try
        {
            // aktueller Thread wird für 5 Sekunden angehalten 
            producerThread.join(TimeUnit.SECONDS.toMillis(5));
            System.out.println("after join");
            // 1000 ms Produktionszeit und 5000 ms Wartezeit => ca. 5 Items 
            System.out.println("Item-Count after join(): " + items.size());
            // der Producer arbeiet noch 2 Sekunden weiter ... 
            SleepUtils.safeSleep(TimeUnit.SECONDS, 2);
        }
        catch (final InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        // der Producer wird aufgefordert, nun anzuhalten ... 
        producerThread.interrupt();

        // 1000 ms Produktionszeit und 7 s Wartezeit => ca. 7 Items 
        System.out.println("Item-Count after interrupt(): " + items.size());
    }

    private ProducerJoinExample()
    {
    }
}
