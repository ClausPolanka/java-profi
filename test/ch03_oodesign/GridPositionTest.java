package ch03_oodesign;

import junit.framework.TestCase;

/**
 * Testklasse für die Klasse GridPosition, basierend auf JUnit 3
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class GridPositionTest extends TestCase
{
    private GridPosition examplePosition = new GridPosition();
    
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testAddOffset()
    {
        examplePosition.addOffset(15, 23);
        assertEquals(10,examplePosition.getX());
        assertEquals(20,examplePosition.getY());
    }

    public void testSetSamePos1()
    {
        examplePosition.setSamePos(37);
        assertEquals(30,examplePosition.getX());
        assertEquals(30,examplePosition.getY());
    }

    public void testSetSamePos2()
    {        
        examplePosition.setSamePos(37);
        examplePosition.addOffset(15, 23);
        assertEquals(40,examplePosition.getX());
        assertEquals(50,examplePosition.getY());
    }
}
