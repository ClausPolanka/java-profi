package ch03_oodesign.generics;

import java.util.Date;

/**
 * Hilfsklasse zur Demonstration von verschiedenen Aspekten bei Generics
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class PersonFactory extends AbstractFactory<Person> 
{
	public Person createNewTypedObject()
	{
		return new Person("", new Date(), "");
	}
}