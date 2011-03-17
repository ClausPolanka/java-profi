package ch07_multithreading;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Klassen-Definition zur Erweiterung des Producer-Consumer-Ansatzes
 * auf eine Datenstruktur mit Gr��enbeschr�nkung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class BlockingFixedSizeBuffer<T> implements FixedSizeContainer<T>
{
    private final Lock      lock     = new ReentrantLock();
    private final Condition notFull  = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final List<T>   queuedItems;
    private final int       maxSize;

    public BlockingFixedSizeBuffer(final int maxSize)
    {
        this.queuedItems = new LinkedList<T>();
        this.maxSize = maxSize;
    }

    public void putItem(final T item) throws InterruptedException
    {
        lock.lock();
        try
        {
            waitWhileQueueFull();
            notEmpty.signal();

            queuedItems.add(item);
        }
        finally
        {
            lock.unlock();
        }
    }

    public T takeItem() throws InterruptedException
    {
        lock.lock();
        try
        {
            waitWhileQueueEmpty();
            notFull.signal();

            return queuedItems.remove(0);
        }
        finally
        {
            lock.unlock();
        }
    }

    private void waitWhileQueueFull() throws InterruptedException
    {
        while (maxSize == queuedItems.size())
            notFull.await();
    }

    private void waitWhileQueueEmpty() throws InterruptedException
    {
        while (queuedItems.size() == 0)
            notEmpty.await();
    }
}
