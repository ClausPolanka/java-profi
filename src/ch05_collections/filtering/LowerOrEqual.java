package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium KLEINER GLEICH basierend auf Comparable
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class LowerOrEqual<T extends Object & Comparable<T>> implements IFilter<T>
{
    private final T acceptedValue;
    
    public LowerOrEqual(final T acceptedValue)
    {
        RangeCheckUtils.assertReferenceParamNotNull("acceptedValue", 
                                                    acceptedValue);
        
        this.acceptedValue = acceptedValue;
    }
    
    @Override
    public boolean accept(final T object)
    {        
        return acceptedValue.compareTo(object) >= 0;
    }
}