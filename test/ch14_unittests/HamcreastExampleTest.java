package ch14_unittests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import junit.framework.TestCase;

/**
 * Testklasse, die unseren eigenen IsEvenMatcher nutzt
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class HamcreastExampleTest extends TestCase
{
    public void testAgeJUnit()
    {
        final Person mike = new Person("Mike", 38);
        assertTrue("age", mike.age > 30 && mike.age % 2 != 0);
    }
    
    public void testAgeHamcreat()
    {
        final Person mike = new Person("Mike", 38);
        assertThat("age", mike.age, allOf(greaterThan(30), not(IsEvenNumber.evenNumber(mike.age))));
    }
}

class Person
{
    final String name;
    final int    age;


    public Person(final String name, final int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null) // null safe
            return false;

        if (this == other) // reflexive
            return true;

        if (!(other instanceof Person)) // compare only objects of same type
            return false;

        final Person otherPerson = (Person) other;
        return equalsImpl(otherPerson);
    }

    private boolean equalsImpl(final Person otherPerson)
    {
        return nullSafeEquals(this.name, otherPerson.name) && this.age == otherPerson.age;
    }

    private static boolean nullSafeEquals(final Object o1, final Object o2)
    {
        return (o1 == o2) || (o1 != null && o1.equals(o2));
    }
}
