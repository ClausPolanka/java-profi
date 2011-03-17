package ch06_applikationsbausteine;

import java.util.ArrayList;
import java.util.List;

/**
 * Beispiel für eine ältere Utility-Klasse zur Konvertierung eines Byte-Arrays in eine
 * Liste von Strings
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringToListUtils
{
    public static List convertByteArrayToStringList(final byte[] inputBytes)
    {
        final List lines = new ArrayList();

        final String inputString = new String(inputBytes);
        int start = 0;
        int end = 0;

        while (end > -1)
        {
            end = inputString.indexOf('\n', start);

            if (end > start && start < inputString.length())
            {
                final char[] extractedChars = new char[end - start];
                inputString.getChars(start, end, extractedChars, 0);

                final String value = new String(extractedChars);
                lines.add(value);
                start = end + 1;
            }
        }

        return lines;
    }

    private StringToListUtils()
    {
    }
}