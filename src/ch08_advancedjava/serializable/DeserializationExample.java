package ch08_advancedjava.serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import ch06_applikationsbausteine.StreamUtils;

/**
 * Beispielklasse zur Demonstration des Deserialisierungsvorgangs
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DeserializationExample
{
    public static void main(String[] args) throws Exception
    {
        FileInputStream fileInStream = null;
        ObjectInputStream objectInStream = null;

        try
        {
            fileInStream = new FileInputStream("Test.ser");
            objectInStream = new ObjectInputStream(fileInStream);

            // Rücklesen des Objekts, ohne Konstruktoraufruf 
            final Person personFromStream = (Person) objectInStream.readObject();
            System.out.println("Back from stream: " + personFromStream);
        }
        finally
        {
            StreamUtils.safeClose(objectInStream);
            StreamUtils.safeClose(fileInStream);
        }
    }

    private DeserializationExample()
    {
    }

}