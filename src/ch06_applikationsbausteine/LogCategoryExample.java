package ch06_applikationsbausteine;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Beispiel für den Einsatz mehrerer Log-Kategorien
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LogCategoryExample
{
    private static final Logger logger      = Logger.getLogger(LogCategoryExample.class);

    private static final Logger audioInLog  = Logger.getLogger("AudioIn");
    private static final Logger audioOutLog = Logger.getLogger("AudioOut");
    private static final Logger replayLog   = Logger.getLogger("REPLAY");

    LogCategoryExample()
    {
        logger.info("LogCategoryExample created");
    }

    private void send(final byte[] msg)
    {
        logger.debug("send()");
        audioOutLog.info("Sending " + ByteUtils.byteArrayToString(msg));
        replayLog.info("Sending " + ByteUtils.byteArrayToString(msg));
    }

    private byte[] receive(final InputStream inStream)
    {
        logger.debug("receive()");
        
        final byte[] msg = getMsgFromStream(inStream); 
        audioInLog.info("Receiving " + ByteUtils.byteArrayToString(msg));
        replayLog.info("Receiving " + ByteUtils.byteArrayToString(msg));
        return msg;
    }

    public static void main(final String[] args)
    {
        PropertyConfigurator.configureAndWatch("config/log4j.properties");
        
        final LogCategoryExample logExample = new LogCategoryExample();
        logExample.send("Hello".getBytes());
    }    
    // ...

    private byte[] getMsgFromStream(final InputStream inStream)
    {
        return "Good Byer".getBytes();
    }
}