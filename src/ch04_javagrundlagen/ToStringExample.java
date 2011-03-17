package ch04_javagrundlagen;

import java.util.Date;

/**
 * Beispielprogramm zur Demonstration von Ausgaben mit toString()
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ToStringExample
{
    public static void main(String[] args)
    {
        final Object obj = new Object();
        final Person tom = new Person("Tom", new Date(), "Hamburg");
        final Object[] test = new Object[] { obj, tom };

        System.out.println("Object: " + obj);
        System.out.println("Person: " + tom);
        System.out.println("Object[]: " + test);
    }
    
    private ToStringExample()
    {
    }
}