package ch03_oodesign;

import java.util.EnumSet;

/**
 * Beispiel einer einfachen Aufz�hlung als enum und deren Kombination mit EnumSet
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public enum FontAttributesEnum
{
    BOLD, ITALIC, UNDERLINE;

    public static void main(final String args[])
    {
        final EnumSet<FontAttributesEnum> fontStyles = EnumSet.of(BOLD, ITALIC);

        final boolean isUnderline = fontStyles.contains(UNDERLINE);
        final boolean isBold = fontStyles.contains(BOLD);

        System.out.println("isUnderline " + isUnderline);
        System.out.println("isBold " + isBold);

        // n�tzliche weitere Methoden 
        System.out.println("All " + EnumSet.allOf(FontAttributesEnum.class));
        System.out.println("None " + EnumSet.noneOf(FontAttributesEnum.class));
    }
}