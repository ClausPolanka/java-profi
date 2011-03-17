package ch06_applikationsbausteine;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * Diverse kleine Hilfsmethoden für die Dateiverarbeitung
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FileUtils
{
    public static final String XML_ENDING = ".xml";
    public static final String PDF_ENDING = ".pdf";
    public static final String BMP_ENDING = ".bmp";
    
    private FileUtils()
    {
        // no ctor
    }

    /**
     * this method ensures that the complete path for the passed file exist.
     * this means that any non existent directories are created.
     * <p>
     * if the passed file is a directory, this directory is created too
     * </p>
     * <p>
     * if it's a file, the direcories to the parent are created
     * </p>
     * 
     * @param destinationFilePath
     *            the file path to be created
     */
    public static void ensurePathExists(final String destinationFilePath)
    {
        if (destinationFilePath == null)
            throw new IllegalArgumentException("Passed File Path must not be null!");

        ensurePathExists(new File(destinationFilePath));
    }

    /**
     * this method ensures that the complete path for the passed file exist.
     * this means that any non existent directories are created.
     * <p>
     * if the passed file is a directory, this directory is created too
     * </p>
     * <p>
     * if it's a file, the direcories to the parent are created
     * </p>
     * 
     * @param destinationFile
     *            the file path to be created
     */
    public static void ensurePathExists(final File destinationFile)
    {
        if (destinationFile == null)
            throw new IllegalArgumentException("Passed File must not be null!");

        if (destinationFile.isDirectory())
        {
            destinationFile.mkdirs();
        }
        else
        {
            if (destinationFile.getParentFile() != null)
            {
                ensurePathExists(destinationFile.getParentFile());
            }
            else
            {
                // Aufruf mit Root-Ordner => nichts zu tun
            }
        }
    }

    /**
     * Diese Methode löscht ein Verzeichnis inklusive aller darin enthaltener
     * Verzeichnisse und Dateien.
     */
    public static final void deleteDir(final String aDirectory)
    {
        if (aDirectory == null)
            throw new IllegalArgumentException("Passed File Path must not be null!");

        delete(new File(aDirectory));
    }

    /**
     * Diese Methode löscht ein Verzeichnis inklusive aller darin enthaltener
     * Verzeichnisse und Dateien.
     */
    public static final void deleteDir(final File aDirectory)
    {
        delete(aDirectory);
    }

    private static final void delete(final File aFile)
    {
        if (aFile == null)
            throw new IllegalArgumentException("Passed File must not be null!");

        if (aFile.exists())
        {
            if (aFile.isDirectory())
            {
                final File[] content = getAllMatchingFiles(aFile, ACCEPT_ALL);
                for (int i = 0; i < content.length; i++)
                {
                    delete(content[i]);
                }
            }
            aFile.delete();
        }
    }

    // Sammlung praktischer FileFilter
    public static final java.io.FileFilter ACCEPT_ALL = new FileFilter()
                                                      {
                                                          @Override
                                                          public boolean accept(final File file)
                                                          {
                                                              return true;
                                                          }
                                                      };

    public static final java.io.FileFilter FILES_ONLY = new FileFilter()
                                                      {
                                                          @Override
                                                          public boolean accept(final File file)
                                                          {
                                                              return file.isFile();
                                                          }
                                                      };

    public static final java.io.FileFilter DIRS_ONLY  = new FileFilter()
                                                      {
                                                          @Override
                                                          public boolean accept(final File file)
                                                          {
                                                              return file.isDirectory();
                                                          }
                                                      };

    /**
     * Diese Methode stellt einen schmalen Wrapper um file.list() dar, damit
     * nicht null zurückgelifert wird!
     * 
     * Sammelt alle laut FileFilter passenden Files im ï¿½bergeben Verzeichnis
     * inputdir
     */
    public static File[] getAllMatchingFiles(final File inputdir, final java.io.FileFilter fileFilter)
    {
        final File[] files = inputdir.listFiles(fileFilter);

        // listFiles may return null
        if (files != null)
        {
            return files;
        }

        return new File[0];
    }

    /**
     * converts a windows file name into a ftp or unix / file name.
     * 
     * @param parentDirName
     *            the parentDirName to be converted
     * @return a windows file name converted into a ftp or unix / file name.
     */
    public static String convertToFtpName(final String parentDirName)
    {
        // convert to ftp name
        if (File.separatorChar != '/')
        {
            return parentDirName.replace(File.separatorChar, '/');
        }

        return parentDirName;
    }

    //

    /**
     * close an FileChannel and ignore IOException
     */
    public static void safeClose(final FileChannel fileChannel)
    {
        try
        {
            if (fileChannel != null)
                fileChannel.close();
        }
        catch (IOException e)
        {
            // ignore
        }
    }

    public static String appendFileExtensionIfNecessary(final String filename, final String fileExtension)
    {
        checkFileNameAndExtension(filename, fileExtension);

        if (filename.toLowerCase().endsWith(fileExtension.toLowerCase()))
            return filename;

        return filename + fileExtension;
    }

    public static String removeFileExtensionIfExisting(final String filename, final String fileExtension)
    {
        checkFileNameAndExtension(filename, fileExtension);

        if (filename.toLowerCase().endsWith(fileExtension.toLowerCase()))
            return filename.substring(0, filename.length() - fileExtension.length());

        return filename;
    }

    private static void checkFileNameAndExtension(final String filename, final String fileExtension)
    {
        if (filename == null || fileExtension == null)
            throw new IllegalArgumentException("Parameters 'filename' and 'fileExtension' must not be null!");
    }

    public static final void copy(final File fromFile, final File toFile) throws FileNotFoundException, IOException
    {
        if (fromFile == null)
            throw new IllegalArgumentException("parameter 'fromFile' must not be null!");

        if (toFile == null)
            throw new IllegalArgumentException("Passed 'toFile' must not be null!");

        if (!fromFile.isFile() || !fromFile.exists() || !fromFile.canRead())
            throw new IllegalArgumentException("Passed 'fromFile' must exist and be a readable file!");

        if (toFile.exists() && toFile.isFile() && !toFile.canWrite())
            throw new IllegalArgumentException("Passed 'toFile' must be a writable file!");

        InputStream is = null;
        OutputStream os = null;
        try
        {
            is = StreamUtils.decorateWithBuffer(new FileInputStream(fromFile));
            os = StreamUtils.decorateWithBuffer(new FileOutputStream(toFile));

            StreamUtils.copyBytewise(is, os);
        }
        finally
        {
            StreamUtils.safeClose(is);
            StreamUtils.safeClose(os);
        }
    }
}