package util;

import java.io.File;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public final class Log4jSupport
{
    private static final Logger log = Logger.getLogger(Log4jSupport.class);

    private Log4jSupport()
    {
    }

    public static void configureLog4j(final String aDefaultIniFile)
    {
        // Konfiguriere den log4j - Logger.
        final String log4jIniFile = aDefaultIniFile;
        if (log4jIniFile != null)
        {
            final File file = new File(log4jIniFile);
            if (file.exists())
            {
                PropertyConfigurator.configureAndWatch(log4jIniFile);
            }
            else
            {
                BasicConfigurator.configure();
                log.error("Unable to read log4j-file '" + log4jIniFile + "'.");
            }
        }
        else
        {
            BasicConfigurator.configure();
        }
    }
}
