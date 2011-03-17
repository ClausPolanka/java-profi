package ch06_applikationsbausteine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch05_collections.Person;

/**
 * Beispiel für das Auslesen von Properties aus einer Konfigurationsdatei und
 * mögliche unerwartete Probleme durch die Implementierungsvererbung der
 * Klasse Properties von der Klasse Hashtable
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AppPropertiesProblems
{
    public static final void main(String[] args) throws IOException
    {
        final Properties properties = new Properties();

        InputStream in = null;
        try
        {
            in = new FileInputStream("config/AppConfig.properties");
            properties.load(in);

            final String dbUser = properties.getProperty(PropertyName.DB_USER.propertyKey);
            final String dbPwd = properties.getProperty(PropertyName.DB_PASSWORD.propertyKey);
            System.out.println("DB USER / PWD= '" + dbUser + "' / '" + dbPwd + "'");

            // unerwartet kann man beliebige Objekte in Properties speichern     
            System.out.println("Einfügen des Person-Objekts MICHA");
            properties.put("MICHA", new Person("Micha", "Aachen", 39));

            // kein Zugriff auf Property mit getProperty()            
            System.out.println("getProperty() = '" + properties.getProperty("MICHA") + "'");

            // aber Zugriff über die Basisklasse mit get()    
            System.out.println("get()= '" + properties.get("MICHA") + "'");
        }
        finally
        {
            StreamUtils.safeClose(in);
        }
    }

    private AppPropertiesProblems()
    {
    }
}