package ch16_optimierungen;

/**
 * Beispielprogramm zur Demonstration verschiedener Peephole-Optimierungen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PeepholeOptimizationExample
{
    // Source-Code
    private static long multiplyBy6(final long x)
    {
        return x * 6;
    }

    private static long multiplyBy6Optimized(final long x)
    {
        return (x << 2) + (x << 1);
    }

    // Inlinig
    private static int processValues(final int x, final int y)
    {
        return multiply(x, y);
    }

    private static int multiply(final int x, final int y)
    {
        return x * y;
    }

    // Integration der Methode multiply()
    private static int processValuesInlined(final int x, final int y)
    {
        return x * y;
    }

    // Loop-Unrolling
    private static int conventionalLoop()
    {
        int x = 0;
        for (int i = 1; i < 4; i++)
        {
            x += i * i;
        }
        return x;
    }

    private static int unrolledLoop()
    {
        int x = 0;
        x += 1 * 1;
        x += 2 * 2;
        x += 3 * 3;
        return x;
    }

    private static void assigment()
    {
        int x = 0;
        x = 4 * 25;
        // ...
    }

    private static void assigmentOptimized()
    {
        int x = 100; // Peephole: Zuweisung und Multiplikation
        // ...
    }

    public static void main(String[] args)
    {
        final long counter = 1000L * 1000 * 1000;
        long value = 0;

        PerformanceUtils.startMeasure("multiplyBy6");
        for (long i = 0; i < counter; i++)
        {
            value = multiplyBy6(i);
        }
        PerformanceUtils.stopMeasure("multiplyBy6");
        PerformanceUtils.printTimingResultWithAverage("multiplyBy6", counter);

        // vermeide Hot-Spot-Weg-Optimierungen der Zuweisungen
        System.out.println("Result: " + value);

        PerformanceUtils.startMeasure("multiplyBy6Optimized");
        for (int i = 0; i < counter; i++)
        {
            value = multiplyBy6Optimized(i);
        }
        PerformanceUtils.stopMeasure("multiplyBy6Optimized");
        PerformanceUtils.printTimingResultWithAverage("multiplyBy6Optimized", counter);
        
        // vermeide Hot-Spot-Weg-Optimierungen der Zuweisungen
        System.out.println("Result: " + value);
    }
}
