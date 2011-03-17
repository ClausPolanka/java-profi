package ch03_oodesign;

/**
 * Beispiel einer einfachen Aufzählung mit int-Konstanten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ErrorStateAsIntConstants
{
    public static final int OK                = 0;
    public static final int INVALID_POSITION  = 3;
    public static final int INPUT_BUFFER_FULL = 8;

    private ErrorStateAsIntConstants()
    {
        // Vermeide Konstruktion dieser Klasse
    }
}