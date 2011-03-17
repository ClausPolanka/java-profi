package ch10_badsmells;

/**
 * Beispiel für Berechnungen in case-Anweisungen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class BadCaseExample
{
    public static void main(String[] args)
    {
        final int byteValue = 7;

        int value = (int) (10 * Math.random());
        switch (value)
        {
            case 7:
            case 19 - 11: // 8
            case 17 - 8: // 9
            case 2 * byteValue - 4: // 10
                System.out.println(value + " accepted [7 - 10]");
                break;

            default:
                System.out.println(value + " is not in range 7 - 10");
        }
    }

    private BadCaseExample()
    {
    }
}
