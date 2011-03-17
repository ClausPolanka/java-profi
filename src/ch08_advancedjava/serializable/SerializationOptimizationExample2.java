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
public final class SerializationOptimizationExample2
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        final PersonWithEyeColorV2 original = new PersonWithEyeColorV2("Test", "TestCity", new Date(), Color.BLUE);

        FileOutputStream fileOutStream = null;
        ObjectOutputStream objectOutStream = null;

        try
        {
            fileOutStream = new FileOutputStream("TestWithEyeColor2.ser");
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

    private SerializationOptimizationExample2()
    {
    }
}