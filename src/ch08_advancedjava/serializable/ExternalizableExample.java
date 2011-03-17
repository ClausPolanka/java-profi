package ch08_advancedjava.serializable;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import ch06_applikationsbausteine.StreamUtils;

/**
 * Beispielklasse zur Demonstration der Serialisierung mithilfe des
 * Externalizable-Interfaces
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ExternalizableExample
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        final PersonExternalizable original = new PersonExternalizable("Test", "TestCity", new Date(), Color.GREEN);

        FileOutputStream fileOutStream = null;
        ObjectOutputStream objectOutStream = null;
        FileInputStream fileInStream = null;
        ObjectInputStream objectInStream = null;

        try
        {
            fileOutStream = new FileOutputStream("TestExternizable.ser");
            objectOutStream = new ObjectOutputStream(fileOutStream);

            // Schreibe Objekt mit writeExternal() in die Datei  
            original.writeExternal(objectOutStream);
            System.out.println("Wrote to stream: " + original);

            // wichtig, sonst liest InputStream evtl. noch unvollständige Daten   
            objectOutStream.flush();

            // Rücklesen des Objekts  
            fileInStream = new FileInputStream("TestExternizable.ser");
            objectInStream = new ObjectInputStream(fileInStream);

            // Konstruktoraufruf und Einsatz von readExternal()  
            final PersonExternalizable readInObject = new PersonExternalizable();
            readInObject.readExternal(objectInStream);
            System.out.println("Back from stream: " + readInObject);
        }
        finally
        {
            StreamUtils.safeClose(objectOutStream);
            StreamUtils.safeClose(fileOutStream);

            StreamUtils.safeClose(objectInStream);
            StreamUtils.safeClose(fileInStream);
        }
    }

    private ExternalizableExample()
    {
    }
}