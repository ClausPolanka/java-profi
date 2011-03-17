package ch08_advancedjava.i18n.basics;

import java.io.File;
import java.io.FileFilter;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility-Klasse zur Vereinfachung beim Umgang mit ResourceBundles
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ResourceBundleUtils
{
    private static final String INDICATOR_MISSING_RESOURCE = "?";
    private static final String INDICATOR_MISSING_KEY      = "??";

    public static String getLangString(final ResourceBundle currentResourceBundle, final ResourceKeys key)
    {
        if (currentResourceBundle != null)
        {
            try
            {
                return currentResourceBundle.getString(key.name());
            }
            catch (final MissingResourceException e)
            {
                return INDICATOR_MISSING_KEY + key;
            }
        }

        return INDICATOR_MISSING_RESOURCE + key;
    }

    // ...

    public static Locale createLocaleFromBundleName(final String name)
    {
        // Präfix und Postfix abschneiden
        final int languageIndex = name.indexOf('_');
        final int countryIndex = name.indexOf('_', languageIndex + 1);

        String language = null;
        String country = "";

        if (languageIndex > 0 && name.length() > languageIndex + 3)
        {
            language = name.substring(languageIndex + 1, languageIndex + 3);
        }
        if (countryIndex > 0 && name.length() > countryIndex + 3)
        {
            country = name.substring(countryIndex + 1, countryIndex + 3);
        }

        if (language == null)
            return Locale.getDefault();

        return new Locale(language, country);
    }

    // ...
    public static class ResourceBundleFileFilter implements FileFilter
    {
        private final String bundlename;

        public ResourceBundleFileFilter(final String bundlename)
        {
            this.bundlename = bundlename;
        }

        @Override
        public boolean accept(final File pathname)
        {
            return pathname.getName().startsWith(bundlename)
                   && pathname.getName().toLowerCase().endsWith(".properties");
        }
    };

    private ResourceBundleUtils()
    {
    }
}