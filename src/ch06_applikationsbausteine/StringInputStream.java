package ch06_applikationsbausteine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Implementierung eines Eingabestremas, der auf einem String arbeitet
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringInputStream extends InputStream
{
    private InputStream stream;
    private boolean     closed = false;

    public StringInputStream(final String inputData)
    {
        stream = new ByteArrayInputStream(inputData.getBytes());
    }

    // ...
    @Override
    public synchronized int read() throws IOException
    {
        if (closed)
            throw new IOException("read forbidden -- stream already closed!");

        return stream.read();
    }

    @Override
    public synchronized int available() throws IOException
    {
        return stream.available();
    }

    @Override
    public synchronized void close() throws IOException
    {
        closed = true;
        stream = new ByteArrayInputStream(new byte[0]);
    }
}