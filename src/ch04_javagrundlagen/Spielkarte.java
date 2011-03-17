package ch04_javagrundlagen;

/**
 * Beispielklasse zur Demonstration der Implementierung von equals()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Spielkarte
{
    enum Farbe 
    {
        KARO, HERZ, PIK, KREUZ
    }
    
    private final Farbe farbe;
    private final int   wert;

    public Spielkarte(final Farbe farbe, final int wert)
    {
        this.farbe = farbe;
        this.wert = wert;
    }
}
