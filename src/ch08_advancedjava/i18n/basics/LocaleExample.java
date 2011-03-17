package ch08_advancedjava.i18n.basics;

import java.util.Locale;

/**
 * Beispielklasse zur Demonstration verschiedener Locales
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LocaleExample
{
    public static final Locale LOCALE_US             = Locale.US;
    public static final Locale LOCALE_FRENCH_SWISS   = new Locale("fr", "CH");
    public static final Locale LOCALE_ITALIAN_SWISS  = new Locale("it", "CH");
    public static final Locale LOCALE_SPANISH        = new Locale("es");
    public static final Locale LOCALE_SPANISH_MEXICO = new Locale("es", "MX");

    // ...

    public static void main(String[] args)
    {
        System.out.println(LOCALE_US.getDisplayCountry());
        System.out.println(LOCALE_US.getDisplayCountry(LOCALE_ITALIAN_SWISS));
        System.out.println(LOCALE_US.getDisplayLanguage());
        System.out.println(LOCALE_US.getDisplayLanguage(LOCALE_ITALIAN_SWISS));
    }
}
