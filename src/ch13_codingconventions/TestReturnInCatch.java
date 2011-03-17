package ch13_codingconventions;

/**
 * Beispielprogramm zur Verdeutlichung einer return-Anweisung in catch und finally
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class TestReturnInCatch
{
    public static String test()
    {
        try
        {
            throw new IllegalStateException("Exception simulieren!");
        }
        catch (final IllegalStateException ex)
        {
            return "Im Fehlerfall";
        }
        finally
        {
            return "Tatsächlich";
        }
    }

    public static void main(String[] argv)
    {
        System.out.println(test());
    }
    
    private TestReturnInCatch()
    {        
    }
}