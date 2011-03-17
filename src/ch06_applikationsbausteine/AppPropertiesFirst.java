package ch06_applikationsbausteine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Beispiel für das Auslesen von Properties aus einer Konfigurationsdatei
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AppPropertiesFirst
{
    public static final void main(String[] args) throws IOException
    {
        final Properties properties = new Properties();

        InputStream in = null;
        try
        {
            in = new FileInputStream("config/AppConfig.properties");
            properties.load(in);

            final String appPdfViewer = properties.getProperty("pdf.viewer");
            System.out.println("PdfViewer = '" + appPdfViewer + "'");

            final String dbUser = properties.getProperty("db.user");
            final String dbPwd = properties.getProperty("db.password");
            System.out.println("DB-USER/PWD = '" + dbUser + "'/'" + dbPwd + "'");
        }
        finally
        {
            StreamUtils.safeClose(in);
        }
    }

    private AppPropertiesFirst()
    {
    }
}