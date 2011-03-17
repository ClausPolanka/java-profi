package ch03_oodesign;

/**
 * Beispiel eines einfachen Zählers
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Counter
{
    private int value = 0;

    public Counter()
    {
    }

    public int currentValue()
    {
        return value;
    }
    
    public void increment()
    {
        value++;
    }
    
    public void reset()
    {
        value = 0;
    }
}