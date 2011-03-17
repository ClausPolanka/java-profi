package ch04_javagrundlagen;

/**
 * Beispiel zur Demonstration der Berechnungsungenauigkeiten von Flieﬂkommazahlen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FloatUngenauigkeit
{
    public static void main(final String[] args)
    {
        float sum = 0.0f;
        for (int i = 0; i < 10; i++)
            sum += 0.1;

        System.out.println("1 != " + sum); // 1 != 1.0000001
    }

    private FloatUngenauigkeit()
    {
    }
}
