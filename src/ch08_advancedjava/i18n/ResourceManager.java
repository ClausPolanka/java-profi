package ch08_advancedjava.i18n;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import ch08_advancedjava.i18n.basics.ResourceBundleUtils;
import ch08_advancedjava.i18n.basics.ResourceKeys;

/**
 * Beispielklasse zur Demonstration der Verwaltung mehrerer PropertyResourceBundles
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ResourceManager
{
    private static final Logger log = Logger.getLogger(ResourceManager.class);

    // ACHTUNG: Muss im Classpath zugänglich sein!  
    private static final String BUNDLE_PATH = "resources.PDFEditor";

    // Verwaltung und Speicherung der ResourceBundles  
    private final Map<Locale, ResourceBundle> availableResourceBundles = new HashMap<Locale, ResourceBundle>();

    private ResourceBundle                    currentResourceBundle    = null;
    private Locale                            currentLocale            = null;

    private ResourceManager()
    {
    }
    
    // Erzeugung einer gebrauchfertigen Instanz  
    public static ResourceManager createInstance()
    {
        final ResourceManager resourceManager = new ResourceManager();
        resourceManager.init();
        resourceManager.activateLocale(Locale.GERMANY);
        
        return resourceManager;
    }

    private void init()
    {
        loadAndAddResourceBundle(Locale.GERMANY);
        loadAndAddResourceBundle(Locale.UK);
        loadAndAddResourceBundle(Locale.FRANCE);        
    }
    
    private void loadAndAddResourceBundle(final Locale locale)
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
    
    private void addResourceBundle(final Locale locale, final ResourceBundle resourceBundle)
    {
        availableResourceBundles.put(locale, resourceBundle);
    }

    // Zugriff auf sprachabhängige Texte  
    public String getLangString(final ResourceKeys key)
    {
        return ResourceBundleUtils.getLangString(currentResourceBundle, key);
    }

    // Umschaltung der Sprache  
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

    // Hilfsmethoden mit selbsterklärenden Namen 
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