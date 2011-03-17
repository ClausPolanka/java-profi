package ch07_multithreading;

/**
 * Beispiel für das Auftreten einer Exception bei der Abarbeitung eines Thread
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ExceptionInThreadsExample
{
    // Achtung: Nur zur Demonstration des Exception Handlings    
    public static void main(final String[] args) throws InterruptedException
    {
        exceptionalMethod();
    }

    static void exceptionalMethod() throws InterruptedException
    {
        final Thread exceptional = new Thread()
        {
            public void run()
            {
                throw new IllegalStateException("run() failed");
            }
        };

        exceptional.start();
        Thread.sleep(1000);
        throw new IllegalStateException("exceptionalMethod() failed");
    }

    private ExceptionInThreadsExample()
    {
    }
}
