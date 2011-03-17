package ch04_javagrundlagen.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import ch06_applikationsbausteine.StreamUtils;
import ch07_multithreading.SleepUtils;

/**
 * Beispielprogramm zur Demonstration der Netzwerkprogrammierung eines Clienten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class EchoClient
{
    private static final int PORT_NR = 7777; 
    
    // Achtung: Nur rudimentäre Fehlerbehandlung 
    public static void main(String[] args) throws IOException
    {
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try
        {
            // Aufbau der Verbindung  
            System.out.println("Connecting to server");
            final String serverIP = "127.0.0.1";
            socket = new Socket(serverIP, PORT_NR);

            // Zugriff auf die Streams 
            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(socket.getOutputStream());

            // Daten in Stream schreiben => an den Server senden 
            System.out.println("Sending 'Hello World' to server");
            out.write("Hello World".getBytes());
            out.flush();
            
            // Warte auf Antwort vom Server 
            while (in.available() <= 0)
                SleepUtils.safeSleep(50);
            
            // Antwort auslesen und ausgeben 
            System.out.println("Receiving answer from server");
            final int available = in.available();
            final byte[] buffer = new byte[available];
            in.read(buffer);
            System.out.println("Answer = '" + new String(buffer) + "'");
        }
        finally
        {
            StreamUtils.safeClose(in);
            StreamUtils.safeClose(out);
            SocketUtils.safeClose(socket);
        }
        // ...
    }
}