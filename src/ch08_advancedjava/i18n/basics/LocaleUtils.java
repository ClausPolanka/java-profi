package ch08_advancedjava.i18n.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Utility-Klasse zur Vereinfachung beim Umgang mit der Klasse Locale
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LocaleUtils
{     
    public static final Comparator<Locale> LOCALE_COMPARATOR = new Comparator<Locale>()
    {
        public int compare(final Locale locale1, final Locale locale2)
        {
            final int val = locale1.getLanguage().compareTo(locale2.getLanguage());
            if (val == 0)
                return locale1.getCountry().compareTo(locale2.getCountry());
    
            return val;
        }
    };

    public static final List<Locale> getSortedLocales()
    {
        final Locale[] availableLocale = Locale.getAvailableLocales();
        final List<Locale> localeList = Arrays.asList(availableLocale);

        // unabhängig machen
        final List<Locale> sortedLocaleList = new ArrayList<Locale>(localeList);
        Collections.sort(sortedLocaleList, LocaleUtils.LOCALE_COMPARATOR);

        return sortedLocaleList;
    }

    public static final List<Locale> getLanguageOnlyLocales()
    {
        final List<Locale> sortedLocaleList = getSortedLocales();

        final List<Locale> filteredLocaleList = new ArrayList<Locale>();
        for (final Locale currentLocale : sortedLocaleList)
        {
            if (currentLocale.getCountry().isEmpty())
                filteredLocaleList.add(currentLocale);
        }

        return filteredLocaleList;
    }

    private LocaleUtils()
    {
    }
}
