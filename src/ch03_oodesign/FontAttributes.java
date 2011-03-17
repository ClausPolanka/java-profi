package ch03_oodesign;

/**
 * Beispiel einer einfachen Aufz�hlung mit int-Konstanten und deren Kombination
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FontAttributes
{
    public static final int NORMAL    = 0;
    public static final int BOLD      = 1;
    public static final int ITALIC    = 2;
    public static final int UNDERLINE = 4;

    private FontAttributes()
    {
        // avoid construction of this class
    }

    public static void main(final String args[])
    {
        final int fontStyles = BOLD | ITALIC;

        // Variante 1: Pr�fe Attribut und zeige Implementierungsdetails 
        final boolean isUnderline = (fontStyles & UNDERLINE) == UNDERLINE; // V1

        // Variante 2+3: Pr�fe Attribute, Abstraktion von Implementierungsdetails 
        final boolean isBold = isBold(fontStyles); // V2               
        final boolean isItalic = isAttributeEnabled(fontStyles, ITALIC); // V3 

        System.out.println("isUnderline " + isUnderline);
        System.out.println("isBold " + isBold);
        System.out.println("isItalic " + isItalic);
    }

    // Variante 2 
    private static boolean isBold(final int fontStyles)
    {
        return (fontStyles & BOLD) == BOLD;
    }

    // Variante 3
    private static boolean isAttributeEnabled(final int fontStyles, final int attibuteValue)
    {
        return (fontStyles & attibuteValue) == attibuteValue;
    }
}
