package ch16_optimierungen;

import org.apache.log4j.PropertyConfigurator;

/**
 * Beispielprogramm zur Demonstration der Geschwindigkeit von komplexeren 
 * mathematischen Berechnungen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class MathExpExample
{   
    static
    {
        PropertyConfigurator.configureAndWatch("config/log4j.properties", 5000);
    }
    
    public static void main(String[] args)
    {
        final int counter = 1000 * 1000;
        final double[] testArray = new double[counter];

        PerformanceUtils.startMeasure("MathExpExample");
        for (int i = 0; i < counter; i++)
        {
            testArray[i] = Math.exp(i);
        }
        PerformanceUtils.stopMeasure("MathExpExample");
        PerformanceUtils.printTimingResult("MathExpExample");
    }

    private MathExpExample()
    {
    }
}
