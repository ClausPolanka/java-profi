package ch14_unittests;

import java.util.concurrent.TimeUnit;

import ch07_multithreading.SleepUtils;
import ch14_unittests.DisplayMsg;
import ch14_unittests.IDisplay;
import ch14_unittests.MessageConcatenator;
import ch14_unittests.SimulationDisplay;
import junit.framework.TestCase;

/**
 * Testklasse f�r die Klasse MessageConcatenator, basierend auf JUnit 3
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class MessageConcatenatorTest extends TestCase
{
    private MessageConcatenator messageConcatenator;
    private IDisplay            testDisplay;

    public void setUp()
    {
        testDisplay = new SimulationDisplay();
        messageConcatenator = new MessageConcatenator(testDisplay);
    }

    // ...

    public void testExplicitFlush()
    {
        // Deaktivieren des AUTO-FLUSHINGs  
        messageConcatenator.enableAutoFlush(false);

        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_40));
        assertEquals(40 + 10, messageConcatenator.getBufferedMsgLength());

        // Explizites Flushen, Erwartung: Rest 0  
        messageConcatenator.flush();
        assertEquals(0, messageConcatenator.getBufferedMsgLength());
    }

    public void testOverflow()
    {
        // Deaktivieren des AUTO-FLUSHINGs 
        messageConcatenator.enableAutoFlush(false);

        // F�lle Puffer bis kurz vor L�ngen-�berschreitung: 90 + 110 + 30 = 230 
        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_80));
        assertEquals(80 + 10, messageConcatenator.getBufferedMsgLength());
        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_100));
        assertEquals(90 + 100 + 10, messageConcatenator.getBufferedMsgLength());
        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_20));
        assertEquals(200 + 20 + 10, messageConcatenator.getBufferedMsgLength());

        // Erzwinge Flush durch L�ngen-�berschreitung: 230 + 70 = 300
        // Daher sollte die folgende Nachricht den Puffers leeren 
        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_60));
        // Hier sollte noch ein Rest von 70 Bytes existieren 
        assertEquals(60 + 10, messageConcatenator.getBufferedMsgLength());
    }

    public void testAutoFlush()
    {
        // Aktivieren des AUTO-FLUSHINGs
        messageConcatenator.enableAutoFlush(true);

        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_80));
        assertEquals(80 + 10, messageConcatenator.getBufferedMsgLength());

        // Pr�fe Auto-Flush: Warte 1 Sekunde, dann Erwartung: Rest 0
        waitForOneSecond();
        assertEquals(0, messageConcatenator.getBufferedMsgLength());
    }

    public void testAutoFlushAndOverflow()
    {
        // Aktivieren des AUTO-FLUSHINGs 
        messageConcatenator.enableAutoFlush(true);

        // Erzwinge Flush durch L�ngen-�berschreitung
        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_80));
        assertEquals(80 + 10, messageConcatenator.getBufferedMsgLength());
        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_100));
        assertEquals(90 + 100 + 10, messageConcatenator.getBufferedMsgLength());

        // Die n�chste Nachricht der L�nge 90 passt nicht mehr: 90 + 110 + 90 = 290 
        messageConcatenator.sendMessage(createTextMsg(SampleData.TEXT_80));
        assertEquals(80 + 10, messageConcatenator.getBufferedMsgLength());

        // Pr�fe Auto-Flush: Warte 1 Sekunde, dann Erwartung: Rest 0
        waitForOneSecond();
        assertEquals(0, messageConcatenator.getBufferedMsgLength());
    }

    private static void waitForOneSecond()
    {
        SleepUtils.safeSleep(TimeUnit.SECONDS, 1);
    }

    private static DisplayMsg createTextMsg(final String text)
    {
        return new DisplayMsg(text);
    }
}
