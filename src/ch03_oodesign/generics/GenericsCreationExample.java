package ch03_oodesign.generics;

import java.util.Date;

/**
 * Beispielprogramm zur Demonstration der generische Objekterzeugung 
 * mithilfe einer Erzeugungsmethode bzw. einer Fabrik
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class GenericsCreationExample
{
    public static void main(final String[] args)
    {
        // Variante 1 - Konstruktion 
        final Person newPerson1 = createNewTypedObject(Person.class);

        // Variante 2 - Konstruktion 
        final AbstractFactory<Person> factory = new PersonFactory();
        final Person newPerson2 = factory.createNewTypedObject();

        // Initialisierung für beide Varianten      
        initPersonAttributes(newPerson1, "Mike1", "Aachen", new Date());
        System.out.println("Person1: " + newPerson1);

        initPersonAttributes(newPerson2, "Mike2", "Bremen", new Date());
        System.out.println("Person2: " + newPerson2);

    }

    private static void initPersonAttributes(final Person newPerson, final String name, final String city,
                                             final Date birthday)
    {
        newPerson.setName(name);
        newPerson.setCity(city);
        newPerson.setBirthday(birthday);
    }

    @SuppressWarnings("unchecked")
    public static <T> T createNewTypedObject(final Class<?> clazz)
    {
        try
        {
            return (T) clazz.newInstance();
        }
        catch (final InstantiationException e)
        {
            // Keine Instanziierung möglich 
        }
        catch (final IllegalAccessException e)
        {
            // Kein Zugriff möglich 
        }

        return null;
    }

    private GenericsCreationExample()
    {
    }
}
