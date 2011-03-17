package ch08_advancedjava.cloneable;

/**
 * Beispielklasse zur Demonstration des Klonens beim Einsatz von Vererbung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class DerivedCloneable extends BaseCloneable2Example.BaseCloneable
{
    private final String info;

    public DerivedCloneable(final int id, final long value, final String info)
    {
        super(id, value);
        this.info = info;
    }

    @Override
    public String toString()
    {
        return "DerivedClonable info='" + info + "' / " + super.toString();
    }
}
