package ch04_javagrundlagen;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * Beispielprogramm für das Filtern von Verzeichnisinhalten mihilfe von 
 * FileFilter- und FilenameFilter-Objekten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FileFilterExample
{
    public static final String XML_ENDING = ".xml";
    public static final String PDF_ENDING = ".pdf";

    public static class XmlFilenameFilter implements FilenameFilter
    {
        @Override
        public boolean accept(final File dir, final String fileName)
        {
            return fileName.toLowerCase().endsWith(XML_ENDING);
        }
    }

    public static class PdfFileFilter implements FileFilter
    {
        @Override
        public boolean accept(final File pathname)
        {
            return pathname.getName().toLowerCase().endsWith(PDF_ENDING);
        }
    }

    public static void main(String[] args)
    {
        final File dir = new File("config/listFilesExampleDir");

        final String[] contents = dir.list();
        final String[] xmlContents = dir.list(new XmlFilenameFilter());
        final File[] pdfContents = dir.listFiles(new PdfFileFilter());

        System.out.println("Contents: " + Arrays.toString(contents));
        // Im Ergebnis ist der Pfad NICHT enthalten    
        System.out.println("XML-Files: " + Arrays.toString(xmlContents));
        // Im Ergebnis ist der Pfad enthalten 
        System.out.println("PDF-Files: " + Arrays.toString(pdfContents));
    }
}
