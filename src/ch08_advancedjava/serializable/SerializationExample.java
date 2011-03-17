package ch08_advancedjava.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import ch06_applikationsbausteine.StreamUtils;

/**
 * Beispielklasse zur Demonstration der Serialisierung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SerializationExample
{
    public static void main(String[] args) throws IOException
    {
        final Person person = new Person("Test", "TestCity", new Date());

        FileOutputStream fileOutStream = null;
        ObjectOutputStream objectOutStream = null;

        try
        {
            fileOutStream = new FileOutputStream("Test.ser");
            objectOutStream = new ObjectOutputStream(fileOutStream);

            // Schreibe Objekt in die Datei  
            objectOutStream.writeObject(person);
            System.out.println("Wrote to stream: " + person);
        }
        finally
        {
            StreamUtils.safeClose(objectOutStream);
            StreamUtils.safeClose(fileOutStream);
        }
    }

    private SerializationExample()
    {
    }
}