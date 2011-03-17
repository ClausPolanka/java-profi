package ch14_unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch14_unittests.Controller;

/**
 * Testklasse für die Klasse Controller, basierend auf JUnit 4
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */ 
public class ControllerTest
{
    @Test
    public void testCalcState07()
    {
        assertEquals(7, Controller.calcState((byte) '0', (byte) '7'));
    }

    @Test
    public void testCalcState20()
    {
        assertEquals(2 * 16 + 0, Controller.calcState((byte) '2', (byte) '0'));
    }

    @Test
    public void testCalcState79()
    {
        assertEquals(7 * 16 + 9, Controller.calcState((byte) '7', (byte) '9'));
    }

    @Test
    public void testCalcStateHexValues3F()
    {
        assertEquals(0x3f, Controller.calcState((byte) '3', (byte) 'F'));
    }

    @Test
    public void testCalcStateHexValuesAE()
    {
        assertEquals(0xAE, Controller.calcState((byte) 'A', (byte) 'E'));
    }

    @Test
    public void testValidHexCodedByteValuesAsNumbersAndChars()
    {
        assertEquals(0, Controller.hexCodedByteValueToInt((byte) '0'));
        assertEquals(9, Controller.hexCodedByteValueToInt((byte) '9'));
        assertEquals(10, Controller.hexCodedByteValueToInt((byte) 'A'));
        assertEquals(15, Controller.hexCodedByteValueToInt((byte) 'F'));
    }

    @Test
    public void testValidHexCodedByteValuesAsNumbersAndCharsImproved()
    {
        final byte[] inputs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final int[] expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

        for (int i = 0; i < inputs.length && i < expected.length; i++)
            assertEquals(expected[i], Controller.hexCodedByteValueToInt(inputs[i]));
    }

    @Test
    public void testInvalidHexCodedByteValues()
    {
        checkInvalidValue((byte) 0x29);
        checkInvalidValue((byte) 0x3a);
        checkInvalidValue((byte) 0x40);
        checkInvalidValue((byte) 0x47);
    }

    private void checkInvalidValue(final byte value)
    {
        try
        {
            Controller.hexCodedByteValueToInt(value);
            fail(Integer.toHexString(value) + " shouln't be valid");
        }
        catch (final IllegalArgumentException ex)
        {
            assertTrue(true);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHexCodedByteValuesExpectedException()
    {
        Controller.hexCodedByteValueToInt((byte) 0x29);
    }

    public void testCalcStateHexValues_ac()
    {
        final byte highByte = 'a';
        final byte lowByte = 'c';
        final int value = Controller.calcState(highByte, lowByte);

        assertEquals(0xAC, value);
    }
}
