package ch14_unittests.cobertura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch14_unittests.cobertura.Coverage;

/**
 * Testklasse, deren Tests 100% Abdeckung erreichen, es aber trotzdem zu einer 
 * NullPointerException kommt
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */ 
public class CoverageTest
{
    final Coverage coverage = new Coverage(4711, "4711");

    @Test
    public void testDivide()
    {
        coverage.divide(4711);
        assertEquals(coverage.value, 1);
    }

    @Test
    public void testDivideByZeroJUnit3()
    {
        try
        {
            coverage.divide(0);
            fail();
        }
        catch (final ArithmeticException ex)
        {
            assertTrue(true);
        }
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroJUnit4()
    {
        coverage.divide(0);
    }

    @Test
    public void testCoverage100ButNPE1()
    {
        coverage.coverage100ButNPE(true);
    }

    @Test
    public void testCoverage100ButNPE2()
    {
        coverage.coverage100ButNPE(false);
    }
}
