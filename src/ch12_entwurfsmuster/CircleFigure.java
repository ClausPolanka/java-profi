package ch12_entwurfsmuster;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Die Klasse <code>RectFigure</code> modelliert einen Kreis.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class CircleFigure extends FigureElement
{
    final int radius;

    protected CircleFigure(final Color drawingColor, final int xMidPoint, final int yMidPoint, final int radius)
    {
        super(drawingColor, xMidPoint, yMidPoint);

        this.radius = radius;
    }

    @Override
    public AbstractGraphicsElement makeCopy()
    {
        return new CircleFigure(getDrawingColor(), getPosition().x, getPosition().y, radius);
    }

    @Override
    public void draw(final Graphics2D g2d)
    {
        final Color oldColor = g2d.getColor();

        g2d.setColor(getDrawingColor());
        g2d.drawOval(getPosition().x - radius, getPosition().y - radius, radius * 2, radius * 2);
        g2d.setColor(oldColor);
    }
}
