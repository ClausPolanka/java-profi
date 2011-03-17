package ch14_unittests;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;

import java.util.List;

/**
 * Beispielprogramm zur Demonstration von Mocks
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class MockExample
{
    public static void main(String[] args)
    {
        // Mock erstellen und nutzen 
        final List<String> listMock = createMock(List.class);
        final Members clubMembers = new Members(listMock);

        // Aufzeichnung
        listMock.add("Person1");
        expectLastCall().andReturn(true);
        listMock.add("Person2");
        expectLastCall().andReturn(true);
        listMock.remove("Person1");
        expectLastCall().andReturn(true);

        // ...

        replay(listMock);

        clubMembers.registerMember("Person1");
        clubMembers.registerMember("Person2");
        // Simuliere hier bewusst einen Fehler!!! 
        clubMembers.deregisterMember("Person6");
    }

    private MockExample()
    {
    }
}
