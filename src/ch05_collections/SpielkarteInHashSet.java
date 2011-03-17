package ch05_collections;

import java.util.Collection;
import java.util.HashSet;

/**
 * Beispielprogramm zur Demonstration der Auswirkung von hashCode() beim Suchen.
 * 
 * Variante: nur equals() korrekt implementiert, hashCode() nicht überschrieben
 *   
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SpielkarteInHashSet
{
    public enum Farbe {
        KARO, HERZ, PIK, KREUZ
    };

    public static final class Spielkarte
    {
        private final Farbe farbe;
        private final int   wert;

        public Spielkarte(final Farbe farbe, final int wert)
        {
            this.farbe = farbe;
            this.wert = wert;
        }

        @Override
        public boolean equals(Object other)
        {
            if (other == null) // Null-Akzeptanz
                return false;

            if (this == other) // Reflexivitïät
                return true;

            if (this.getClass() != other.getClass()) // Typ-Gleichheit 
                return false;

            // Enum mit equals, int mit Wertevergleich
            final Spielkarte karte = (Spielkarte) other;
            return this.wert == karte.wert && this.farbe.equals(karte.farbe);
        }
    }

    public static void main(final String[] args)
    {
        final Collection<Spielkarte> spielkarten = new HashSet<Spielkarte>();

        spielkarten.add(new Spielkarte(Farbe.HERZ, 7));
        // PIK 8 einfügen 
        spielkarten.add(new Spielkarte(Farbe.PIK, 8));
        spielkarten.add(new Spielkarte(Farbe.KARO, 9));

        // Finden wir eine PIK 8?
        final boolean gefunden = spielkarten.contains(new Spielkarte(Farbe.PIK, 8));
        System.out.println("gefunden = " + gefunden);
    }
}