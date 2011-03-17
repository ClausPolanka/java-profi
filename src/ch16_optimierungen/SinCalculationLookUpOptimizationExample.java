package ch16_optimierungen;

/**
 * Beispielprogramm zur Demonstration des Einflusses von Vorausberechnungen mit Look-up-Tabellen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SinCalculationLookUpOptimizationExample
{
    private static final double[] sinLookUp = new double[360];
    
    public static void main(String[] args)
    {
        final long counter = 1000 * 1000;

        PerformanceUtils.startMeasure("calcSin");
        for (long i = 0; i < counter; i++)
        {
            calcSin(counter);
        }
        PerformanceUtils.stopMeasure("calcSin");
        PerformanceUtils.printTimingResultWithAverage("calcSin", counter);

        PerformanceUtils.startMeasure("calcSinOptimized");
        populateLookUpTable();
        for (long i = 0; i < counter; i++)
        {
            calcSinOptimized(counter);
        }
        PerformanceUtils.stopMeasure("calcSinOptimized");
        PerformanceUtils.printTimingResultWithAverage("calcSinOptimized", counter);
    }
    
    private static double calcSin(final double x)
    {
        return Math.sin(x);
    }
    
    private static double calcSinOptimized(final double x)
    {
        final int lookUpPos = (int) x % 360;
        return sinLookUp[lookUpPos];
    }
    
    private static void populateLookUpTable()
    {
        for (int i = 0; i < 360; i++)
        {
            sinLookUp[i] = Math.sin(i);
        }
    }
    
    private SinCalculationLookUpOptimizationExample()
    {        
    }
}
