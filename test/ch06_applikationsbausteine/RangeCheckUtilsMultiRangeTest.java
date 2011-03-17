package ch06_applikationsbausteine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Testklasse für die Klasse RangeCheckUtils, basierend auf JUnit 4
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RangeCheckUtilsMultiRangeTest
{
    @Test
    public void testValueInMultiRangeInteger()
    {
        final ValueRange<Long> validRange1 = new ValueRange<Long>(1L, 4L);
        final ValueRange<Long> validRange2 = new ValueRange<Long>(10L, 20L);

        final List<ValueRange<Long>> validRanges = new ArrayList<ValueRange<Long>>();

        validRanges.add(validRange1);
        validRanges.add(validRange2);

        assertFalse(RangeCheckUtils.isValueInMultiRange(5L, validRanges));
        assertTrue(RangeCheckUtils.isValueInMultiRange(18L, validRanges));
        assertTrue(RangeCheckUtils.isValueInMultiRange(20L, validRanges));
    }

    @Test
    public void testValueInMultiRangeDouble()
    {
        final ValueRange<Double> validRange1 = new ValueRange<Double>(1.0, 4.0);
        final ValueRange<Double> validRange2 = new ValueRange<Double>(10.10, 20.20);

        @SuppressWarnings("unchecked")
        final List<ValueRange<Double>> validRanges = Arrays.asList(validRange1, validRange2);

        assertFalse(RangeCheckUtils.isValueInMultiRange(5.0, validRanges));
        assertTrue(RangeCheckUtils.isValueInMultiRange(18.0, validRanges));
        assertTrue(RangeCheckUtils.isValueInMultiRange(20.0, validRanges));
    }
}