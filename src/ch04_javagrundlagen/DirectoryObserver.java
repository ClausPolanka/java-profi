package ch04_javagrundlagen;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ch07_multithreading.SleepUtils;

/**
 * Beispielprogramm für die Überwachung eines Verzeichnisses:
 * Meldet, wenn Dateien hinzugefügt oder gelöscht werden
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class DirectoryObserver
{
    protected static final int DEFAULT_CHECK_INTERVAL_IN_SEC = 5;

    private final int          checkIntervalInSec;
    private final File         directoryToCheck;

    public DirectoryObserver(final String nameOfDirectoryToCheck) throws IOException
    {
        this(nameOfDirectoryToCheck, DEFAULT_CHECK_INTERVAL_IN_SEC);
    }

    public DirectoryObserver(final String nameOfDirectoryToCheck, 
                             final int checkIntervalInSec) throws IOException
    {
        this.checkIntervalInSec = checkIntervalInSec;

        directoryToCheck = new File(nameOfDirectoryToCheck);
    }

    public void checkDirectory()
    {
        System.out.println("starting directory check");
        int numOfFiles = getContents(directoryToCheck).length;

        while (!Thread.currentThread().isInterrupted())
        {
            System.out.println("checkDirectory... '" + directoryToCheck + "'");
            if (directoryExists(directoryToCheck))
            {
                numOfFiles = checkForContentsChanged(numOfFiles);
            }
            else
            {
                onDirectoryNotExisting();
                numOfFiles = 0;
            }
            SleepUtils.safeSleep(TimeUnit.SECONDS, checkIntervalInSec);
            System.out.println("...checkDirectory");
        }
    }

    protected int checkForContentsChanged(final int numOfFiles)
    {
        final String[] currentContents = getContents(directoryToCheck);

        if (currentContents.length != numOfFiles)
        {
            onContentsChanged(currentContents.length, numOfFiles);
        }
        return currentContents.length;
    }

    // Callback-Methode 
    protected void onContentsChanged(final int newFileCount, 
                                     final int oldFileCount)
    {
        System.out.println("new FileCount=" + newFileCount + " / " + 
                           "old FileCount=" + oldFileCount);
    }

    // Callback-Methode  
    protected void onDirectoryNotExisting()
    {
        System.out.println("missing directory='" + directoryToCheck + "'");
    }

    protected final int getCheckIntervalInSec()
    {
        return checkIntervalInSec;
    }

    public static void main(String[] args) throws IOException
    {
        final String nameOfDirectoryToCheck = "C:\\toBeChecked";
    
        final DirectoryObserver directoryObserver = new DirectoryObserver(nameOfDirectoryToCheck);
        directoryObserver.checkDirectory();
    }    
    //...
    
    // FileUtils:

    public static String[] getContents(final File directoryToCheck)
    {
        if (directoryExists(directoryToCheck))
        {
            final String[] contents = directoryToCheck.list();

            if (contents != null)
            {
                return contents;
            }
        }

        return new String[0];
    }

    public static boolean directoryExists(final File directoryToCheck)
    {
        if (directoryToCheck == null)
            return false;

        return directoryToCheck.exists() && directoryToCheck.isDirectory();
    }

    protected final File getDirectoryToCheck()
    {
        return directoryToCheck;
    }
}