package ch07_multithreading;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface ChangeListener
{
    void update();
}

/**
 * Beispielprogramm, das einen Benachrichtigungsmechanismus mithilfe von
 * synchronized realisiert
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ObserverWithSynchronizedExample
{
    private int                        state     = 0;

    private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();

    public synchronized void addChangeListener(final ChangeListener listener)
    {
        listeners.add(listener);
    }

    public synchronized void removeChangeListener(final ChangeListener listener)
    {
        listeners.remove(listener);
    }

    private synchronized void notifyChangeListeners()
    {
        final Iterator<ChangeListener> it = listeners.iterator();
        while (it.hasNext())
        {
            final ChangeListener listener = it.next();

            listener.update();
        }
    }

    public synchronized void changeState(final int newValue)
    {
        state = newValue;
        notifyChangeListeners();
    }

    public static void main(String[] args)
    {
        final ObserverWithSynchronizedExample example = new ObserverWithSynchronizedExample();

        example.addChangeListener(new ChangeListener()
        {
            @Override
            public void update()
            {
                System.out.println("Recieved state change message");
            }
        });
        example.changeState(4711);
    }

    private ObserverWithSynchronizedExample()
    {
    }
}
