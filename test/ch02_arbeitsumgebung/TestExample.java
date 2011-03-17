package ch02_arbeitsumgebung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Besipiel für verschiedene assert()-Methoden
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class TestExample
{
    @Test
    public void testAssertTrue()
    {
        final List<String> names = new ArrayList<String>();
        names.add("Max");
        names.add("Moritz");
        names.clear();
        assertTrue(names.isEmpty());
    }
    
    @Test
    public void testAssertFalse()
    {
        final List<Integer> primes = Arrays.asList(2, 3, 5, 7);
		  // hier wird bewusst ein Fehler provoziert 
        assertFalse(primes.contains(7));
    }

    @Test
    public void testAssertNull()
    {
        assertNull(null);
    }
    
    @Test
    public void testAssertNotNull()
    {
        // hier wird bewusst ein Fehler provoziert 
        assertNotNull("Unexpected null value", null);
    }

    @Test
    public void testAssertEquals()
    {
        assertEquals("EXPECTED", "expected".toUpperCase());
    }

    @Test
    public void testFailWithExceptionJunit3()
    {
        try
        {
            // unerwarteten Fall simulieren
            // hier wird bewusst ein Fehler provoziert
            final int value = Integer.parseInt("Fehler simulieren!");
            fail("calculation should throw an exception!");
        }
        catch (final Exception ex)
        {
            // assertTrue ist eigentlich überflüssig
            // dient aber zur Verdeutlichung, dass dieser Fall erwartet wird 
            assertTrue(true);
        }
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testFailWithExceptionJUnit4()
    {
        // hier wird bewusst ein Fehler provoziert
        final int value = Integer.parseInt("Fehler simulieren!");
        fail("calculation should throw an exception!");
    }
}
