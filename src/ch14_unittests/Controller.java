package ch14_unittests;

import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * Beispielklasse, die einen Controller mit den minimal benötigten Methoden 
 * zur Ansteuerung eines Ausgabegeräts vom Typ IDisplay realisiert 
 * <br>
 * Verbesserte Version: Korrekte Auswertung in hexCodedByteValueToInt()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Controller
{
    private static final Logger log               = Logger.getLogger(Controller.class);

    public static final int     ANSWER_MSG_LENGTH = 10;

    public static final int     STATUS_OFFSET     = 7;

    public int onReceivedAnswer(final byte[] answerMsgBytes)
    {
        if (answerMsgBytes != null && (answerMsgBytes.length == ANSWER_MSG_LENGTH))
        {
            final byte stateHigh = answerMsgBytes[STATUS_OFFSET];
            final byte stateLow = answerMsgBytes[STATUS_OFFSET + 1];

            final int state = calcState(stateHigh, stateLow);

            // ...

            return state;
        }
        else
        {
            log.warn("wrong message format! message is = '" + Arrays.toString(answerMsgBytes) + "'");
        }
        return -1;
    }

    /*private*/ static int calcState(final byte highByte, final byte lowByte)
    {
        final int stateHigh = hexCodedByteValueToInt(highByte);
        final int stateLow = hexCodedByteValueToInt(lowByte);

        final int state = stateHigh * 16 + stateLow;
        return state;
    }

    /*private*/ static int hexCodedByteValueToInt(final byte byteValue)
    {
        if (byteValue >= 'A' && byteValue <= 'F')
        {
            return (10 + (byteValue - 'A'));
        }
        if (byteValue >= '0' && byteValue <= '9')
        {
            return (byteValue - '0');
        }
        throw new IllegalArgumentException("Unexpected byte value: " + byteValue
                                           + ". Must be in Range '0'-'9' (0x30-0x39) or 'A'-'F' (0x41-0x46)");
    }
}
