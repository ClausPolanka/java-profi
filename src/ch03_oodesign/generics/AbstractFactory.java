package ch03_oodesign.generics;

/**
 * Hilfsklasse zur Demonstration von verschiedenen Aspekten bei Generics
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public abstract class AbstractFactory<T>
{
    abstract T createNewTypedObject();
}
