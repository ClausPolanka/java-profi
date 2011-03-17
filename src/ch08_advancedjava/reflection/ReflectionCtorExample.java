package ch08_advancedjava.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public final class ReflectionCtorExample
{
public static void main(final String[] args)
{
    try
    {
        final Class<?> stringClazz = Class.forName("java.lang.String");

        // $\mbox{\bfseries Aufruf des Defaultkonstruktors }$
        final String stringInstance1 = (String) stringClazz.newInstance();

        // $\mbox{\bfseries Suche den Konstruktor String(char[], int, int) }$
        final Class<?>[] parameterTypes = new Class<?>[] { char[].class, int.class, int.class };
        final Constructor<?> ctor = stringClazz.getDeclaredConstructor(parameterTypes);

        // $\mbox{\bfseries Aufruf des Konstruktors String(char[], int, int) }$
        final String stringInstance2 = (String) ctor.newInstance(new char[] { 'a', ' ', 'T', 'e', 's', 't' }, 2, 4);

        System.out.println("String 1 = '" + stringInstance1 + "'");
        System.out.println("String 2 = '" + stringInstance2 + "'");
    }
    // $\mbox{\bfseries Behandlung sämtlicher durch Reflection möglicher Exceptions }$
    // ...
    catch (final NoSuchMethodException e)
    {
        // $\mbox{\bfseries Es gibt keinen solchen Konstruktor }$
        throw new IllegalStateException("does not support constructor", e);
    }
    catch (final SecurityException e)
    {
        // $\mbox{\bfseries Keine Erlaubnis auf den Konstruktor zuzugreifen }$
        throw new IllegalStateException("insufficent security rights to access constructor", e);
    }
    catch (final IllegalArgumentException e)
    {
        // $\mbox{\bfseries Ungültige Parameter beim Aufruf }$
        throw new IllegalStateException("illegal parameters passed", e);
    }
    catch (final IllegalAccessException e)
    {
        // $\mbox{\bfseries Kein Zugriff auf den Konstruktor }$
        throw new IllegalStateException("can't access constructor", e);
    }
    catch (final InvocationTargetException e)
    {
        // $\mbox{\bfseries Konstruktor wirft Exception }$
        throw new IllegalStateException("exception in constructor", e);
    }
    catch (final InstantiationException e)
    {
        // $\mbox{\bfseries Kann keine Instanz erzeugen }$
        throw new IllegalStateException("can't create instance", e);
    }
    catch (final ClassNotFoundException e)
    {
        // $\mbox{\bfseries Klassenbeschreibung nicht gefunden }$
        throw new IllegalStateException("class not found", e);
    }
}

    private ReflectionCtorExample()
    {
    }
}
