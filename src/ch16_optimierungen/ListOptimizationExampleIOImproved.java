package ch16_optimierungen;

import javax.swing.JFrame;

/**
 * Beispielprogramm zur Demonstration verschiedener Optimierungstechniken
 * <br>
 * Ausgangsbasis bildet diese Klasse, die eine Tabelle mit 50.000 Einträgen
 * darstellt, die in einer LinkedList verwaltet werden.
 * Die Darstellung erfolgt nun mit dem CachedImageTableCellRenderer. 
 * Dort werden die Bilder optimiert zwischengespeichert.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ListOptimizationExampleIOImproved
{
    public static void main(final String[] args)
    {
        final JFrame demoframe = new ListOptimizationExample(50000, ListType.LinkedList, new CachedImageTableCellRenderer());
        demoframe.setSize(700, 500);
        demoframe.setVisible(true);
    }
    
    private ListOptimizationExampleIOImproved()
    {
    }
}