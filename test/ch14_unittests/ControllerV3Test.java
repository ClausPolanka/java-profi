package ch14_unittests;

import ch14_unittests.ControllerV3;
import junit.framework.TestCase;

/**
 * Testklasse für die Klasse ControllerV3, basierend auf JUnit 3
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */ 
public final class ControllerV3Test extends TestCase
{
    public void testCalcState07()
    {
        assertEquals(7, ControllerV3.calcState((byte) '0', (byte) '7'));
    }

    public void testCalcState20()
    {
        assertEquals(2 * 16 + 0, ControllerV3.calcState((byte) '2', (byte) '0'));
    }

    public void testCalcState79()
    {
        assertEquals(7 * 16 + 9, ControllerV3.calcState((byte) '7', (byte) '9'));
    }

    public void testCalcStateRealHexValues3F()
    {
        assertEquals(0x3f, ControllerV3.calcState((byte) '3', (byte) 'F'));
    }

    public void testCalcStateRealHexValuesAE()
    {
        assertEquals(0xAE, ControllerV3.calcState((byte) 'A', (byte) 'E'));
    }
}