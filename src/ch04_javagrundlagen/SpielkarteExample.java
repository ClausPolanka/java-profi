package ch04_javagrundlagen;

import java.util.ArrayList;
import java.util.Collection;

import ch04_javagrundlagen.Spielkarte.Farbe;

/**
 * Beispielklasse zur Demonstration der Suche in Collections ohne 
 * Implementierung von equals() in der eigenen Klasse
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SpielkarteExample
{
    public static void main(final String[] args)
    {
        final Collection<Spielkarte> spielkarten = new ArrayList<Spielkarte>();

        spielkarten.add(new Spielkarte(Farbe.HERZ, 7));
        // PIK 8 einfügen 
        spielkarten.add(new Spielkarte(Farbe.PIK, 8));
        spielkarten.add(new Spielkarte(Farbe.KARO, 9));

        // Finden wir eine PIK 8? 
        final boolean gefunden = spielkarten.contains(new Spielkarte(Farbe.PIK, 8));
        System.out.println("gefunden=" + gefunden);
    }
}
