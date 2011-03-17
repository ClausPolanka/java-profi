package ch04_javagrundlagen.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ch06_applikationsbausteine.StreamUtils;
import ch07_multithreading.SleepUtils;

/**
 * Beispielprogramm zur Demonstration der Netzwerkprogrammierung eines Servers
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class EchoServer
{
    private static final int PORT_NR = 7777;

    // Achtung: Nur rudimentäre Fehlerbehandlung 
    public static void main(String[] args) throws IOException
    {
        System.out.println("Listening on port " + PORT_NR);
        final ServerSocket serverSocket = new ServerSocket(PORT_NR);
        
        while (!Thread.currentThread().isInterrupted())
        {
            Socket socket = null;
            try
            {
                socket = serverSocket.accept();            
                handleNewConnection(socket);
            }
            finally
            {
                SocketUtils.safeClose(socket);
            }            
        }
    }

    private static void handleNewConnection(final Socket socket) throws IOException
    {
        InputStream in = null;
        OutputStream out = null;

        try
        {           
            System.out.println("Client connected");

            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(socket.getOutputStream());

            // Warte auf Daten vom Client 
            while (in.available() <= 0)
                SleepUtils.safeSleep(50);
            
            // Antwort einlesen und ausgeben 
            final int available = in.available();
            final byte[] buffer = new byte[available];
            in.read(buffer);                

            // Daten in den Ausgabestream schreiben => an Client zurüksenden 
            System.out.println("Sending answer to client '" + 
                               new String(buffer) + "'");
            out.write(buffer);
            out.flush();
        }
        finally
        {
            StreamUtils.safeClose(in);
            StreamUtils.safeClose(out);
        }
    }
}