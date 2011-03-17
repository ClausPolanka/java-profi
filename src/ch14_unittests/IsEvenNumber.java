package ch14_unittests;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Beispielimplementierung eines Hamcrest-Matchers für gerade Zahlen  
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class IsEvenNumber extends TypeSafeMatcher<Number>
{
    @Override
    public boolean matchesSafely(final Number number)
    {
        return number.intValue() % 2 == 0;
    }

    public void describeTo(final Description description)
    {
        description.appendText("even number");
    }

    @Factory
    public static <T> Matcher<Number> evenNumber(final Number number)
    {
        return new IsEvenNumber();
    }
}
