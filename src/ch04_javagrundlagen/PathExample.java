package ch04_javagrundlagen;

import java.io.File;

/**
 * Beispielprogramm für Pfadaktionen von File-Objekten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PathExample
{
    public static void main(String[] args)
    {
        final String nameOfDirectory1 = "C:\\toBeChecked";
        final String nameOfDirectory2 = "C:\\toBeChecked\\./../toBeChecked/";

        final File directory1 = new File(nameOfDirectory1);
        final File directory2 = new File(nameOfDirectory2);

        printFileInfo(directory1);
        printFileInfo(directory2);
    }

    private static void printFileInfo(final File file)
    {
        System.out.println("Name='" + file.getName() + " / " + "Path='" + file.getAbsolutePath() + "'");
    }

    private PathExample()
    {
    }
}