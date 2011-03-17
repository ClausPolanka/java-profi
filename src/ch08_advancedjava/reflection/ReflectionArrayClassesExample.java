package ch08_advancedjava.reflection;

/**
 * Die Klasse <code>ReflectionArrayClassesExample</code> zeigt die merkwürdige
 * Angabe von Klassennamen in Class.forName().
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ReflectionArrayClassesExample
{
    public static void main(final String[] args) throws Exception
    {
        // Zugriff auf den Typ ,,zweidimensionales int-Array'': [[I  
        final Class<?> intintArrayClazz = Class.forName("[[I");
        System.out.println(int[][].class);
        System.out.println(intintArrayClazz == int[][].class);

        // Zugriff auf den Typ ,,eindimensionales String-Array'': [Ljava.lang.String; 
        final Class<?> stringArrayClazz = Class.forName("[Ljava.lang.String;");
        System.out.println(String[].class);
        System.out.println(stringArrayClazz == String[].class);
    }

    private ReflectionArrayClassesExample()
    {
    }
}
