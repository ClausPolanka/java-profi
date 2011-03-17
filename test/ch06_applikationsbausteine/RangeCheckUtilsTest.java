package ch06_applikationsbausteine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ch06_applikationsbausteine.RangeCheckUtils.*;

import org.junit.Test;

/**
 * Testklasse für die Klasse RangeCheckUtils, basierend auf JUnit 4
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class RangeCheckUtilsTest
{
    final long[]   PRIM_LONG    = { 2, 3, 5, 7, 9 };
    final double[] PRIM_DOUBLE  = { 2.0, 3.0, 5.0, 7.0, 9.0 };
    final String[] JAHRESZEITEN = { "Frï¿½hling", "Sommer", "Herbst", "Winter" };
    
    @Test 
    public void testValueInRange()
    {
        assertTrue("7 in [2,14]", isValueInRange(7, 2, 14));
        assertFalse("1 nicht in [2,3]", isValueInRange(1, 2, 3));
    }

    @Test 
    public void testValueInRangeDouble()
    {
        assertTrue("7.2 in [7.1,7.3]", isValueInRange(7.2, 7.1, 7.3));
        assertFalse("1.0 nicht in [1.1, 1.2]", isValueInRange(1.0, 1.1, 1.2));
    }

    @Test 
    public void testValueValid()
    {
        assertTrue("5 ist Primzahl", isValueValid(5, PRIM_LONG));
        assertFalse("8 keine Primzahl", isValueValid(8, PRIM_LONG));
    }

    @Test 
    public void testValueValidDouble()
    {
        assertTrue("5.0 ist Primzahl", isValueValid(5.0, PRIM_DOUBLE));
        assertFalse("8.0 keine Primzahl", isValueValid(8.0, PRIM_DOUBLE));
    }

    @Test 
    public void testValueInRangeComparable()
    {
        assertTrue(isValueInRange("Test", "Tee", "Test ok"));
        assertFalse(isValueInRange("Test", "Test falsch", "Test ok"));
    }

    @Test 
    public void testValueValidComparable()
    {
        assertTrue("GÃ¼ltige Jahreszeit", isValueValid("Sommer", JAHRESZEITEN));
        assertFalse("Keine Jahreszeit", isValueValid("Test", JAHRESZEITEN));
    }
}