package ch08_advancedjava.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;


/**
 * Die Klasse <code>AttributeAcccessExample</code> zeigt den Zugriff auf Attribute
 * mithilfe von Reflection.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AttributeAcccessExample
{
    public static long  instanceCounter = 0;
    @Deprecated
    public volatile int value;
    private String      description     = "Hello World";

    public static void main(String[] args)
    {
        try
        {
            final AttributeAcccessExample obj = new AttributeAcccessExample();
            final Class<?> clazz = obj.getClass();
            
            // $\mbox{\bfseries Zugriff auf das Attribut 'value' }$            
            final Field field = clazz.getField("value");
                       
            // $\mbox{\bfseries Zugriff auf den Wert des Attributs 'value' }$            
            final Object attributeValue = field.get(obj);
            System.out.println("value = " + attributeValue);
            
            // $\mbox{\bfseries Zugriff auf die Annotation des Attributs 'value' }$            
            System.out.println("Annotations: " + Arrays.asList(field.getAnnotations()));
            
            // $\mbox{\bfseries Zugriff auf das statische Attribut 'instanceCounter' }$            
            final Field staticfield = clazz.getField("instanceCounter");
            
            // $\mbox{\bfseries Zugriff auf den Wert des statischen Attributs 'instanceCounter' }$
            final Object staticvalue = staticfield.get(null);
            System.out.println("instanceCounter = " + staticvalue);

            // $\mbox{\bfseries Zugriff auf das private Attribut 'description' }$    
            final Field field2 = clazz.getDeclaredField("description");
            
            // $\mbox{\bfseries Zugriff ermöglichen }$
            field2.setAccessible(true);
            
            // $\mbox{\bfseries Zugriff auf den Wert des Attributs 'description'  }$
            final Object attributeValue2 = field2.get(obj);
            System.out.println("description = " + attributeValue2);
        }
        // $\mbox{\bfseries Behandlung sämtlicher durch Reflection möglicher Exceptions }$
        catch (final NoSuchFieldException e)
        {
            // $\mbox{\bfseries Es gibt kein solches Attribut }$
            throw new IllegalStateException("no such field!", e);
        }
        catch (final SecurityException e)
        {
            // $\mbox{\bfseries Keine Erlaubnis auf das Attribut zuzugreifen }$
            throw new IllegalStateException("insufficent security rights to access field!", e); 
        }
        catch (final IllegalAccessException e)
        {
            // $\mbox{\bfseries Kein Zugriff auf das Attribut }$
            throw new IllegalStateException("can't access field!", e);
        }  
   }
}