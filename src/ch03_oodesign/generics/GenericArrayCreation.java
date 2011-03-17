package ch03_oodesign.generics;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Beispielprogramm zur Demonstration der korrekten Objeterzeugung generischer Arrays
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class GenericArrayCreation
{
    @SuppressWarnings("unchecked")
    public static <T> T[] createTypedArray(final Class<T> clazz, final int size)
    {
        return (T[]) Array.newInstance(clazz, size);
    }

    public static void main(String[] args)
    {
        final Object[] texts = createTypedArray(String.class, 2);

        System.out.println("Type: " + texts.getClass().getSimpleName());
        System.out.println("Size: " + texts.length);        
        texts[0] = "Test1";
        texts[1] = "Test2";
        System.out.println("Content: " + Arrays.toString(texts));
    }
    
    private GenericArrayCreation()
    {        
    }
}
