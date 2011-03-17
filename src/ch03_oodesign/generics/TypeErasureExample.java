package ch03_oodesign.generics;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration der Auswirkungen der Type Erasure
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class TypeErasureExample
{
    public static void main(String[] args)
    {
        final String[] nicknames = { "Dragonman", "Iron Mike", "Lordmaster" };
        final Person[] bowlingPeople = { new Person("Sven", new Date(), "Kiel"),
                        new Person("Michael", new Date(), "Bremen"), new Person("Andreas", new Date(), "Kiel") };

        // Compile-Error: Incompatible operand types Class<String[]> and Class<Person[]>
        // final boolean sameType1 = (String[].class == Person[].class);
        // final boolean sameType2 = (nicknames.getClass() == bowlingPeople.getClass());
        final boolean sameType3 = (nicknames.getClass().equals(bowlingPeople.getClass()));
        System.out.println(sameType3); // false

        final List<String> nicknamesList = Arrays.asList(nicknames);
        final List<Person> bowlingPeopleList = Arrays.asList(bowlingPeople);

        // ACHTUNG: Compile-Error 
        // final boolean sameType1 = (List<String>.class == List<Person>.class);        
        final boolean sameType2 = (nicknamesList.getClass() == bowlingPeopleList.getClass());
        System.out.println(sameType2); // true 
    }

    private TypeErasureExample()
    {
    }
}
