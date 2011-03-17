package ch10_badsmells;

/**
 * Beispiel für die unbedachte Rückgabe von null
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DontReturnNullExample
{
    public static void main(final String[] args)
    {
        final String value1 = toHashSeparatedString("012");
        final String value2 = toHashSeparatedString("");

        System.out.println("Encoded='" + value1 + "' / length=" + value1.length());
        System.out.println("Encoded='" + value2 + "' / length=" + value2.length());
    }

    public static String toHashSeparatedString(final String message)
    {
        if (message.length() == 0)
            return null;

        final StringBuffer sb = new StringBuffer("#");
        for (int i = 0; i < message.length(); i++)
        {
            sb.append(message.charAt(i)).append('#');
        }
        return sb.toString();
    }

    private DontReturnNullExample()
    {
    }
}