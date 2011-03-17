package ch05_collections;

import java.util.List;

/**
 * Beispielinterface zur Beschreibung von Read-only-Datenmodellen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public interface IDataAccessRO
{
    public int getElementCount();
    public ModelElement getElementAt(final int i);
    public List<ModelElement> getAllElements();
}