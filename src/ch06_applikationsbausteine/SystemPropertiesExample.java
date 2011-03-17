package ch06_applikationsbausteine;

import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Beispiel für das Auslesen von System-Properties
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SystemPropertiesExample
{
    public static void main(String[] args)
    {
        final Properties systemProperties = System.getProperties();
        final SortedMap<String, String> sortedProperties = new TreeMap(systemProperties);

        for (Map.Entry<String, String> entry : sortedProperties.entrySet())
        {
            System.out.println("Key = " + entry.getKey() + " / Value = " + entry.getValue());
        }
    }
    
    private SystemPropertiesExample()
    {        
    }
}
