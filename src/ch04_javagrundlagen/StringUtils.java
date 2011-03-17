package ch04_javagrundlagen;

/**
 * Utilityklasse mit einfachen String-Hilfsmethoden
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringUtils
{
    private StringUtils()
    {
    }

    public static boolean isNullOrEmpty(final String text)
    {
        return text == null || text.isEmpty();
    }


    public static void printTokens(final String[] tokens)
    {
        System.out.print("Tokens = '[");
        for (int i = 0; i < tokens.length; i++)
        {
            System.out.print("'" + tokens[i] + "'");
            
            if (i != tokens.length - 1)
                System.out.print(", ");
        }
        System.out.println("]'");
    }
    
    public static String truncateTextToMaxLength(final String text, final int maxLength)
    {
        if (text.length() < maxLength)
        {
            return text;
        }

        // Hänge "..." an den Text an, wenn er zu lang ist
        final String ELLIPSIS = "...";
        final int ELLIPSIS_COUNT = ELLIPSIS.length();

        final String truncated = text.substring(0, maxLength - ELLIPSIS_COUNT);

        return truncated + ELLIPSIS;
    }

    public static String appendEndingIfNecessary(final String name, final String ending)
    {
        if ( name == null || ending == null )
            throw new IllegalArgumentException("Parameters 'name' and 'ending' must not be null!");
        
        if (name.toLowerCase().endsWith(ending.toLowerCase()))
        {
            return name;
        }

        return name + ending;
    }

    public static String removeEndingIfExisting(final String name, final String ending)
    {
        if ( name == null || ending == null )
            throw new IllegalArgumentException("Parameters 'name' and 'ending' must not be null!");

        if (name.toLowerCase().endsWith(ending.toLowerCase()))
        {
            return name.substring(0, name.length() - ending.length());
        }

        return name;
    }
}