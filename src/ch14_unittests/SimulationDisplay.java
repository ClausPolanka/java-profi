package ch14_unittests;

/**
 * Beispielklasse zur Simulation der Verarbeitung und Ausgabe von Nachrichten vom
 * Typ DisplayMsg auf der Konsole
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SimulationDisplay implements IDisplay
{
    public SimulationDisplay()
    {
    }

    public boolean displayMsg(final DisplayMsg displayMsg)
    {
        System.out.println("SimulationDisplay - got msg '" + displayMsg + "'");
        return true;
    }
}