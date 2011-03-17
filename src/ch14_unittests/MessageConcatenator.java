package ch14_unittests;

import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * Beispielklasse, die Nachrichten konkateniert
 * <br>
 * Die Anzeiger haben einen internen Speicher für eingehende Nachrichten. Dieser Eingangspuffer ist allerdings nicht
 * sonderlich intelligent, sondern bietet nur jeweils Platz für 6 Nachrichten, die jeweils bis zu 256 Bytes lang sein
 * können. Bei kürzeren Nachrichten verfällt der vorhandene Platz ungenutzt.
 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class MessageConcatenator
{
    private static final Logger log                      = Logger.getLogger(MessageConcatenator.class);

    // maximale Telegrammlänge hier auf 250 Bytes begrenzt
    private final int           MAX_MESSAGE_TELEGRAMM    = 250;

    private byte[]              incomingMessageBytes;

    private int                 currentBufferedMsgLength = 0;

    private final IDisplay      display;

    // auto flush thread
    private final AutoFlusher   autoFlusher              = new AutoFlusher();

    public MessageConcatenator(final IDisplay display)
    {
        this.display = display;

        reinitMessageBuffer();

        final Thread autoFlushThread = new Thread(autoFlusher);
        autoFlushThread.start();
    }

    /**
     * External Messages coming in here
     */
    public synchronized boolean sendMessage(final DisplayMsg sendMsg)
    {
        // # 1: Ermitteln der Nutzdaten + Länge
        final byte[] msgBytes = sendMsg.getPayloadMsgBytes();
        final int msgLength = msgBytes.length;

        // #2: Prüfe auf Maximallänge
        if (getBufferedMsgLength() + msgLength > MAX_MESSAGE_TELEGRAMM)
        {
            log.info("cumulated msg reached MAX_MESSAGE_TELEGRAMM length - flushing");

            // #2a i: Rausschreiben der gespeicherten Nachrichten
            flush();

            // #2a ii: (Neu-)Initialisierung des Messagepuffers
            reinitMessageBuffer();
        }

        log.debug("Cummulating msg = " + sendMsg);
        // #2b: Kopieren der Nachrichtenbytes 
        incomingMessageBytes = Arrays.copyOf(msgBytes, getBufferedMsgLength());
        currentBufferedMsgLength += msgLength;

        return true;
    }

    public boolean flush()
    {
        return flushImpl();
    }

    private synchronized boolean flushImpl()
    {
        if (getBufferedMsgLength() > 0)
        {
            log.debug("flushImpl() -- sending cumulated message");

            // als DisplayMsg verpacken 
            final DisplayMsg cumulatedMsg = new DisplayMsg(new String(incomingMessageBytes));

            reinitMessageBuffer();

            return display.displayMsg(cumulatedMsg);
        }

        log.debug("flushImpl() -- No msg to send.");
        return true;
    }

    private void reinitMessageBuffer()
    {
        incomingMessageBytes = new byte[MAX_MESSAGE_TELEGRAMM];
        currentBufferedMsgLength = 0;
    }

    private boolean autoflush()
    {
        return flushImpl();
    }

    // access for unit tests
    /*private*/void enableAutoFlush(final boolean autoFlushEnabled)
    {
        autoFlusher.enableAutoFlush(autoFlushEnabled);
    }

    public final int getBufferedMsgLength()
    {
        return currentBufferedMsgLength;
    }

    /**
     * Periodic flushing of messages
     */
    public final class AutoFlusher implements Runnable
    {
        private static final long AUTO_FLUSH_DELAY_MS = 500;

        // spart uns das synchronized;
        private volatile boolean  isAutoFlushEnabled  = true;

        public void run()
        {
            while (true)
            {
                // wait for some ms, send message to display afterwards
                try
                {
                    Thread.sleep(AUTO_FLUSH_DELAY_MS);
                }
                catch (InterruptedException e)
                {
                    // ignore me
                }

                if (isAutoFlushEnabled())
                    autoflush();
            }
        }

        private boolean isAutoFlushEnabled()
        {
            return isAutoFlushEnabled;
        }

        private void enableAutoFlush(final boolean autoFlushEnabled)
        {
            this.isAutoFlushEnabled = autoFlushEnabled;
        }
    }
}