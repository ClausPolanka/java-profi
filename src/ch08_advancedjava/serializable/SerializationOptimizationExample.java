package ch08_advancedjava.serializable;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import ch06_applikationsbausteine.StreamUtils;

/**
 * Beispielprogramm zur Demonstration von Optimierungsmöglichkeiten bei der
 * Serialisierung
 *  
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SerializationOptimizationExample
{
    public static void main(String[] args) throws IOException
    {
        final PersonWithEyeColorV1 original = new PersonWithEyeColorV1("Test", "TestCity", new Date(), Color.GREEN);

        FileOutputStream fileOutStream = null;
        ObjectOutputStream objectOutStream = null;

        try
        {
            fileOutStream = new FileOutputStream("TestWithEyeColor1.ser");
            objectOutStream = new ObjectOutputStream(fileOutStream);
            // Schreibe Objekt in die Datei  
            objectOutStream.writeObject(original);
            System.out.println("Wrote to stream: " + original);
        }
        finally
        {
            StreamUtils.safeClose(objectOutStream);
            StreamUtils.safeClose(fileOutStream);
        }
    }

    private SerializationOptimizationExample()
    {
    }
}