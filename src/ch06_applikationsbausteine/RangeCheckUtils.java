package ch06_applikationsbausteine;

import java.util.List;

/**
 * Hilfsklasse für Wertebereichsprüfungen 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class RangeCheckUtils
{
    // ##################### Einfache Wertebereiche #################################    

    // Basismethode 
    public static boolean isValueInRange(final long value, final long minValue, final long maxValue)
    {
        return (value >= minValue && value <= maxValue);
    }

    // Spezialbehandlung für Floating-Point-Werte (Rundung) 
    public static boolean isValueInRange(final double value, final double minValue, final double maxValue)
    {
        return (value >= minValue && value <= maxValue);
    }

    // Typsichere Definition 
    public static <T> boolean isValueInRange(final Comparable<T> value, final T minValue, final T maxValue)
    {
        return (value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0);
    }

    // ##################### Einfache Wertemengen #################################

    public static boolean isValueValid(final long value, final long[] validValues)
    {
        for (final long currentValue : validValues)
        {
            if (value == currentValue)
                return true;
        }

        return false;
    }

    public static boolean isValueValid(final double value, final double[] validValues)
    {
        for (final double currentValue : validValues)
        {
            if (value == currentValue)
                return true;
        }

        return false;
    }

    public static <T> boolean isValueValid(final Comparable<T> value, final T[] validValues)
    {
        for (final T currentValue : validValues)
        {
            if (value.compareTo(currentValue) == 0)
                return true;
        }

        return false;
    }

    // ################# Einfache und mehrfache Wertebereiche ###########################

    public static <T extends Number & Comparable<T>> boolean isValueInRange(final T value,
                                                                            final ValueRange<T> valueRange)
    {
        return valueRange.contains(value);
    }

    public static <T extends Number & Comparable<T>> boolean isValueInMultiRange(final T value,
                                                                                 final List<ValueRange<T>> valueRanges)
    {
        for (final ValueRange<T> currentValueRange : valueRanges)
        {
            if (currentValueRange.contains(value))
                return true;
        }

        return false;
    }

    
    

    // Fehlermeldungen für Prüfungen primitiver Wertebereiche  
    public static String createErrorMessage(final long value, final long from, final long to)
    {
        final ValueRange<Long> valueRange = new ValueRange<Long>(from, to);
        return createErrorMessage(Long.valueOf(value), valueRange);
    }

    public static String createErrorMessage(final double value, final double from, final double to)
    {
        final ValueRange<Double> valueRange = new ValueRange<Double>(from, to);
        return createErrorMessage(Double.valueOf(value), valueRange);
    }

    public static <T extends Number & Comparable<T>> String createErrorMessage(final T value,
                                                                               final ValueRange<T> valueRange)
    {
        return valueRange.createErrorMessage(value);
    }

    // Methoden für immer wiederkehrende Null-Prüfungen 
    public static void assertStringParamNotNullOrEmpty(final String paramName, final String value)
    {
        if (value == null || value.length() == 0)
            throw new IllegalArgumentException("parameter '" + paramName + "' " + "must not be empty or null");
    }

    public static void assertArrayParamNotNullOrEmpty(final String paramName, final Object[] values)
    {
        if (values == null || values.length == 0)
            throw new IllegalArgumentException("parameter '" + paramName + "' " + "must not be empty or null");
    }

    public static void assertReferenceParamNotNull(final String paramName, final Object value)
    {
        if (value == null)
            throw new IllegalArgumentException("parameter '" + paramName + "' " + "must not be null");
    }
}