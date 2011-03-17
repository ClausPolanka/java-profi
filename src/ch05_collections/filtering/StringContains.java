package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium ENTHÄLT basierend auf String.contains()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class StringContains implements IFilter<String>
{
    private final String necessarySubstring;
    
    public StringContains(final String necessarySubstring)
    {
        RangeCheckUtils.assertReferenceParamNotNull("necessarySubstring", 
                                                    necessarySubstring);

        this.necessarySubstring = necessarySubstring;
    }
    
    @Override
    public boolean accept(final String object)
    {        
        return object.contains(necessarySubstring);
    }
}