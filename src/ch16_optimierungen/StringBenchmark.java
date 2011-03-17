package ch16_optimierungen;

import java.util.Formatter;

/**
 * Beispielprogramm zuer Ermittlung der Laufzeit verschiedener Formen der
 * Stringkonkatenation
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringBenchmark
{
    // linksbündig im Buch    
    public static void main(String[] args)
    {
        final String name = "Sarah vom Auetal";
        final int counter = 1000000;
        String result = "";

        // Messung mit String + 
        result = measureStringPlus(name, counter, result);

        // Messung mit String += 
        result = measureStringPlusEquals(name, counter, result);

        // Messung mit StringBuilder 
        result = measureStringBuilderAppend(name, counter, result);

        // Messung mit StringBuilder und initialer Kapazität
        result = measurePresizedStringBuilderAppend(name, counter, result);

        // Messung mit String.format()
        result = measureStringFormat(name, counter, result);

        // Messung mit StringBufferr 
        result = measureStringBufferAppend(name, counter, result);

        // Messung mit StringBufferr und initialer Kapazität
        result = measurePresizedStringBufferAppend(name, counter, result);

        // Messung mit String und Formatter
        result = measureStringAndFormatter(name, counter, result);

        // Messung mit StringBuilder, initialer Kapazität und Formatter
        result = measureStringBuilderAndFormatter(name, counter, result);

        // Ausgabe des Ergebnises vermeidet Weg-Optimieurng der Berechnungen
        System.out.println(result);
    }

    private static String measureStringBuilderAndFormatter(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("StringBuilder And Formatter");
        for (int i = 0; i < counter; i++)
        {
            final StringBuilder sb = new StringBuilder(100);
            final Formatter formatter = new Formatter(sb);
            result = formatter.format("Mein Hund ist %1$d Jahre alt und heißt %2$s.", i, name).toString();
        }
        PerformanceUtils.stopMeasure("StringBuilder And Formatter");
        PerformanceUtils.printTimingResultWithAverage("StringBuilder And Formatter", counter);
        return result;
    }

    private static String measureStringFormat(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("String.format()");
        for (int i = 0; i < counter; i++)
        {
            result = String.format("Mein Hund ist %d Jahre alt und heißt %s.", i, name);
        }
        PerformanceUtils.stopMeasure("String.format()");
        PerformanceUtils.printTimingResultWithAverage("String.format()", counter);
        return result;
    }

    private static String measureStringAndFormatter(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("String And Formatter");
        for (int i = 0; i < counter; i++)
        {
            final Formatter formatter = new Formatter();
            result = formatter.format("Mein Hund ist %1$d Jahre alt und heißt %2$s.", i, name).toString();
        }
        PerformanceUtils.stopMeasure("String And Formatter");
        PerformanceUtils.printTimingResultWithAverage("String And Formatter", counter);
        return result;
    }

    private static String measureStringBufferAppend(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("StringBuffer");
        for (int i = 0; i < counter; i++)
        {
            final StringBuffer sb = new StringBuffer();
            sb.append("Mein Hund ist ").append(i).append(" Jahre alt und heißt ").append(name).append(".");
            result = sb.toString();
        }
        PerformanceUtils.stopMeasure("StringBuffer");
        PerformanceUtils.printTimingResultWithAverage("StringBuffer", counter);
        return result;
    }

    private static String measurePresizedStringBufferAppend(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("StringBuffer Presized");
        for (int i = 0; i < counter; i++)
        {
            final StringBuffer sb = new StringBuffer(100);
            sb.append("Mein Hund ist ").append(i).append(" Jahre alt und heißt ").append(name).append(".");
            result = sb.toString();
        }
        PerformanceUtils.stopMeasure("StringBuffer Presized");
        PerformanceUtils.printTimingResultWithAverage("StringBuffer Presized", counter);
        return result;
    }

    private static String measurePresizedStringBuilderAppend(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("StringBuilder Presized");
        for (int i = 0; i < counter; i++)
        {
            final StringBuilder sb = new StringBuilder(100);
            sb.append("Mein Hund ist ").append(i).append(" Jahre alt und heißt ").append(name).append(".");
            result = sb.toString();
        }
        PerformanceUtils.stopMeasure("StringBuilder Presized");
        PerformanceUtils.printTimingResultWithAverage("StringBuilder Presized", counter);
        return result;
    }

    private static String measureStringBuilderAppend(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("StringBuilder");
        for (int i = 0; i < counter; i++)
        {
            final StringBuilder sb = new StringBuilder();
            sb.append("Mein Hund ist ").append(i).append(" Jahre alt und heißt ").append(name).append(".");
            result = sb.toString();
        }
        PerformanceUtils.stopMeasure("StringBuilder");
        PerformanceUtils.printTimingResultWithAverage("StringBuilder", counter);
        return result;
    }

    private static String measureStringPlusEquals(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("String +=");
        for (int i = 0; i < counter; i++)
        {
            result = "Mein Hund ist ";
            result += i;
            result += " Jahre alt und heißt ";
            result += name;
            result += ".";
        }
        PerformanceUtils.stopMeasure("String +=");
        PerformanceUtils.printTimingResultWithAverage("String +=", counter);
        return result;
    }

    private static String measureStringPlus(final String name, final int counter, String result)
    {
        PerformanceUtils.startMeasure("String +");
        for (int i = 0; i < counter; i++)
        {
            result = "Mein Hund ist " + i + " Jahre alt und heißt " + name + ".";
        }
        PerformanceUtils.stopMeasure("String +");
        PerformanceUtils.printTimingResultWithAverage("String +", counter);
        return result;
    }

    private StringBenchmark()
    {
    }
}