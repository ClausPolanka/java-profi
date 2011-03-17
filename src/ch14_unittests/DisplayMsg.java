package ch14_unittests;

import java.util.Arrays;

/**
 * Beispielklasse zur Simulation der Verarbeitung und Ausgabe von Nachrichten vom
 * Typ DisplayMsg auf der Konsole
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DisplayMsg
{
    public byte[] msgBytes;

    DisplayMsg(final String strMsg)
    {
        final String strLength = DisplayMsgUtils.buildAlignedDecString(strMsg.length(), 4);
        final int checksum = DisplayMsgUtils.calcCrc(strLength + strMsg);
        final String strChecksum = DisplayMsgUtils.buildAlignedDecString(checksum, 4);

        final String payLoad = strLength + strMsg + strChecksum;

        final String completeMsg = DisplayMsgUtils.addFraming(payLoad);

        this.msgBytes = completeMsg.getBytes();
    }

    DisplayMsg(final byte[] msgBytes)
    {
        this.msgBytes = msgBytes;
    }

    public byte[] getPayloadMsgBytes()
    {
        return msgBytes;
    }

    @Override
    public String toString()
    {
        return "DisplayMsg: " + Arrays.toString(msgBytes);
    }
}