package ch08_advancedjava.i18n;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import ch06_applikationsbausteine.FileUtils;
import ch06_applikationsbausteine.StreamUtils;
import ch08_advancedjava.i18n.basics.ResourceBundleUtils;
import ch08_advancedjava.i18n.basics.ResourceKeys;

/**
 * Beispielklasse zur Demonstration der Verwaltung mehrerer PropertyResourceBundles
 * <br>
 * Erweiterung: Automatisches Einlesen der ResourceBundle aus einem Verzeichnis
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ResourceManagerV2
{
    private static final Logger               log                      = Logger.getLogger(ResourceManagerV2.class);
    private static final String               BUNDLE_PATH              = "PDFEditor";
    private static final File                 bundleDir                = new File("config/resources");

    // Verwaltung und Speicherung der ResourceBundles  
    private final Map<Locale, ResourceBundle> availableResourceBundles = new HashMap<Locale, ResourceBundle>();

    private ResourceBundle                    currentResourceBundle    = null;

    private Locale                            currentLocale            = null;

    private ResourceManagerV2()
    {
    }

    // Erzeugung einer gebrauchfertigen Instanz 
    public static ResourceManagerV2 createInstance()
    {
        final ResourceManagerV2 resourceManager = new ResourceManagerV2();
        resourceManager.init();
        resourceManager.activateLocale(Locale.GERMANY);

        return resourceManager;
    }

    private void init()
    {
        final FileFilter fileFilter = new ResourceBundleUtils.ResourceBundleFileFilter("PDFEditor");
        final File[] propertyFiles = FileUtils.getAllMatchingFiles(bundleDir, fileFilter);

        for (final File propertyFile : propertyFiles)
        {
            InputStream is = null;
            try
            {
                is = new BufferedInputStream(new FileInputStream(propertyFile));
                final PropertyResourceBundle resourceBundle = new PropertyResourceBundle(is);

                final Locale locale = ResourceBundleUtils.createLocaleFromBundleName(propertyFile.getName());
                addResourceBundle(locale, resourceBundle);
            }
            catch (final IOException ex)
            {
                log.warn("Failed to load resource from file '" + propertyFile.getAbsolutePath() + "'", ex);
            }
            finally
            {
                StreamUtils.safeClose(is);
            }
        }
    }

    public void loadAndAddResourceBundle(final Locale locale)
    {
        try
        {
            final ResourceBundle resourceBundle = PropertyResourceBundle.getBundle(BUNDLE_PATH, locale);
            addResourceBundle(locale, resourceBundle);
        }
        catch (final MissingResourceException ex)
        {
            log.warn("Missing recource '" + BUNDLE_PATH + "' for locale: '" + locale + "'", ex);
        }
    }

    protected void addResourceBundle(final Locale locale, final ResourceBundle resourceBundle)
    {
        availableResourceBundles.put(locale, resourceBundle);
    }

    public String getLangString(final ResourceKeys key)
    {
        return ResourceBundleUtils.getLangString(currentResourceBundle, key);
    }

    public boolean activateLocale(final Locale locale)
    {
        if (supportsLocale(locale))
        {
            currentResourceBundle = availableResourceBundles.get(locale);
            currentLocale = locale;
            return true;
        }

        return false;
    }

    public boolean supportsLocale(final Locale locale)
    {
        return availableResourceBundles.containsKey(locale);
    }

    public Locale getCurrentLocale()
    {
        return currentLocale;
    }

    public List<Locale> getAvailableLocales()
    {
        return new ArrayList<Locale>(availableResourceBundles.keySet());
    }
}