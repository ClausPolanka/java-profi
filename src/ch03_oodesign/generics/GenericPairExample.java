package ch03_oodesign.generics;

import java.util.Date;

/**
 * Beispielprogramm, das die generische Pair-Klasse nutzt
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class GenericPairExample
{
    public static void main(String[] args)
    {
        final Person wizard = new Person("Wizard", new Date(), "Kiel");
        final Person mike = new Person("Mike", new Date(), "Bremen");

        final Pair<String, Person> pair1 = new Pair<String, Person>("Dark", wizard);
        final Pair<String, Person> pair2 = new Pair<String, Person>("Iron", mike);

        System.out.println(pair1);
        System.out.println(pair2);
    }
    
    private GenericPairExample()
    {        
    }
}
