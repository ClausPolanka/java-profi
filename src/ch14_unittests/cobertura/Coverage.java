package ch14_unittests.cobertura;

/**
 * Beispielklasse, deren Tests 100% Abdeckung erreichen, es aber trotzdem zu einer 
 * NullPointerException kommt
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class Coverage
{
    int    value   = 0;
    String message = null;

    Coverage(final int initalvalue, final String initialMessage)
    {
        this.value = initalvalue;
        this.message = initialMessage;
    }

    public void divide(final int divisor)
    {
        // Bewusst falsche Implemetierung 
        if (divisor == 0)
            this.value = 1;
        else
            this.value /= divisor;
    }

    public String coverage100ButNPE(final boolean condition)
    {
        String value = null;
        if (condition)
        {
            value = String.valueOf(condition);
        }
        return value.trim();
    }
}
