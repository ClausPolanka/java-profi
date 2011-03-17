package ch03_oodesign.generics;

import java.util.List;

/**
 * Hilfsklasse zur Demonstration von Kovarianz bei Arrays
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class GenericsArrayPolymorphieExample
{
    public static void main(String[] args)
    {
        final CircleFigure[] circles = { new CircleFigure(), new CircleFigure() };
        final RectFigure[] rects = { new RectFigure(), new RectFigure() };
        final BaseFigure[] figures = { new CircleFigure(), new RectFigure() };

        printInfo(circles);
        printInfo(rects);
        printInfo(figures);
    }

    public static void printInfo(final BaseFigure figure)
    {
        figure.printInfo();
    }

    public static void printInfo(final BaseFigure[] figures)
    {
        for (final BaseFigure figure : figures)
            figure.printInfo();
    }

    public static void printInfo(final List<BaseFigure> figures)
    {
        for (final BaseFigure figure : figures)
            figure.printInfo();
    }

    private GenericsArrayPolymorphieExample()
    {        
    }
}
