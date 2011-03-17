package ch06_applikationsbausteine;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Testklasse für die Klasse StringToListUtils3, basierend auf JUnit 4
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringToListUtils3Test
{
    private final List<String> EXPECTED_SPLIT_ALL = Arrays.asList("Split1", "Split2");

    private final List<String> EXPECTED_TESTINPUT = Arrays.asList("Test");

    @Test
    public void testNormalLineEnding()
    {
        final byte[] input = "Test\n".getBytes();
        final List<String> result = StringToListUtils3.convertByteArrayToStringList(input);

        assertEquals("terminated with '\\n'", EXPECTED_TESTINPUT, result);
    }

    @Test
    public void testMissingLineEnding()
    {
        final byte[] input = "Test".getBytes();
        final List<String> result = StringToListUtils3.convertByteArrayToStringList(input);

        assertEquals("NOT terminated with '\\n'", EXPECTED_TESTINPUT, result);
    }

    @Test
    public void testSplitting()
    {
        final byte[] input = "Split1\nSplit2\n".getBytes();
        final List<String> result = StringToListUtils3.convertByteArrayToStringList(input);

        assertEquals("splitting", EXPECTED_SPLIT_ALL, result);
    }
}