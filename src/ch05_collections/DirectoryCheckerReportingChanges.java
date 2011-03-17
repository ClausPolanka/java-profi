package ch05_collections;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ch04_javagrundlagen.DirectoryObserver;

/**
 * Beispielprogramm zur Demonstration der Überwachung eines Verzeichnisses.
 * Wir nutzen Mengen, um die hinzugefügten bzw. entfernten Dateien zu ermitteln. 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class DirectoryCheckerReportingChanges extends DirectoryObserver
{
    private final Set<String> savedContent = new TreeSet<String>();

    public DirectoryCheckerReportingChanges(final String nameOfDirectoryToCheck) throws IOException
    {
        super(nameOfDirectoryToCheck);
    }

    @Override
    protected int checkForContentsChanged(final int numOfFiles)
    {
        final String[] content = getContents(getDirectoryToCheck());
        final List<String> contentAsList = Arrays.asList(content);
        final Set<String> contentAsSet = new TreeSet<String>(contentAsList);

        // Neu = Differenzmenge Aktuell - Vorher 
        final Set<String> newContent = new TreeSet<String>(contentAsSet);
        newContent.removeAll(savedContent);

        // Gelöscht = Differenzmenge Vorher - Aktuell 
        final Set<String> oldContent = new TreeSet<String>(savedContent);
        oldContent.removeAll(contentAsSet);

        // Unverändert = Schnittmenge Vorher und Aktuell 
        final Set<String> unchangedContent = new TreeSet<String>(savedContent);
        unchangedContent.retainAll(contentAsSet);

        if (newContent.size() > 0 || oldContent.size() > 0)
        {
            onContentsChanged(contentAsSet.size(), savedContent.size());            
            onFilesAdded(newContent);
            onFilesRemoved(oldContent);
        }

        savedContent.clear();
        savedContent.addAll(contentAsSet);

        return savedContent.size();
    }

    protected void onFilesAdded(final Set<String> newContent)
    {
        System.out.println("addedFiles = '" + newContent +"'");
    }
    
    protected void onFilesRemoved(final Set<String> removedContent)
    {
        System.out.println("removedFiles = '" + removedContent +"'");
    }

    public static void main(String[] args) throws IOException
    {
        final String nameOfDirectoryToCheck = "c:\\toBeChecked";

        new DirectoryCheckerReportingChanges(nameOfDirectoryToCheck).checkDirectory();
    }
}