package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium NICHT
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class Not<T> implements IFilter<T>
{
    private final IFilter<T> filter;

    public Not(final IFilter<T> filter)
    {
        RangeCheckUtils.assertReferenceParamNotNull("filter", 
                                                    filter);
        
        this.filter = filter;
    }

    @Override
    public boolean accept(final T object)
    {
        return !(filter.accept(object));
    }
}