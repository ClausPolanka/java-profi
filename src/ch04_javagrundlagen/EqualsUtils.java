package ch04_javagrundlagen;

/**
 * Utility-Class for easy writing of equals
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class EqualsUtils
{
    private EqualsUtils()
    {
    }

    public static boolean nullSafeEquals(final Object obj1, final Object obj2)
    {
        return (obj1 == obj2) || (obj1 != null && obj1.equals(obj2));
    }

    public static boolean isEqualWithinPrecision(final double value, final double expected, final double epsilon)
    {
        return (value > expected - epsilon && value < expected + epsilon);
    }
}