package ch06_applikationsbausteine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Implementierung eines Ausgabestremas, der auf einem String arbeitet
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class StringOutputStream extends OutputStream
{
    private OutputStream stream = new ByteArrayOutputStream();
    private boolean      closed = false;

    // ByteArrayOutputStream wirft keine Exception
    public synchronized void write(int b) throws IOException
    {
        if (closed)
            throw new IOException("write forbidden -- stream already closed!");

        stream.write(b);
    }

    public synchronized String getContentAsString()
    {
        return stream.toString();
    }

    @Override
    public String toString()
    {
        return this.getClass() + " / Content: '" + getContentAsString() + "'";
    }

    @Override
    public synchronized void close() throws IOException
    {
        closed = true;
        reset();
    }

    public synchronized void reset()
    {
        // trick: this frees larger allocated byte array blocks ...
        stream = new ByteArrayOutputStream();
    }
}