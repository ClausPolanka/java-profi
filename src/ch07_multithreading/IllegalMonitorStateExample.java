package ch07_multithreading;

/**
 * Beispiel für das Auftreten einer IllegalMonitorStateException
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class IllegalMonitorStateExample
{
    static Integer lock = new Integer(1);

    public static void main(String[] args)
    {
        synchronized (lock)
        {
            System.out.println("lock is " + lock);
            lock++;
            System.out.println("lock is " + lock);
            lock.notifyAll();
        }
        System.out.println("notWorking");
    }

    private IllegalMonitorStateExample()
    {
    }
}