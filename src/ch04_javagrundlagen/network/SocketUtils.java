package ch04_javagrundlagen.network;

import java.io.IOException;
import java.net.Socket;

/**
 * Utilityklasse
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SocketUtils
{
    public static void safeClose(final Socket socket)
    {
        try
        {
            if (socket != null)
                socket.close();
        }
        catch (final IOException e)
        {
            // IOException bei close ignorieren
        }
    }
    // ...
}

