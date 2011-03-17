package ch12_entwurfsmuster;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ch12_entwurfsmuster.FilterStrategies.EvenFilter;

/**
 * Beispielprogramm zur Filterung mithilfe des Strategie-Musters
 * <br>
 * Die Klasse <code>StrategyFilterExample2</code> zeigt eine Filterung
 * von einer Liste von Integer-Werten.
 * Es wird hier die Kombination verschiedener Filterkriterien demonstriert.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StrategyFilterExample2
{
    public static List<Integer> filterAll(final List<Integer> inputs, final FilterStrategy filterStrategy)
    {
        final List<Integer> result = new LinkedList<Integer>();
        for (final Integer value : inputs)
        {
            if (filterStrategy.acceptValue(value))
                result.add(value);
        }

        return result;
    }

    public static void main(String[] args)
    {
        final List<Integer> inputs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // EvenFilter und OddFiler (InverseEvenFilter)  
        final FilterStrategy evenFilter = new EvenFilter();
        System.out.println(evenFilter + ": " + filterAll(inputs, evenFilter));

        final FilterStrategy oddFilter = new InverseFilter(evenFilter);
        System.out.println(oddFilter + ": " + filterAll(inputs, oddFilter));

        // PrimeFilter und InversePrimeFilter 
        final FilterStrategy primeFilter = new PrimeFilter();
        System.out.println(primeFilter + ": " + filterAll(inputs, primeFilter));

        final FilterStrategy inversePrimeFilter = new InverseFilter(primeFilter);
        System.out.println(inversePrimeFilter + ": " + filterAll(inputs, inversePrimeFilter));
    }
}
