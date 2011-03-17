package ch12_entwurfsmuster;

import java.awt.Color;

/**
 * Die Klasse <code>FigureElement</code> modelliert eine Basisklasse für
 * grafische Figuren.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public abstract class FigureElement extends AbstractGraphicsElement
{
    private Color drawingColor = Color.BLACK;

    protected FigureElement(final Color drawingColor, final int x, final int y)
    {
        super(x, y);
        setDrawingColor(drawingColor);
    }

    public void setDrawingColor(final Color drawingColor)
    {
        if (drawingColor == null)
            throw new IllegalArgumentException("parameter 'drawingColor' must not be null!");

        this.drawingColor = drawingColor;
    }

    public Color getDrawingColor()
    {
        return drawingColor;
    }
}
