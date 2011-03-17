package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium ODER
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class Or<T> implements IFilter<T>
{
    private final IFilter<T> filter1;
    private final IFilter<T> filter2;
    
    public Or(final IFilter<T> filter1, final IFilter<T> filter2)
    {
        RangeCheckUtils.assertReferenceParamNotNull("filter1", 
                                                    filter1);
        RangeCheckUtils.assertReferenceParamNotNull("filter2", 
                                                    filter2);
        
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    @Override
    public boolean accept(final T object)
    {
        return (filter1.accept(object) || filter2.accept(object));
    }
}