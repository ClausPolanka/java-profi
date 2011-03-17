package ch05_collections.filtering;

/**
 * Filterkriterium UND
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class And<T> implements IFilter<T>
{
    private final IFilter<T> filter1;
    private final IFilter<T> filter2;
    
    public And(final IFilter<T> filter1, final IFilter<T> filter2)
    {
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    @Override
    public boolean accept(final T object)
    {
        return (filter1.accept(object) && filter2.accept(object));
    }
}