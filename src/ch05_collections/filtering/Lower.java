package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium KLEINER basierend auf Comparable
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class Lower<T extends Object & Comparable<T>> implements IFilter<T>
{
    private final T acceptedValue;
    
    public Lower(final T acceptedValue)
    {
        RangeCheckUtils.assertReferenceParamNotNull("acceptedValue", 
                                                    acceptedValue);
        
        this.acceptedValue = acceptedValue;
    }
    
    @Override
    public boolean accept(final T object)
    {        
        return acceptedValue.compareTo(object) > 0;
    }
}