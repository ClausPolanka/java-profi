package ch06_applikationsbausteine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Testklasse für die Klasse RangeCheckUtils, basierend auf JUnit 4
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RangeCheckUtilsValueRangeTest
{
    @Test
    public void testValueInRangeIntegerUsingValueRange()
    {
        final ValueRange<Long> validRange = new ValueRange<Long>(1L, 4L);

        assertTrue(RangeCheckUtils.isValueInRange(1L, validRange));
        assertFalse(RangeCheckUtils.isValueInRange(5L, validRange));
    }

    @Test
    public void testValueInRangeDoubleUsingValueRange()
    {
        final ValueRange<Double> validRange = new ValueRange<Double>(0.9, 3.1);

        assertTrue(RangeCheckUtils.isValueInRange(1.0, validRange));
        assertFalse(RangeCheckUtils.isValueInRange(3.1415, validRange));
    }
}