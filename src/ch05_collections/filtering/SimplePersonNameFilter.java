package ch05_collections.filtering;

/**
 * Filterkriterium Konverter-Klasse zum Zugriff auf einzelne Attribute der Klasse SimplePerson
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class SimplePersonNameFilter implements IFilter<SimplePerson>
{
    private final IFilter<String> stringFilter;
    
    public SimplePersonNameFilter(final IFilter<String> stringFilter)
    {
        this.stringFilter = stringFilter;
    }
    
    @Override
    public boolean accept(final SimplePerson object)
    {        
        return stringFilter.accept(object.name);
    }
}