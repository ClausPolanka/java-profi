package ch05_collections;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Beispielklasse zur Beschreibung von Read-only-Datenmodellen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DataModelImproved implements IDataAccessRO
{
    private final List<ModelElement> modelElements = new LinkedList<ModelElement>();

    public DataModelImproved()
    {
    }

    public int getElementCount()
    {
        return modelElements.size();
    }

    public ModelElement getElementAt(final int i)
    {
        return modelElements.get(i);
    }

    public List<ModelElement> getAllElements()
    {
        return Collections.unmodifiableList(modelElements);
    }

    public List<IModelElementRO> getUnmodifiableCopyOfAllElements()
    {
        final List<IModelElementRO> elementsAsRO = new LinkedList<IModelElementRO>(modelElements);
        return elementsAsRO;
    }
}