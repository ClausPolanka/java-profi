package ch05_collections.filtering;

/**
 * Basisinterface aller Klassen zur Definition von Filterkriterien
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public interface IFilter<T>
{
    boolean accept(final T object);
}