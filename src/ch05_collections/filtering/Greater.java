package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium GRÖSSER basierend auf Comparable
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class Greater<T extends Object & Comparable<T>> implements IFilter<T>
{
    private final T lowerBound;
    
    public Greater(final T lowerBound)
    {
        RangeCheckUtils.assertReferenceParamNotNull("lowerBound", 
                                                    lowerBound);
        
        this.lowerBound = lowerBound;
    }
    
    @Override
    public boolean accept(final T object)
    {        
        return lowerBound.compareTo(object) < 0;
    }
}