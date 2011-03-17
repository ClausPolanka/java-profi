package ch12_entwurfsmuster;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Die Klasse <code>AbstractGraphicsElement</code> modelliert eine Basisklasse für
 * grafische Figuren, die eine Position besitzen.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public abstract class AbstractGraphicsElement
{
    private Point pos;

    public AbstractGraphicsElement(int x, int y)
    {
        pos = new Point(x, y);
    }

    public abstract AbstractGraphicsElement makeCopy();

    public final Point getPosition()
    {
        return pos;
    }

    public void setPosition(int x, int y)
    {
        pos = new Point(x, y);
    }
    
    public abstract void draw(final Graphics2D g2D);
}
