package ch08_advancedjava.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Die Klasse <code>InspectionExample</code> zeigt die Möglichkeiten von Reflection
 * zur Inspektion von Klassen, hier der Klasse String.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class InspectionExample
{
    public static void main(String[] args)
    {
        inspectClass(String.class);
    }

    private static void inspectClass(final Class<?> clazz)
    {
        System.out.println("Untersuchte Klasse: " + clazz.getCanonicalName());
        System.out.println();
        System.out.println("Superklasse: " + clazz.getSuperclass());
        System.out.println("Interfaces: " + Arrays.toString(clazz.getInterfaces()));

        // Zugriff und Ausgabe der Konstruktoren        
        final Constructor<?>[] ctors = clazz.getDeclaredConstructors();
        System.out.println("\nKonstruktoren: ");
        for (final Constructor<?> ctor : ctors)
        {
            ReflectionUtils.printCtorInfos(ctor);
        }

        // Zugriff und Ausgabe der Attribute  
        final Field[] fields = clazz.getDeclaredFields();
        System.out.println("\nAttribute: ");
        for (final Field field : fields)
        {
            ReflectionUtils.printFieldInfos(field);
        }

        // Zugriff und Ausgabe aller Methoden  
        final Method methods[] = ReflectionUtils.getAllMethods(clazz);
        System.out.println("\nAlle Methoden: ");
        for (final Method method : methods)
        {
            ReflectionUtils.printMethodInfos(method);
        }
    }
}
