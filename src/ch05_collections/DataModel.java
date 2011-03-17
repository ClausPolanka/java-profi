package ch05_collections;

import java.util.LinkedList;
import java.util.List;

/**
 * Beispielklasse zur Beschreibung von Read-only-Datenmodellen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DataModel implements IDataAccessRO
{
    private final List<ModelElement> modelElements = new LinkedList<ModelElement>();

    public DataModel()
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
        return modelElements;
    }

    public static void main(String[] args)
    {
        final IDataAccessRO dataModel = new DataModel();
        final List<ModelElement> elements = dataModel.getAllElements();

        // ups: new element "Unexpected"
        elements.add(new ModelElement("Unexpected"));
        for (ModelElement element : elements)
        {
            System.out.println(element);
        }
    }
}