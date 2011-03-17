package ch04_javagrundlagen;

/**
 * Beispielprogramm für den Einsatz innerer Klassen und Interfaces
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CalculatorInnerClassWithIFExample
{
    // Basisinterface 
    public interface ICalculator
    {
        public int calc(int val1, int val2);

        public String getName();
    }

    // Nur zur Demonstration von Vererbung 
    private static abstract class AbstractCalculator implements ICalculator
    {
        public String getName()
        {
            return getClass().getSimpleName();
        }
    }

    // Für alle Klassen zugreifbar 
    public static final class Plus extends AbstractCalculator
    {
        public int calc(int val1, int val2)
        {
            return val1 + val2;
        }
    }

    // Nur innerhalb des Packages und abgeleiteter Klassen zugreifbar   
    protected static final class Minus extends AbstractCalculator
    {
        public int calc(int val1, int val2)
        {
            return val1 - val2;
        }
    }

    // Extrem selten sinnvoll, nur innerhalb dieser Klasse zugreifbar 
    private static class Modulo extends AbstractCalculator
    {
        public int calc(int val1, int val2)
        {
            return val1 % val2;
        }
    }

    public static void main(String[] args)
    {
        final ICalculator[] calculators = { new Plus(), new Minus(), new Modulo() };

        for (final ICalculator calculator : calculators)
        {
            System.out.println("7 " + calculator.getName() + " 2 = " + calculator.calc(7, 2));
        }
    }
}
