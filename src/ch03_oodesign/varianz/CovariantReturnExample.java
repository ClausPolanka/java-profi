package ch03_oodesign.varianz;

/**
 * Beispiel für kovariante Rückgabewerte
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CovariantReturnExample
{
    public static void main(String[] args)
    {
        final BaseFigure baseFigure = new CircleFigure();
        final CircleFigure circleFigure = new CircleFigure();

        final CircleFigure circleTypeCopy = circleFigure.copy(); // Kovariante Rückgabe 
        final double radius = circleTypeCopy.getRadius();

        //final CircleFigure circleTypeCopy = baseFigure.copy(); // Compile-Error 

        final BaseFigure baseCopy = baseFigure.copy();
        System.out.println(baseCopy.getClass()); // CircleFigure 
    }

    private CovariantReturnExample()
    {
    }
}

// Hilfsklassen usw. ... hier stark vereinfacht
abstract class BaseFigure
{
    abstract BaseFigure copy();
}

class CircleFigure extends BaseFigure
{
    public CircleFigure copy()
    {
        return new CircleFigure();
    }

    public double getRadius()
    {
        return 0;
    }
}

class RectFigure extends BaseFigure
{
    public RectFigure copy()
    {
        return new RectFigure();
    }
}