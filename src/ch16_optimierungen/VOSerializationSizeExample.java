package ch16_optimierungen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ch06_applikationsbausteine.StreamUtils;

/**
 * Beispielprogramm für den Einfluss von primitiven Attributen auf die Größe der
 * serialisierten Daten, die sich aus der eigentlichen Größe der Attribute sowie
 * der zusätzlichen Informationen (Klassennamen usw.) für die Serialisierung ergeben
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class VOSerializationSizeExample
{
    public static void main(String[] args) throws Exception
    {
        final BadVO badVO = new BadVO();
        final File badVOFile = new File("BadVo.ser");
        writeObject(badVO, badVOFile);
        System.out.println("File '" + badVOFile.getName() + "' / size = " + badVOFile.length());
        
        final GoodVO goodVO = new GoodVO();
        final File goodVOFile = new File("GoodVO.ser");
        writeObject(goodVO, goodVOFile);
        System.out.println("File '" + goodVOFile.getName() + "' / size = " + goodVOFile.length());
    }

    private static void writeObject(final Object objToWrite, final File file) throws IOException, FileNotFoundException
    {
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(objToWrite);
        oos.flush();
        StreamUtils.safeClose(oos);        
    }
    
    // class Size = 5 * 16 = 80 + 80 + 200 = 360
    public static class BadVO implements Serializable
    {
        //  5 * 16 byte
        Long    id     = 4711L;
        Integer value1 = 1;
        Integer value2 = 2;
        Integer value3 = 3;
        Integer value4 = 4;

        //  5 * 16 byte
        Double  value5 = 5d;
        Double  value6 = 6d;
        Double  value7 = 7d;
        Double  value8 = 8d;
        Double  value9 = 9d;

        //  5 * 40 byte
        String  str1   = "Test1";
        String  str2   = "Test2";
        String  str3   = "Test3";
        String  str4   = "Test4";
        String  str5   = "Test5";
    }

    // class Size = 8 + 4 * 4 = 24 + 40 + 200 = 264
    public static class GoodVO implements Serializable
    {
        //  8 + 4 * 4
        long   id     = 4711;
        int    value1 = 1;
        int    value2 = 2;
        int    value3 = 3;
        int    value4 = 4;

        //  5 * 8 byte
        double value5 = 5d;
        double value6 = 6d;
        double value7 = 7d;
        double value8 = 8d;
        double value9 = 9d;

        //  5 * 40 byte
        String str1   = "Test1";
        String str2   = "Test2";
        String str3   = "Test3";
        String str4   = "Test4";
        String str5   = "Test5";
    }    
}
