package ch12_entwurfsmuster;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Beispielprogramm zur Motivation für das Strategie-Muster
 * <br>
 * Die Klasse <code>StrategyFilterBaseExample</code> zeigt eine Filterung
 * von einer Liste von Integer-Werten und zwei Filter-Kriterien (offenes und
 * geschlossenes Intervall).
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StrategyFilterBaseExample
{
    enum FilterType {
        OPEN_INTERVAL, CLOSED_INTERVAL
    }

    public static List<Integer> filterAll(final List<Integer> inputs, final FilterType filterStrategy,
                                          final int lowerBound, final int upperBound)
    {
        final List<Integer> resultSet = new LinkedList<Integer>();

        if (filterStrategy == FilterType.CLOSED_INTERVAL)
        {
            for (final Integer value : inputs)
            {
                if (value >= lowerBound && value <= upperBound)
                    resultSet.add(value);
            }
        }
        if (filterStrategy == FilterType.OPEN_INTERVAL)
        {
            for (final Integer value : inputs)
            {
                if (value > lowerBound && value < upperBound)
                    resultSet.add(value);
            }
        }

        return resultSet;
    }

    public static void main(String[] args)
    {
        final List<Integer> inputs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("Filtering values for Intervall 2-7");
        System.out.println("Using CloseInterval [2,7] " + filterAll(inputs, FilterType.CLOSED_INTERVAL, 2, 7));
        System.out.println("Using OpenInterval ]2,7[ " + filterAll(inputs, FilterType.OPEN_INTERVAL, 2, 7));
    }
}