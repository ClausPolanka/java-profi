package ch08_advancedjava.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Die Klasse <code>ReflectionUtils</code> ist eine Utility-Klasse, die verschiedene
 * Hilfsmethoden zur vereinfachten Handhabung von Reflection bereitstellt.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ReflectionUtils
{
    public static String modifierToString(final int modifier)
    {
        String modifiers = "";

        if (Modifier.isPublic(modifier))
            modifiers += "public ";
        if (Modifier.isProtected(modifier))
            modifiers += "protected ";
        if (Modifier.isPrivate(modifier))
            modifiers += "private ";

        if (Modifier.isStatic(modifier))
            modifiers += "static ";
        if (Modifier.isAbstract(modifier))
            modifiers += "abstract ";
        if (Modifier.isFinal(modifier))
            modifiers += "final ";

        if (Modifier.isVolatile(modifier))
            modifiers += "volatile ";
        if (Modifier.isSynchronized(modifier))
            modifiers += "synchronized ";

        return modifiers;
    }

    public static Field findField(final Class<?> clazz, final String fieldName)
    {
        // Abbruch der Rekursion
        if (clazz == null)
            return null;

        try
        {
            return clazz.getDeclaredField(fieldName);
        }
        catch (final NoSuchFieldException ex)
        {
            // rekursive Suche in Superklasse
            return findField(clazz.getSuperclass(), fieldName);
        }
    }

    public static Method findMethod(final Class<?> clazz, final String methodName, final Class<?>... parameterTypes)
    {
        // Abbruch der Rekursion
        if (clazz == null)
            return null;

        try
        {
            return clazz.getDeclaredMethod(methodName, parameterTypes);
        }
        catch (final NoSuchMethodException ex)
        {
            // rekursive Suche in Superklasse
            return findMethod(clazz.getSuperclass(), methodName, parameterTypes);
        }
    }

    public static Method[] getAllMethods(final Class<?> clazz)
    {
        final List<Method> methods = new ArrayList<Method>();
        methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));

        if (clazz.getSuperclass() != null)
        {
            // rekursive Suche in Superklasse
            methods.addAll(Arrays.asList(getAllMethods(clazz.getSuperclass())));
        }

        return methods.toArray(new Method[0]);
    }

    public static void printCtorInfos(final Constructor<?> ctor)
    {
        System.out.println(modifierToString(ctor.getModifiers()) + " " + ctor.getName()
                           + buildParameterTypeString(ctor.getParameterTypes()));
        printAnnotations(ctor.getAnnotations());
    }

    public static void printMethodInfos(final Method method)
    {
        System.out.println(modifierToString(method.getModifiers()) + method.getReturnType() + " " + method.getName()
                           + buildParameterTypeString(method.getParameterTypes()));
        printAnnotations(method.getAnnotations());
    }

    public static void printFieldInfos(final Field field)
    {
        System.out.println(ReflectionUtils.modifierToString(field.getModifiers()) + field.getType() + " "
                           + field.getName());
        printAnnotations(field.getAnnotations());
    }

    public static String buildParameterTypeString(final Class<?>[] parameterTypes)
    {
        if (parameterTypes.length > 0)
            return "(" + Arrays.toString(parameterTypes) + ")";

        return "()";
    }

    private static void printAnnotations(final Annotation[] annotations)
    {
        if (annotations.length > 0)
            System.out.println("Annotations: " + Arrays.toString(annotations));
    }

    private ReflectionUtils()
    {
    }
}
