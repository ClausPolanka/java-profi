package ch16_optimierungen;

import org.apache.log4j.BasicConfigurator;

/**
 * Beispielprogramm zur Demonstration des Einflusses von Pooling bei Booleans
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class BooleanPoolingOptimization
{
    public static void main(String[] args)
    {
        BasicConfigurator.configure();
        
        final int counter = 1000 * 1000 * 10;
        final Boolean[] testArray = new Boolean[counter];

        System.out.println(MemoryInfo.statistics());
        performTests(testArray, "Boolean.TRUE", false);
        MemoryInfo.gcAndSleep5s();

        System.out.println(MemoryInfo.statistics());

        performTests(testArray, "new Boolean(true)", true);
        MemoryInfo.gcAndSleep5s();
        System.out.println(MemoryInfo.statistics());
    }

    public static void performTests(final Boolean[] testArray, 
                                    final String info, 
                                    final boolean createNew)
    {
        PerformanceUtils.startMeasure(info);
        for (int i = 0; i < testArray.length; i++)
        {
            if (createNew)
                testArray[i] = new Boolean(true);
            else
                testArray[i] = Boolean.TRUE;
        }
        PerformanceUtils.stopMeasure(info);
        PerformanceUtils.printTimingResult(info);
    }

    private BooleanPoolingOptimization()
    {
    }
}
