package ch08_advancedjava.serializable;

import java.awt.Color;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import ch04_javagrundlagen.EqualsUtils;

/**
 * Beispielklasse zur Modellierung einer Person
 * <br>
 * Demonstraton des Externalizable-Interfaces
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonExternalizable implements Externalizable
{
    private String name;
    private String city;
    private Date   birthday;
    private Color  eyeColor;

    public PersonExternalizable()
    {
    }

    public PersonExternalizable(final String name, final String city, final Date birthday, final Color eyeColor)
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

    @Override
    public void writeExternal(final ObjectOutput objectOut) throws IOException
    {
        objectOut.writeUTF(name);
        objectOut.writeUTF(city);

        objectOut.writeLong(birthday.getTime());

        objectOut.writeInt(eyeColor.getRed());
        objectOut.writeInt(eyeColor.getGreen());
        objectOut.writeInt(eyeColor.getBlue());
    }

    @Override
    public void readExternal(final ObjectInput objectIn) throws IOException, ClassNotFoundException
    {
        name = objectIn.readUTF();
        city = objectIn.readUTF();

        final long time = objectIn.readLong();
        birthday = new Date(time);

        final int red = objectIn.readInt();
        final int green = objectIn.readInt();
        final int blue = objectIn.readInt();
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

        final PersonExternalizable otherPerson = (PersonExternalizable) other;
        return equalsImpl(otherPerson);
    }

    private boolean equalsImpl(final PersonExternalizable otherPerson)
    {
        return EqualsUtils.nullSafeEquals(this.getName(), otherPerson.getName())
               && EqualsUtils.nullSafeEquals(this.getCity(), otherPerson.getCity())
               && EqualsUtils.nullSafeEquals(this.getBirthDay(), otherPerson.getBirthDay())
               && EqualsUtils.nullSafeEquals(this.getEyeColor(), otherPerson.getEyeColor());
    }
}