package ch03_oodesign;

/**
 * Beispielklasse zur Demonstration der Technik Immutable-Klasse
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ImmutablePosition
{
    private final int x;
    private final int y;

    public ImmutablePosition(final int x, final int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()    { return x; }
    public int getY()    { return y; }

    public ImmutablePosition offset(final int xOffset, final int yOffset)
    {
        return new ImmutablePosition(x + xOffset, y + yOffset);
    }
}