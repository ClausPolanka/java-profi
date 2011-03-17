package ch03_oodesign;

/**
 * Beispiel einer einfachen Aufzählung als enum
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public enum ErrorStateEnum
{
    OK(0, "Ok"), 
    INVALID_POS(3, "Ungï¿½ltige Positionsangabe"), 
    BUFFER_FULL(8, "Empfangspuffer voll");

    private final int    value;
    private final String description;

    private ErrorStateEnum(final int value, final String description)
    {
        this.value = value;
        this.description = description;
    }    

    public int getValue()               { return value; }
    public String getDescription()      { return description; }
}
