package ch14_unittests;

import java.util.ArrayList;
import java.util.List;

import ch14_unittests.Members;


import junit.framework.TestCase;

/**
 * Testklasse für die Klasse Members, basierend auf JUnit 3
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */ 
public class MembersTest extends TestCase
{
    private Members members;
    
    public void setUp()
    {
        final List<String> persons = new ArrayList<String>();
        persons.add("Test-Person");
        
        members = new Members(persons);
    }
        
    public void testRegisterMember()
    {
        assertTrue(members.registerMember("New Registered"));
        assertEquals(2, members.getMemberCount());
        
        assertTrue(members.getMembersAsCopy().contains("Test-Person"));
        assertTrue(members.getMembersAsCopy().contains("New Registered"));
    }

    public void testDeregisterMember()
    {
        assertTrue(members.deregisterMember("Test-Person"));
        assertEquals(0, members.getMemberCount());
    }
    
    public void testDeregisterMemberInvalidMember()
    {
        assertFalse(members.deregisterMember("Unknown"));
        assertEquals(1, members.getMemberCount());
    }
}
