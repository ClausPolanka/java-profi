package ch08_advancedjava.cloneable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Utility-Klasse zum Klonen von Objekten mithilfe von Serialisierung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CopyObjectUtils
{
    @SuppressWarnings("unchecked")
    public static <T extends Object & Serializable> T copyObject(final T original)
    {
        try
        {
            // Objekt in Byte-Array schreiben 
            final ByteArrayOutputStream bout = new ByteArrayOutputStream();
            final ObjectOutputStream objectout = new ObjectOutputStream(bout);
            objectout.writeObject(original);

            // Objekt aus dem Byte-Array einlesen und konstrukieren  
            final byte[] objBytes = bout.toByteArray();
            final ByteArrayInputStream bin = new ByteArrayInputStream(objBytes);
            final ObjectInputStream objectin = new ObjectInputStream(bin);

            return (T) objectin.readObject();
        }
        catch (final IOException e)
        {
            // unmöglich, da wir ein Byte-Array zur Ein- und Ausgabe nutzen 
        }
        catch (final ClassNotFoundException e)
        {
            // unmöglich, da wir ein Objekt der Klasse gerade geschrieben haben  
        }

        // unmöglicher Fall, Anweisung nur zur Vermeidung von Kompilierfehlern 
        throw new IllegalStateException("copyObject() failed to copy object of type " + original.getClass());
    }

    private CopyObjectUtils()
    {
    }
}