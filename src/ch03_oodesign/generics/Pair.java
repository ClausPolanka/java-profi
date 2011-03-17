package ch03_oodesign.generics;

/**
 * Utility class emulating a pair of objects.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Pair<T1, T2>
{
    private final T1 first;
    private final T2 second;

    public Pair(final T1 first, final T2 second)
    {
        this.first = first;
        this.second = second;
    }

    public final T1 getFirst()       { return first; }
    public final T2 getSecond()      { return second; }
    
    @Override
    public String toString()
    {
        return "Pair [first=" + first + ", second=" + second + "]";
    }
}