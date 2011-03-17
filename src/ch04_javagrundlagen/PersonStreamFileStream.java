package ch04_javagrundlagen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Beispielprogramm zur Verarbeitung von Person-Objekten mit
 * einfachen File-Streams
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PersonStreamFileStream
{    
    public static void main(String[] args) throws Exception
    {
        final File file = new File("Person.ser1");
        final String filepath = file.getAbsolutePath();
        final Person max = new Person("Max", new Date(), "Hamburg");

        // Speichere Person 'Max' in einer Datei 
        System.out.println("Writing Person " + max + " to file '" + filepath + "'");
        final FileOutputStream fos = new FileOutputStream(file);
        writePersonToStream(max, fos);

        // Person aus der Datei einlesen 
        final FileInputStream fis = new FileInputStream(file);
        final Person newPerson = readPersonFromStream(fis);
        System.out.println("Person " + newPerson + " from file '" + filepath + "'");

        // Prüfe auf inhaltliche Gleichheit
        System.out.println("Gleich? " + max.equals(newPerson));
    }

    public static void writePersonToStream(final Person person, final OutputStream os) throws IOException
    {
        // String => getBytes()         
        final byte[] nameBytes = person.getName().getBytes();
        final byte[] cityBytes = person.getCity().getBytes();

        // Date => long => String => getBytes()      
        final long time = person.getBirthday().getTime();
        final String timeString = Long.toString(time);
        final byte[] timeBytes = timeString.getBytes();

        // Schreibe Attribute in den Stream 
        writeByteArray(os, nameBytes);
        writeByteArray(os, cityBytes);
        writeByteArray(os, timeBytes);
    }

    public static void writeByteArray(final OutputStream os, final byte[] bytesToWrite) throws IOException
    {
        os.write(bytesToWrite.length);
        os.write(' '); // Überspringe Leerfeld 
        os.write(bytesToWrite);
    }

    private static Person readPersonFromStream(final InputStream is) throws FileNotFoundException, IOException
    {
        final byte[] nameBytes = readByteArray(is);
        final String name = new String(nameBytes);

        final byte[] cityBytes = readByteArray(is);
        final String city = new String(cityBytes);

        final byte[] birthdayBytes = readByteArray(is);
        final long time = Long.parseLong(new String(birthdayBytes));
        final Date birthday = new Date(time);

        // Trick: immer erst alle Daten vor der Konstruktion einlesen 
        return new Person(name, birthday, city);
    }

    private static byte[] readByteArray(final InputStream is) throws IOException
    {
        final int nameBytesLength = is.read();
        is.read(); // Überspringe Leerfeld 
        final byte[] nameBytes = new byte[nameBytesLength];
        is.read(nameBytes);
        return nameBytes;
    }

    public static final class Person
    {
        private final String name;
        private final Date   birthday;
        private final String city;

        public Person(final String name, final Date birthday, final String city)
        {
            if (name == null || birthday == null || city == null)
                throw new IllegalArgumentException("parameters 'name', 'birthday' and 'city' must not be null!");

            this.name = name;
            this.birthday = birthday;
            this.city = city;
        }

        public final String getName()
        {
            return name;
        }

        public final Date getBirthday()
        {
            return birthday;
        }

        public final String getCity()
        {
            return city;
        }

        public boolean equals(Object other)
        {
            if (other == null) // Null-Akzeptanz
                return false;

            if (this == other) // reflexive
                return true;

            if (this.getClass() != other.getClass()) // same type ?
                return false;

            final Person otherPerson = (Person) other;
            return this.getName().equals(otherPerson.getName()) && this.getCity().equals(otherPerson.getCity())
                   && this.getBirthday().equals(otherPerson.getBirthday());
        }

        public String toString()
        {
            final StringBuffer buf = new StringBuffer();

            buf.append("Person: ");

            buf.append("Name='");
            buf.append(getName());
            buf.append("' ");
            buf.append("City='");
            buf.append(getCity());
            buf.append("' ");
            buf.append("Birthday='");
            buf.append(getBirthday());
            buf.append("'");

            return buf.toString();
        }
    }
}
