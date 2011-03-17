package ch03_oodesign;

import java.util.LinkedList;
import java.util.List;

/**
 * Beispiel für den Einsatz der Klasse Counter zum Zählen gemalter Figuren
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CounterUsage
{
    private static final List<GraphicObject> graphicObjects = new LinkedList<GraphicObject>();

    static
    {
        graphicObjects.add(new Line());
        graphicObjects.add(new Line());
        graphicObjects.add(new Rect());
    }

    public static void main(String[] args)
    {
        final Counter lineCounter = new Counter();
        final Counter rectCounter = new Counter();

        for (final GraphicObject graphicObject : graphicObjects)
        {
            graphicObject.draw();

            if (graphicObject instanceof Line)
            {
                lineCounter.increment();
            }

            if (graphicObject instanceof Rect)
            {
                rectCounter.increment();
            }
        }

        System.out.println("Number of Lines: " + lineCounter.currentValue());
        System.out.println("Number of Rects: " + rectCounter.currentValue());
    }
    
    private CounterUsage()
    {        
    }
}


// Hilfsklassen usw. ... hier stark vereinfacht
enum GraphicObjectType {
    LINE, RECT
};

class GraphicObject
{
    public GraphicObject()
    {
    }

    public void draw()
    {
    }
}

class Line extends GraphicObject
{
}

class Rect extends GraphicObject
{
}