package ch05_collections.filtering;

import java.util.Arrays;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration eines Zahlenfilters
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class SimpleFilterExample
{
    public static void main(final String[] args)
    {
        final List<Integer> intValues = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // int-Zahlenfilter auf den Wert 2
        final IFilter<Integer> numberFilter = new EqualsFilter<Integer>(2);
        final List<Integer> filteredValues = FilterUtils.applyfilter(intValues, numberFilter);
        System.out.println(filteredValues);
    }
}
