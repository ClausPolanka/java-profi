package ch03_oodesign.generics;

/**
 * Beispielprogramm zur Demonstration der Probleme bei der Objekterzeugung generischer Arrays
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class WrongGenericArrayCreation<T>
{
    public static <T> T[] createTypedArray(final int size)
    {
        final T[] typedArray = (T[]) new Object[size];
        return typedArray;
    }

    public static void main(String[] args)
    {
        // java.lang.ClassCastException: 
        // [Ljava.lang.Object; cannot be cast to [Ljava.lang.String; 
        final String[] texts = createTypedArray(10);
        texts[0] = "Test";        
    }
    
    private WrongGenericArrayCreation()
    {        
    }
}
