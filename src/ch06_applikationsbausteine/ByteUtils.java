package ch06_applikationsbausteine;

/**
 * Diverse kleine Hilfsmethoden für Byte-Arrays
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ByteUtils
{

    private ByteUtils()
    {
    }

    public static String byteArrayToString(final byte[] bytes)
    {
        if (bytes == null)
            return "";

        final StringBuilder sb = new StringBuilder();

        sb.append("'" + new String(bytes) + "' = ");
        
        sb.append("'[");
        for (int i = 0; i < bytes.length; i++)
        {
            sb.append(bytes[i]);
            sb.append(" ");
        }
        sb.append("]'"); 
        
        return sb.toString();
    }

    /**
     * extract the bytes form passed byte array to a new byte array beginning with the byte at 
     * position "from" last copied byte is the byte at position "to"-1 
     * <br>
     * NOT INCLUDING "to" !!!!!
     */
    public static byte[] extractByteArray(final byte[] original, final int from, final int to)
    {
        final byte[] newMsg = new byte[to - from];
        for (int i = 0; i < to - from; i++)
        {
            newMsg[i] = original[from + i];
        }

        return newMsg;
    }

    public static int find(final byte[] original, final byte value)
    {
        if (original == null)
            throw new IllegalArgumentException("passed byte array must not be null");

        for (int i = 0; i < original.length; i++)
        {
            if (original[i] == value)
                return i;
        }

        return -1;
    }

    public static int find(final byte[] original, final byte value, final int startPos)
    {
        if (original == null)
            throw new IllegalArgumentException("passed byte array must not be null");

        if (startPos >= original.length - 1)
            throw new IllegalArgumentException("start pos must not exceed length of input byte array");

        for (int i = startPos; i < original.length; i++)
        {
            if (original[i] == value)
                return i;
        }

        return -1;
    }

    public static int findLast(final byte[] original, final byte value)
    {
        if (original == null)
            throw new IllegalArgumentException("passed byte array must not be null");

        for (int i = original.length - 1; i >= 0; i--)
        {
            if (original[i] == value)
                return i;
        }

        return -1;
    }

    public static int findLast(final byte[] original, final byte value, final int startPos)
    {
        if (original == null)
            throw new IllegalArgumentException("passed byte array must not be null");

        if (startPos >= original.length - 1)
            throw new IllegalArgumentException("start pos must not exceed length of input byte array");

        for (int i = startPos; i >= 0; i--)
        {
            if (original[i] == value)
                return i;
        }

        return -1;
    }
}