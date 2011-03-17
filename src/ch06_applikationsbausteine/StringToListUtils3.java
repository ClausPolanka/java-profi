package ch06_applikationsbausteine;

import java.util.Arrays;
import java.util.List;

/**
 * Beispiel f�r eine �ltere Utility-Klasse zur Konvertierung eines Byte-Arrays in eine
 * Liste von Strings
 * <br>
 * Diese Variante arbeitet mit String.split() und gibt eine typsichere Liste zur�ck
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringToListUtils3
{
    public static List<String> convertByteArrayToStringList(final byte[] inputBytes)
    {
        final String input = new String(inputBytes);

        return Arrays.asList(input.split("\n"));
    }

    private StringToListUtils3()
    {
    }
}