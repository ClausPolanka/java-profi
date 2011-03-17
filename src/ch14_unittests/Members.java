package ch14_unittests;

import java.util.ArrayList;
import java.util.List;

/**
 * Beispielklasse zur Demonstration von Mock-Objekten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Members
{
    private final List<String> members;

    Members(final List<String> persons)
    {
        this.members = persons;
    }

    public boolean registerMember(final String member)
    {
        return members.add(member);
    }

    public boolean deregisterMember(final String member)
    {
        return members.remove(member);
    }
    
    // ...
    
    public int getMemberCount()
    {
        return members.size();
    }
    
    public List<String> getMembersAsCopy()
    {
        return new ArrayList<String>(members);
    }
}
