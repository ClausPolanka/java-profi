package ch05_collections;

/**
 * Beispielklasse zur Beschreibung von Read-only-Datenmodellen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ModelElement implements IModelElementRO
{
    private String name;

    public ModelElement(final String name)
    {
        this.name = name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return "ModelElement name = '" + name + "'";
    }
}