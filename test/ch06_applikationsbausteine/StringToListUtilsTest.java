package ch06_applikationsbausteine;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ch06_applikationsbausteine.StringToListUtils;

/**
 * Testklasse für die Klasse StringToListUtils, basierend auf JUnit 4
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringToListUtilsTest
{
    private final List EXPECTED_SPLIT_ALL = Arrays.asList("Split1", "Split2");
    private final List EXPECTED_TESTINPUT = Arrays.asList("Test");

    @Test
    public void testNormalLineEnding()
    {
        final byte[] input = "Test\n".getBytes();
        final List list = StringToListUtils.convertByteArrayToStringList(input);

        assertEquals("terminated with '\\n'", EXPECTED_TESTINPUT, list);
    }

    @Test
    public void testMissingLineEnding()
    {
        final byte[] input = "Test".getBytes();
        final List list = StringToListUtils.convertByteArrayToStringList(input);

        assertEquals("NOT terminated with '\\n'", EXPECTED_TESTINPUT, list);
    }

    @Test
    public void testSplitting()
    {
        final byte[] input = "Split1\nSplit2\n".getBytes();
        final List list = StringToListUtils.convertByteArrayToStringList(input);

        assertEquals("splitting", EXPECTED_SPLIT_ALL, list);
    }
}