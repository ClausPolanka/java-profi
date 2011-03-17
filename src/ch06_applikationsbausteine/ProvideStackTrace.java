package ch06_applikationsbausteine;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Beispiel, wie man beim Logging einen Stacktrace erzeugen kann
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ProvideStackTrace
{   
    private static final Logger log = Logger.getLogger(ProvideStackTrace.class);

    public static void main(final String[] args)
    {
        PropertyConfigurator.configureAndWatch("config/log4j.properties", 5000);
        method1();
    }

    // just to provide a call stack
    private static void method1()
    {
        method2();        
    }

    private static void method2()
    {
        method3();
    }

    private static void method3()
    {
        provideStackTrace();
    }

    private static void provideStackTrace()
    {
        log.info("Stacktrace: ", new IllegalStateException("Stacktrace!"));
    }
}