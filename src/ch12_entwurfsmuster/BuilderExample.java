package ch12_entwurfsmuster;

import java.util.LinkedList;
import java.util.List;

/**
 * Beispielprogramm für das Erbauer-Muster:
 * Die Klasse PizzaBuilder bietet Methoden, um die gewünschte Pizza zu parametrieren.
 * Analog zu einer Bestellung also die Zutaten anzukreuzen.
 * Anschließend wird über einen Aufruf von create() ein Pizza-Objekt erzeugt.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class BuilderExample
{
    enum Size {
        SMALL, MEDIUM, LARGE
    };

    private BuilderExample()
    {
    }

    public static void main(final String[] args)
    {
        final PizzaBuilder builder = new PizzaBuilder();
        builder.mitExtraSardellen();
        System.out.println("Normale Pizza mit extra Sardellen: " + builder.create());

        final PizzaBuilder builder2 = new PizzaBuilder();
        builder2.mitSalami().small().bitteBeachten("Ohne Mais!");
        System.out.println("Kleine Salami-Pizza ohne Mais: " + builder2.create());
    }

    /**
     * alle optionalen Bestandteile des Produkts
     */
    public static final class PizzaBuilder
    {
        // Defaultwerte, gelten wenn keine korrespondierende Methode aufgerufen wird
        boolean mitSalami         = false;
        boolean mitExtraSardellen = false;
        String  info              = "";
        Size    size              = Size.MEDIUM;

        public PizzaBuilder mitSalami()
        {
            this.mitSalami = true;
            return this;
        }

        public PizzaBuilder mitExtraSardellen()
        {
            this.mitExtraSardellen = true;
            return this;
        }

        public PizzaBuilder small()
        {
            this.size = Size.SMALL;
            return this;
        }

        public PizzaBuilder bitteBeachten(final String info)
        {
            this.info = info;
            return this;
        }

        public Pizza create()
        {
            return new Pizza(this);
        }

        // ...

        public PizzaBuilder medium()
        {
            this.size = Size.MEDIUM;
            return this;
        }

        public PizzaBuilder large()
        {
            this.size = Size.LARGE;
            return this;
        }
    }

    public static final class Pizza
    {
        private static final int ANZAHL_SALAMI          = 5;
        private static final int ANZAHL_EXTRA_SARDELLEN = 7;
        List<Salami>             salami                 = null;
        List<Sardelle>           sardellen              = null;
        String                   info                   = null;
        Size                     size                   = null;

        Pizza(final PizzaBuilder buildOptions)
        {
            // Erzeuge eine boolesche Option
            salami = new LinkedList<Salami>();
            if (buildOptions.mitSalami)
            {
                for (int i = 0; i < ANZAHL_SALAMI; i++)
                {
                    salami.add(new Salami());
                }
            }

            // Erzeuge die gewünschte Anzahl an Sardellen
            sardellen = new LinkedList<Sardelle>();
            if (buildOptions.mitExtraSardellen)
            {
                for (int i = 0; i < ANZAHL_EXTRA_SARDELLEN; i++)
                {
                    sardellen.add(new Sardelle());
                }
            }

            // Normale Wertzuweisungen
            info = buildOptions.info;
            size = buildOptions.size;
        }

        @Override
        public String toString()
        {
            return this.getClass().getSimpleName() + " Attribute: " + createAttributeInfo("info", info) + " / "
                   + createAttributeInfo("size", size) + " / " + createAttributeInfo("Salami", salami) + " / "
                   + createAttributeInfo("Sardellen", sardellen);
        }

        // toString-Hilfe 
        private String createAttributeInfo(final String attributeName, final Object attribute)
        {
            return attributeName + ": '" + (attribute != null ? attribute : "-") + "'";
        }
    }

    // Options to choose
    public static final class Salami
    {
        @Override
        public String toString()
        {
            return this.getClass().getSimpleName();
        }
    }

    public static final class Sardelle
    {
        @Override
        public String toString()
        {
            return this.getClass().getSimpleName();
        }
    }
}
