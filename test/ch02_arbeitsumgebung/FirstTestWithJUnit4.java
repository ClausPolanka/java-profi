package ch02_arbeitsumgebung;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Besipiel eines einfachen Unit Tests mit JUnit 4
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class FirstTestWithJUnit4 
{
	@Test 
	public void stringEquals() 
	{
		final String valueWithBlanks = "   Writing Unit Tests is easy!   ";
		final String expectedTrimmed = "Writing Unit Tests is easy!";
		
		assertEquals(expectedTrimmed, valueWithBlanks.trim());
	}
}