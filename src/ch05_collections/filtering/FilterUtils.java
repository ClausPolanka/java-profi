package ch05_collections.filtering;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility-Klasse zur Anwendung eines Filters auf eine Liste von Werten 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FilterUtils
{
    public static <T> List<T> applyfilter(final List<T> values, 
                                          final IFilter<T> filter)
    {
        final List<T> filteredValues = new ArrayList<T>();
        for (final T current : values)
        {
            if (filter.accept(current))
                filteredValues.add(current);
        }
                
        return filteredValues;
    }
}
