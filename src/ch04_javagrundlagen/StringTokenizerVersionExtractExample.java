package ch04_javagrundlagen;

import java.util.StringTokenizer;

/**
 * Beispielprogramm zur Demonstration eines StringTokenizers
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringTokenizerVersionExtractExample
{
    public static void main(String[] args)
    {
        final String version = "APPNAME-2.14.77 build(07-07-07 11:11)";
        
        final int majorVersion = extractMajorVersion(version);
        System.out.println("Major: " + majorVersion);

        final int minorVersion = extractMinorVersion(version);
        System.out.println("Minor: " + minorVersion);

        final int patchLevel = extractPatchLevel(version);
        System.out.println("Patch-Level: " + patchLevel);
    }

    /**
     * extracts the major version from a passed version string. <br>
     * <p>
     * Example: When passing in "APPNAME-2.14.77 build(05-03-07 10:06)" The
     * returend major version is 2.
     * </p>
     * 
     * @return the major software version of the passed version string, -1 if no
     *         such version could be retrieved
     */
    public static int extractMajorVersion(final String strImplementationVersion)
    {
        final String versions = cutOffAppnameAndBuild(strImplementationVersion);

        final StringTokenizer tokenizer = new StringTokenizer(versions, "._-");
        if (tokenizer.hasMoreTokens())
        {
            final String versionValue = tokenizer.nextToken();
            return parseValue(versionValue);
        }
        return -1;
    }

    /**
     * extracts the minor version from a passed version string. <br>
     * <p>
     * Example: When passing in "APPNAME-2.14.77 build(05-03-07 10:06:06)" The
     * returend major version is 14.
     * </p>
     * 
     * @return the minor software version of the passed version string, -1 if no
     *         such version could be retrieved
     */
    public static int extractMinorVersion(final String strImplementationVersion)
    {
        final String versions = cutOffAppnameAndBuild(strImplementationVersion);

        final StringTokenizer tokenizer = new StringTokenizer(versions, "._-");
        if (tokenizer.countTokens() > 1)
        {
            // skip major version
            tokenizer.nextToken();
            final String versionValue = tokenizer.nextToken();
            return parseValue(versionValue);
        }
        return -1;
    }

    /**
     * extracts the minor version from a passed version string. <br>
     * <p>
     * Example: When passing in "APPNAME-2.14.77 build(05-03-07 10:06)" The
     * returend major version is 77.
     * </p>
     * 
     * @return the minor software version of the passed version string, -1 if no
     *         such version could be retrieved
     */
    public static int extractPatchLevel(final String strImplementationVersion)
    {
        final String versions = cutOffAppnameAndBuild(strImplementationVersion);

        final StringTokenizer tokenizer = new StringTokenizer(versions, "._-");
        if (tokenizer.countTokens() > 2)
        {
            // skip major and minor version
            tokenizer.nextToken();
            tokenizer.nextToken();
            final String versionValue = tokenizer.nextToken();
            return parseValue(versionValue);
        }
        return -1;
    }

    /** 
     * wandelt den String in einen Int-Wert, falls nicht möglich, wird -1 zurückgegeben 
     */
    private static int parseValue(final String versionValue)
    {
        try
        {
            return Integer.parseInt(versionValue.trim());
        }
        catch (final NumberFormatException e)
        {
            return -1;
        }
    }

    /** 
     * schneidet alles bis zum ersten Minus ab und vor den build-Infos ab 
     */
    private static String cutOffAppnameAndBuild(String strImplementationVersion)
    {
        final int posFirstMinus = strImplementationVersion.indexOf("-");

        String result = strImplementationVersion;
        if (foundOccurence(posFirstMinus))
        {
            final String versionWithoutAppname = strImplementationVersion.substring(posFirstMinus + 1);
            result = versionWithoutAppname;
        }

        final int posBuild = result.indexOf("build");
        if (foundOccurence(posBuild))
        {
            result = result.substring(0, posBuild);
        }

        return result;
    }

    private static boolean foundOccurence(final int posFirstMinus)
    {
        return posFirstMinus != -1;
    }
    
    private StringTokenizerVersionExtractExample()
    {
    }
}
