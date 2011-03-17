package ch03_oodesign;

/**
 * Beispiel eines einfachen Zählers - 3te Überarbeitung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Counter3
{
    private int count;

    public Counter3()
    {
        reset();
    }

    public int currentValue()
    {
        return count;
    }
    
    public void increment()
    {
        count++;
    }
    
    public void reset()
    {
        count = 0;
    }
}