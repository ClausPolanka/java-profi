package ch06_applikationsbausteine;

import java.util.Arrays;
import java.util.List;

/**
 * Beispiel für eine ältere Utility-Klasse zur Konvertierung eines Byte-Arrays in eine
 * Liste von Strings
  * <br>
 * Diese Variante arbeitet mit String.split()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringToListUtils2
{
    public static List convertByteArrayToStringList(final byte[] inputBytes)
    {
        final String input = new String(inputBytes);

        return Arrays.asList(input.split("\n"));
    }

    private StringToListUtils2()
    {
    }
}