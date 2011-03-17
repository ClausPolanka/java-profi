package ch03_oodesign;

/**
 * Beispiel für den Einsatz der Klasse CounterWithOverflow (z. B. für Spieleapplikation)
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CounterWithOverflowExample
{
    public static void main(String[] args)
    {
        final CounterWithOverflow points = new CounterWithOverflow();

        for (int i = 0; i < 2010; i++)
            points.increment();

        System.out.println("Points: " + points.currentValue());
        System.out.println("Bonus-Lifes: " + points.overflowCount());
    }
    
    private CounterWithOverflowExample()
    {        
    }
}