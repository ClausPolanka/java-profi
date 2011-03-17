package ch06_applikationsbausteine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Diverse kleine Hilfsmethoden für die Streamverarbeitung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class StreamUtils
{
    /** BUFFER SIZE FOR COPY */
    public static final int BUFFER_SIZE = 8192;

    /**
     * closes an inputstream and ignores IOException.
     * 
     * @param is    the input stream to be closed
     */
    public static void safeClose(final InputStream is)
    {
        try
        {
            if (is != null)
            {
                is.close();
            }
        }
        catch (final IOException e)
        {
            // ignore only io close exceptions
        }
    }

    /**
     * closes an outputstream and ignores IOException.
     * 
     * @param os    the output stream to be closed
     */
    public static void safeClose(final OutputStream os)
    {
        try
        {
            if (os != null)
            {
                os.close();
            }
        }
        catch (final IOException e)
        {
            // ignore only io close exceptions
        }
    }

    /**
     * closes the passed reader and ignores IOException.
     * 
     * @param reader    the reader to be closed
     */
    public static void safeClose(final Reader reader)
    {
        try
        {
            if (reader != null)
            {
                reader.close();
            }
        }
        catch (final IOException e)
        {
            // ignore
        }
    }

    /**
     * closes the passed writer and ignores IOException.
     * 
     * @param writer       the writer to be closed
     */
    public static void safeClose(final Writer writer)
    {
        try
        {
            if (writer != null)
            {
                writer.close();
            }
        }
        catch (final IOException e)
        {
            // ignore
        }
    }

    /**
     * copies all contents from the passed input stream into the given output
     * stream using byte wise copy
     * 
     * @param is    the source
     * @param os    the destination
     * 
     * @return true if the copy succeded
     * @throws IOException --- may occur when performing i/o
     */
    public static void copyBytewise(final InputStream is, final OutputStream os) throws IOException
    {
        int data = -1;
        while ((data = is.read()) != -1)
        {
            os.write(data);
        }
        os.flush();
    }

    /**
     * copies all contents from the passed input stream into the given output
     * stream using buffered streams
     */
    public static void copyBuffered(final InputStream is, final OutputStream os) throws IOException
    {
        final InputStream bufferedIn = decorateWithBuffer(is);
        final OutputStream bufferedOut = decorateWithBuffer(os);

        copyBytewise(bufferedIn, bufferedOut);
    }
    
    /**
     * copies all contents from the passed input stream into the given output
     * stream using byte buffer copy
     */
    public static void copyOwnBuffering(final InputStream is, final OutputStream os) throws IOException
    {
        final byte[] buffer = new byte[BUFFER_SIZE];
        int length = -1;
        while ((length = is.read(buffer, 0, BUFFER_SIZE)) != -1)
        {
            os.write(buffer, 0, length);
        }
        os.flush();
    }

    /**
     * copies all contents from the passed reader into the given writer using
     * char wise copy.
     */
    public static void copyCharWise(final Reader reader, final Writer writer) throws IOException
    {
        int data = -1;
        while ((data = reader.read()) != -1)
        {
            writer.write(data);
        }
        writer.flush();
    }

    /**
     * copies all contents from the passed reader into the given writer using
     * buffered Writer and Reader
     */
    public static void copyBuffered(final Reader reader, final Writer writer) throws IOException
    {
        final Reader bufferedIn = decorateWithBuffer(reader);
        final Writer bufferedOut = decorateWithBuffer(writer);

        copyCharWise(bufferedIn, bufferedOut);
    }
    
    /**
     * copies all contents from the passed reader into the given writer using char buffer copy
     */
    public static void copyOwnBuffering(final Reader reader, final Writer writer) throws IOException
    {
        final char[] buffer = new char[BUFFER_SIZE];
        int length = -1;
        while ((length = reader.read(buffer, 0, BUFFER_SIZE)) != -1)
        {
            writer.write(buffer, 0, length);
        }
        writer.flush();
    }

    /**
     * decorates a passed input stream "is" with a BufferedInputStream, if the
     * passed "is" is already a BufferedInputStream no additional
     * BufferedInputStream is created and originally passed "is" is returned.
     * 
     * @param is
     *            the InputStream to be decorated with a buffer
     * @return the decorated input stream
     */
    public static InputStream decorateWithBuffer(final InputStream inStream)
    {
        if (inStream == null)
            throw new IllegalArgumentException("parameter 'inStream' must not be null");

        if (!(inStream instanceof BufferedInputStream))
        {
            return new BufferedInputStream(inStream);
        }

        return inStream;
    }

    /**
     * decorates a passed output stream "os" with a BufferedOutputStream, if the
     * passed "os" is already a BufferedOutputStream no additional
     * BufferedOutputStream is created and the originally passed "os" is
     * returned.
     * 
     * @param os
     *            the OutputStream to be decorated with a buffer
     * @return the decorated output stream
     */
    public static OutputStream decorateWithBuffer(final OutputStream outStream)
    {
        if (outStream == null)
        {
            throw new IllegalArgumentException("parameter 'outStream' must not be null!");
        }

        return (outStream instanceof BufferedOutputStream) ? outStream : new BufferedOutputStream(outStream,
                                                                                                  BUFFER_SIZE);
    }

    /**
     * decorates a passed Reader "reader" with a BufferedReader, if the passed
     * "reader" is already a BufferedReader no additional BufferedReader is
     * created and originally passed "reader" is returned.
     * 
     * @param reader
     *            the Reader to be decorated with a buffer
     * @return the decoreted reader
     */
    public static Reader decorateWithBuffer(final Reader reader)
    {
        if (reader == null)
        {
            throw new IllegalArgumentException("parameter 'reader' must not be null!");
        }

        if (!(reader instanceof BufferedReader))
        {
            return new BufferedReader(reader);
        }

        return reader;
    }

    /**
     * decorates a passed Writer "writer" with a BufferedWriter, if the passed
     * "writer" is already a BufferedWriter no additional BufferedWriter is
     * created and the originally. passed "writer" is returned
     * 
     * @param writer
     *            the Writer to be decorated with a buffer
     * @return the decorated writer
     */
    public static Writer decorateWithBuffer(final Writer writer)
    {
        if (writer == null)
        {
            throw new IllegalArgumentException("parameter 'writer' must not be null!");
        }

        return (writer instanceof BufferedWriter) ? writer : new BufferedWriter(writer);
    }
}
