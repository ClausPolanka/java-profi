package ch12_entwurfsmuster;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ch12_entwurfsmuster.FilterStrategies.ClosedInterval;
import ch12_entwurfsmuster.FilterStrategies.OpenInterval;

/**
 * Beispielprogramm zur Filterung mithilfe des Strategie-Musters
 * <br>
 * Die Klasse <code>StrategyFilterExample</code> zeigt eine Filterung
 * von einer Liste von Integer-Werten und zwei Filter-Kriterien
 * (offenes und geschlossenes Intervall).
 * Als Erweiterung zum einführenden Beispiel  <code>StrategyFilterBaseExample</code>
 * wird die Filterung hier nicht in der Methode <code>filterAll()</code> ausprogrammiert,
 * sondern stattdessen an eine <code>FilterStrategy</code> delegiert. Dieses Interface
 * wird hier von den beiden Realisierungen <code>ClosedInterval</code> und <code>OpenInterval</code>
 * erfüllt.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
interface FilterStrategy
{
    public abstract boolean acceptValue(final int value);
}

public final class StrategyFilterExample
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

        System.out.println("Filtering values for Intervall 2-7");
        final FilterStrategy closedInterval = new ClosedInterval(2, 7);
        System.out.println("Using " + closedInterval + " " + filterAll(inputs, closedInterval));
        final FilterStrategy openInterval = new OpenInterval(2, 7);
        System.out.println("Using " + openInterval + " " + filterAll(inputs, openInterval));
    }
}
