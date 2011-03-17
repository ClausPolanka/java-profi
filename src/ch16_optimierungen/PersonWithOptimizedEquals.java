package ch16_optimierungen;

import ch04_javagrundlagen.EqualsUtils;

/**
 * Beispielklasse zur Modellierung einer Person
 * <br>
 * Diese Klasse enthält bewusst einige unsinnige Attribute, um Effekte
 * von Auto-Boxing bei Performance-Messungen zu demonstrieren:
 * Diese Implementierung vergleicht in equals() die Attriute mit == und vermeidet so
 * Auto-Boxing. 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
final class PersonWithOptimizedEquals
{
    private final String name;
    private Gender       gender;   
    private int          age;
    
    // Attribute nur zur Demo 
    private long         value1;
    private long         value2;
    private long         value3;

    PersonWithOptimizedEquals(final String name, final Gender male, final int age)
    {
        this.name = name;
        this.gender = male;
        this.age = age;
        // Werte nicht aus dem Cache
        this.value1 = 2000;
        this.value2 = 22000;
        this.value3 = 222000;
    }

    public boolean equals(Object other)
    {
        if (other == null) // null sicher
            return false;

        if (this == other) // reflexiv
            return true;

        if (this.getClass() != other.getClass()) // prüfe auf gleichen Typ
            return false;

        final PersonWithOptimizedEquals otherPerson = (PersonWithOptimizedEquals) other;
        return equalsImpl(otherPerson);
    }

    private boolean equalsImpl(final PersonWithOptimizedEquals otherPerson)
    {
        return 
               this.age == otherPerson.age &&
               this.value1 == otherPerson.value1 &&
               this.value2 == otherPerson.value2 &&
               this.value3 == otherPerson.value3 &&               
               EqualsUtils.nullSafeEquals(this.name, otherPerson.name)  && 
               EqualsUtils.nullSafeEquals(this.gender, otherPerson.gender);
    }

    public final String getName()
    {
        return name;
    }

    public final Gender getGender()
    {
        return gender;
    }
}
