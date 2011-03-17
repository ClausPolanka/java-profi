package ch05_collections.filtering;

import ch06_applikationsbausteine.RangeCheckUtils;

/**
 * Filterkriterium Konverter-Klasse zum Zugriff auf einzelne Attribute der Klasse SimplePerson
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class SimplePersonAgeFilter implements IFilter<SimplePerson>
{
    private final IFilter<Integer> intFilter;
    
    public SimplePersonAgeFilter(final IFilter<Integer> intFilter)
    {
        RangeCheckUtils.assertReferenceParamNotNull("intFilter", 
                                                    intFilter);
        
        this.intFilter = intFilter;
    }
    
    @Override
    public boolean accept(final SimplePerson object)
    {        
        return intFilter.accept(object.age);
    }
}