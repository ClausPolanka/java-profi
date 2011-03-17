package ch16_optimierungen;

import javax.swing.JFrame;

/**
 * Beispielprogramm zur Demonstration verschiedener Optimierungstechniken
 * <br>
 * Ausgangsbasis bildet diese Klasse, die eine Tabelle mit 1.000.000 Einträgen
 * darstellt, die auf indizierte Zugriffe optimiert in einer ArrayList verwaltet werden.
 * Die Darstellung erfolgt mit dem CachedImageTableCellRenderer. 
 * Dort werden die Bilder optimiert zwischengespeichert.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ListOptimizationExampleOneMillion
{
    public static void main(final String[] args)
    {
        final JFrame demoframe = new ListOptimizationExample(1000000, // 1.000.000 
                                                             ListType.ArrayList, new CachedImageTableCellRenderer());

        demoframe.setSize(700, 500);
        demoframe.setVisible(true);
    }

    private ListOptimizationExampleOneMillion()
    {
    }
}