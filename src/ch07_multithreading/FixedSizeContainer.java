package ch07_multithreading;

/**
 * Interface-Definition zur Erweiterung des Producer-Consumer-Ansatzes
 * auf eine Datenstruktur mit Größenbeschränkung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public interface FixedSizeContainer<T>
{
    /** 
     * put the passed item into this container, blocks if container reached
     * its capacity (the queue is full)
     */
    void putItem(final T item) throws InterruptedException;
    
    /** 
     * returns the next item from this container, blocks if there are no
     * items available (the queue is empty)
     */    
    T takeItem() throws InterruptedException;
}
