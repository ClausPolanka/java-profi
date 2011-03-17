package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium BEREICH
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class Between<T extends Object & Comparable<T>> implements IFilter<T>
{
    private final T lowerBound;
    private final T higherBound;
    
    public Between(final T lowerBound, final T higherBound)
    {
        RangeCheckUtils.assertReferenceParamNotNull("lowerBound", 
                                                    lowerBound);
        RangeCheckUtils.assertReferenceParamNotNull("higherBound", 
                                                    higherBound);
        
        if (!(lowerBound.compareTo(higherBound) <= 0))
            throw new IllegalArgumentException("lowerBound " + lowerBound + 
                                  " must be <= higherBound " + higherBound);

        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
    }
    
    @Override
    public boolean accept(final T object)
    {        
        return lowerBound.compareTo(object) <= 0 && 
               higherBound.compareTo(object) >= 0;
    }
}