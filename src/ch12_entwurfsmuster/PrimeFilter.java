package ch12_entwurfsmuster;

/**
 * Utility-Klasse zur Filterung mithilfe des Strategie-Musters
 * <br>
 * Die Klasse <code>PrimeFilter</code> realisiert eine Filterung nach Primzahlen, d. h. sie
 * akzeptiert nur Primzahlen.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PrimeFilter implements FilterStrategy
{
    @Override
    public boolean acceptValue(final int value)
    {
        return isPrime(value);
    }

    private boolean isPrime(final int value)
    {
        if (value < 2)
            return false;
        
        if (value == 2)
            return true;
        
        for (int i = 2; i <= Math.sqrt(value); i++)
        {
            // Test auf Teilbarkeit
            if ((value % i) == 0)
                return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "PrimeFilter";
    }    
}