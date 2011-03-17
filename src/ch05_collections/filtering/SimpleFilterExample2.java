package ch05_collections.filtering;

import java.util.Arrays;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration komplexerer Zahlenfilter
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SimpleFilterExample2
{
    public static void main(final String[] args)
    {
        final List<Integer> intValues = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        // Ermittle alle Werte, die "NICHT im Bereich 4-11" liegen 
        final IFilter<Integer> inverseNumberFilter = new Not<Integer>(new Between<Integer>(4, 11));
        final List<Integer> filteredValues1 = FilterUtils.applyfilter(intValues, inverseNumberFilter);
        System.out.println(filteredValues1);

        // Ermittle alle Werte, die "im Bereich 3-7 liegen oder größer als 12" sind 
        final IFilter<Integer> range2_7OrGreater12 = new Or<Integer>(new Between<Integer>(3, 7),
                                                                     new Greater<Integer>(12));
        final List<Integer> filteredValues2 = FilterUtils.applyfilter(intValues, range2_7OrGreater12);
        System.out.println(filteredValues2);
    }
}
