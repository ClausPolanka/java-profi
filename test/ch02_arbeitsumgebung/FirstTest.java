package ch02_arbeitsumgebung;

import junit.framework.TestCase;

/**
 * Besipiel eines einfachen Unit Tests mit JUnit 3
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class FirstTest extends TestCase 
{
	public void testStringTrimEquals() 
	{
		final String valueWithBlanks = "   Writing Unit Tests is easy!   ";
		final String expectedTrimmed = "Writing Unit Tests is easy!";
		
		assertEquals(expectedTrimmed, valueWithBlanks.trim()); 
	}
}