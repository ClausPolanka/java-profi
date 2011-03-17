package ch12_entwurfsmuster;

/**
 * Utility-Klasse zur Filterung mithilfe des Strategie-Musters
 * <br>
 * Die Klasse <code>InverseFilter</code> erlaubt es beliebige Filterkriterien zu
 * invertieren. Sie ist gem‰ﬂ dem Dekorierer-Muster realisiert. 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class InverseFilter implements FilterStrategy
{
    private final FilterStrategy originalFilterStrategy;

    public InverseFilter(final FilterStrategy originalFilterStrategy)
    {
        if (originalFilterStrategy == null)
            throw new IllegalArgumentException("parameter " + "'originalFilterStrategy' must not be null!");

        this.originalFilterStrategy = originalFilterStrategy;
    }

    @Override
    public boolean acceptValue(final int value)
    {
        return !originalFilterStrategy.acceptValue(value);
    }

    @Override
    public String toString()
    {
        return "InverseFilter " + originalFilterStrategy;
    }
}