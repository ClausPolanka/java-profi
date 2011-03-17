package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium GLEICH basierend auf equals()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class EqualsFilter<T> implements IFilter<T>
{
    private final T acceptedValue;
    
    public EqualsFilter(final T acceptedValue)
    {
        RangeCheckUtils.assertReferenceParamNotNull("acceptedValue", 
                                                    acceptedValue);
        
        this.acceptedValue = acceptedValue;
    }
    
    @Override
    public boolean accept(final T object)
    {        
        return acceptedValue.equals(object);
    }
}