package ch14_unittests;

import java.util.Arrays;

/**
 * Utility-Klasse, die beim Verarbeiten von Nachrichten vom Typ DisplayMsg unterstützt 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class DisplayMsgUtils
{
    // error codes for checkMessageContents
    public static final int    CHECK_MSG_OK                    = 0;
    public static final int    CHECK_MSG_ERROR_MISSING_STX     = 1;
    public static final int    CHECK_MSG_ERROR_MISSING_ETX     = 2;
    public static final int    CHECK_MSG_ERROR_WRONG_CHECKSUM  = 3;
    public static final int    CHECK_MSG_ERROR_NO_MSG_CONTENTS = 4;

    public static final String STX_AS_STRING                   = "\02";
    public static final String ETX_AS_STRING                   = "\03";

    public static final byte   STX                             = STX_AS_STRING.getBytes()[0];
    public static final byte   ETX                             = ETX_AS_STRING.getBytes()[0];

    private static final int   FRAME_OFFSET                    = 1;
    private static final int   CHECKSUM_OFFSET                 = 4;

    private DisplayMsgUtils()
    {
    }

    public static int checkMessage(final byte[] message) throws IllegalArgumentException
    {
        if (message == null || message.length == 0)
        {
            return CHECK_MSG_ERROR_NO_MSG_CONTENTS;
        }

        if (!frameOK(message))
        {
            if (message[0] != STX)
            {
                return CHECK_MSG_ERROR_MISSING_STX;
            }
            if (message[message.length - 1] != ETX)
            {
                return CHECK_MSG_ERROR_MISSING_ETX;
            }
        }

        if (!checksumOK(message))
        {
            return CHECK_MSG_ERROR_WRONG_CHECKSUM;
        }

        return CHECK_MSG_OK;
    }

    /**
     * strip of first and last byte of the passed message
     */
    public static byte[] stripOfFraming(final byte[] message)
    {
        // frame less message     
        final byte[] framelessMsg = Arrays.copyOfRange(message, FRAME_OFFSET, message.length - FRAME_OFFSET);

        return framelessMsg;
    }

    /** 
     * extract crc 
     * CRC = die hintersten 2 Bytes nach Entfernern des Framing
     * 
     * @param message   message with framing 
     */
    public static int extractCrc(final byte[] telegramBytes)
    {
        // read expected checksum
        final byte[] actualCRC = Arrays.copyOfRange(telegramBytes, telegramBytes.length - CHECKSUM_OFFSET,
                                                    telegramBytes.length - CHECKSUM_OFFSET + 4);

        final String actualStrCRC = new String(actualCRC);

        // als Hex
        return Integer.parseInt(actualStrCRC, 16);
    }

    public static boolean isValidTelegram(final byte[] msgBytes)
    {
        if (frameOK(msgBytes) && checksumOK(msgBytes))
        {
            return true;
        }
        return false;
    }

    public static boolean frameOK(final byte[] telegramBytes)
    {
        if (telegramBytes[0] == STX && telegramBytes[telegramBytes.length - 1] == ETX)
        {
            return true;
        }
        return false;
    }

    public static boolean checksumOK(final byte[] telegramBytes)
    {
        // strip frame and checksum from message
        final byte[] strippedMsg = Arrays.copyOfRange(telegramBytes, FRAME_OFFSET, telegramBytes.length
                                                                                   - CHECKSUM_OFFSET);

        // calculate actual checksum
        final int expectedCrc = calcCrc(strippedMsg);
        final String expectedStrCRC = buildAlignedDecString(expectedCrc, 4);

        // read expected checksum
        final byte[] actualCRC = Arrays.copyOfRange(telegramBytes, telegramBytes.length - CHECKSUM_OFFSET,
                                                    telegramBytes.length - CHECKSUM_OFFSET + 2);
        final String actualStrCRC = new String(actualCRC);

        // compare expected and actual checksums
        if (expectedStrCRC.equalsIgnoreCase(actualStrCRC))
        {
            return true;
        }
        return false;
    }

    public static String addFraming(final String strTelegramData)
    {
        // add stx '\02' && etx '\03'
        return STX_AS_STRING + strTelegramData + ETX_AS_STRING;
    }

    public static int calcCrc(final byte[] dataBuffer)
    {
        return calcCrc(dataBuffer, 0, dataBuffer.length);
    }

    public static int calcCrc(final byte[] dataBuffer, final int offset, final int length)
    {
        if (dataBuffer == null)
        {
            return 0;
        }
        int crc = 0xff;
        for (int i = offset; i < length; i++)
        {
            crc = crc ^ (char) dataBuffer[i];
        }

        return crc;
    }

    public static int calcCrc(final String strTelegram)
    {
        int crc = 0xff;
        for (int i = 0; i < strTelegram.length(); i++)
            crc = crc ^ strTelegram.charAt(i);

        return crc;
    }
    
    // 
    
    // 21h => 32h 31h
    public static String buildAlignedHexString(final int value, final int desiredLength)
    {
        final String strHex = Integer.toHexString(value).toUpperCase();

        return buildAlignedString(strHex, desiredLength, '0');
    }

    // 75dez => 37h 35h
    public final static String buildAlignedDecString(final int value, final int desiredLength)
    {
        final String strDec = Integer.toString(value);

        return buildAlignedString(strDec, desiredLength, '0');
    }

    private final static String buildAlignedString(final String strValue, final int desiredLength, final char fillUpChar)
    {
        String result = "";

        final char[] chars = new char[desiredLength];

        final int nrStart = desiredLength - strValue.length();

        // add "0" chars to fill up to desired length
        for (int i = 0; i < nrStart; i++)
        {
            chars[i] = fillUpChar;
        }

        // convert value to chars
        if (nrStart >= 0)
        {
            for (int i = 0; i < strValue.length(); i++)
            {
                chars[nrStart + i] = (char) (strValue.charAt(i)); // + '0' );
            }
        }

        result = String.valueOf(chars);

        return result;
    }    
}