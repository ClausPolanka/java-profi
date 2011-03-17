package ch03_oodesign;

/**
 * Beispiel eines Z�hlers - der bei einem bestimmten Stand einen �berlauf verursacht und
 * die Z�hlung wieder von 0 beginnt
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CounterWithOverflow
{
    private static final int COUNTER_MAX = 100;
    
    private int value = 0;
    private int overflowCount = 0;
    
    public CounterWithOverflow()
    {
    }

    public int currentValue()
    {
        return value;
    }
    
    public int overflowCount()
    {
        return overflowCount;
    }
    
    public void reset()
    {
        value = 0;
        overflowCount = 0;
    }
        
    public void increment()
    {
        if (value == COUNTER_MAX-1)
        {
            value = 0;
            overflowCount++;
        }
        else
        {
            value++;
        }
    }
}