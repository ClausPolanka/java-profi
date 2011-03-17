package ch08_advancedjava.serializable;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import ch04_javagrundlagen.EqualsUtils;

/**
 * Beispielklasse zur Modellierung einer Person
 * <br>
 * Demonstration einer Versionsverwaltung über die serialVersionUID
 *  
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonWithEyeColorV2 implements Serializable
{
    private String name;

    private String city;

    private Date   birthday;

    private Color  eyeColor;

    public PersonWithEyeColorV2(final String name, final String city, final Date birthday, final Color eyeColor)
    {
        this.name = name;
        this.city = city;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\n" + "[name=" + name + ", city=" + city + ", birthday=" + birthday + ", eyeColor="
               + eyeColor + "]";
    }

    private void writeObject(final ObjectOutputStream outStream) throws IOException
    {
        outStream.defaultWriteObject();

        outStream.writeLong(birthday.getTime());

        outStream.writeInt(eyeColor.getRed());
        outStream.writeInt(eyeColor.getGreen());
        outStream.writeInt(eyeColor.getBlue());
    }

    private void readObject(final ObjectInputStream inStream) throws IOException, ClassNotFoundException
    {
        inStream.defaultReadObject();

        final long time = inStream.readLong();
        birthday = new Date(time);

        final int red = inStream.readInt();
        final int green = inStream.readInt();
        final int blue = inStream.readInt();
        eyeColor = new Color(red, green, blue);
    }

    // ...

    public final String getName()
    {
        return name;
    }

    public final String getCity()
    {
        return city;
    }

    public final Date getBirthDay()
    {
        return birthday;
    }

    public final Color getEyeColor()
    {
        return eyeColor;
    }

    public boolean equals(final Object other)
    {
        if (other == null) // null safe
            return false;

        if (this == other) // reflexive
            return true;

        if (this.getClass() != other.getClass())
            return false;

        final PersonWithEyeColorV2 otherPerson = (PersonWithEyeColorV2) other;
        return equalsImpl(otherPerson);
    }

    private boolean equalsImpl(final PersonWithEyeColorV2 otherPerson)
    {
        return EqualsUtils.nullSafeEquals(this.getName(), otherPerson.getName())
               && EqualsUtils.nullSafeEquals(this.getCity(), otherPerson.getCity())
               && EqualsUtils.nullSafeEquals(this.getBirthDay(), otherPerson.getBirthDay())
               && EqualsUtils.nullSafeEquals(this.getEyeColor(), otherPerson.getEyeColor());
    }
}