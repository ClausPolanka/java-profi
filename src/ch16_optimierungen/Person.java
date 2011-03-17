package ch16_optimierungen;

import ch04_javagrundlagen.EqualsUtils;

enum Gender {
    MALE, FEMALE
};

/**
 * Beispielklasse zur Modellierung einer Person
 * <br>
 * Diese Klasse enthält bewusst einige unsinnige Attribute, um Effekte
 * von Auto-Boxing bei Performance-Messungen zu demonstrieren:
 * Dazu werden hier in equals() die Attriute nicht mit == verglichen, sondern
 * mithilfe der Methode nullSafeEquals(). Diese führt für die primitiven Typen
 * ein Auto-Boxing durch. 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
final class Person
{
    private final String name;
    private Gender       gender;
    private int          age;

    // Attribute zur Demo für Auto-Boxing
    private long         value1;
    private long         value2;
    private long         value3;

    Person(final String name, final Gender gender, final int age)
    {
        this.name = name;
        this.gender = gender;
        this.age = age;
        // Werte nicht aus dem Auto-Boxing-Long-Cache
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

        final Person otherPerson = (Person) other;
        return equalsImpl(otherPerson);
    }

    private boolean equalsImpl(final Person otherPerson)
    {
        return EqualsUtils.nullSafeEquals(this.age, otherPerson.age)
               && EqualsUtils.nullSafeEquals(this.value1, otherPerson.value1)
               && EqualsUtils.nullSafeEquals(this.value2, otherPerson.value2)
               && EqualsUtils.nullSafeEquals(this.value3, otherPerson.value3)              
               // Unterschiede bewusst am Ende vergleichen, um die anderen Vergleiche und damit
               // Auto-Boxing durchzuführen
               && EqualsUtils.nullSafeEquals(this.name, otherPerson.name)
               && EqualsUtils.nullSafeEquals(this.gender, otherPerson.gender);
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
