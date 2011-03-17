package ch07_multithreading;

import java.util.LinkedList;
import java.util.List;

/**
 * Klassen-Definition zur Erweiterung des Producer-Consumer-Ansatzes
 * auf eine Datenstruktur mit Größenbeschränkung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FixedSizeListContainer<T> implements FixedSizeContainer<T>
{
    private final List<T> queuedItems;
    private final int     maxSize;

    public FixedSizeListContainer(final int maxSize)
    {
        this.queuedItems = new LinkedList<T>();
        this.maxSize = maxSize;
    }

    public synchronized void putItem(final T item) throws InterruptedException
    {
        waitWhileQueueFull();
        // aufwecken von waitWhileQueueEmpty()
        notify();

        queuedItems.add(item);
    }

    public synchronized T takeItem() throws InterruptedException
    {
        waitWhileQueueEmpty();
        // aufwecken von waitWhileQueueFull() 
        notify();

        return queuedItems.remove(0);
    }

    private void waitWhileQueueFull() throws InterruptedException
    {
        while (queuedItems.size() == maxSize)
            wait();
    }

    private void waitWhileQueueEmpty() throws InterruptedException
    {
        while (queuedItems.size() == 0)
            wait();
    }
}