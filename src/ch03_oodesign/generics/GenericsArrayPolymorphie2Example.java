package ch03_oodesign.generics;

import java.util.Arrays;
import java.util.List;

/**
 * Hilfsklasse zur Demonstration von Kovarianz bei Arrays und generischen Containern
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class GenericsArrayPolymorphie2Example
{
    public static void main(String[] args)
    {
        // Identische Definitionen 
        final CircleFigure[] circles = { new CircleFigure(), new CircleFigure() };
        final RectFigure[] rects = { new RectFigure(), new RectFigure() };
        final BaseFigure[] figures = { new CircleFigure(), new RectFigure() };

        // Umwandlung Array -> Liste (Arrays.asList(T...)) 
        final List<BaseFigure> figureList = Arrays.asList(figures);
        printInfo(figureList);

        // Compile-Error: Type mismatch: cannot convert from 
        // List<CircleFigure> to List<BaseFigure> 
        // final List<BaseFigure> circleList = Arrays.asList(circles);

        // Compile-Error: The method printInfo(List<BaseFigure>) in the type 
        // GenericsArrayPolymorphie2Example is not applicable 
        // for the arguments (List<Rect"-Figure>) 
        // printInfo(Arrays.asList(rects));
    }

    public static void printInfo(final List<BaseFigure> figures)
    {
        for (final BaseFigure figure : figures)
            figure.printInfo();
    }

    private GenericsArrayPolymorphie2Example()
    {
    }
}
