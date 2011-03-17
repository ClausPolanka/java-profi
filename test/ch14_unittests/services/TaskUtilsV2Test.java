package ch14_unittests.services;

import ch14_unittests.services.TaskCompareResults;
import junit.framework.TestCase;
import ch14_unittests.services.TaskUtilsV2;

/**
 * Testklasse für die Klasse TaskUtilsV2, basierend auf JUnit 3
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class TaskUtilsV2Test extends TestCase
{
    public void testCompareTaskDataNewMustNotBeLonger()
    {
        final String newTaskData = "abcde";
        final String existingTaskData = "abcd";

        final TaskCompareResults value = TaskUtilsV2.compareTaskData(newTaskData, existingTaskData);
        assertFalse(TaskCompareResults.isOkValue(value));
    }

    public void testCompareTaskSameLengthAndSameContent()
    {
        final String newTaskData = "abcd";
        final String existingTaskData = "abcd";

        final TaskCompareResults value = TaskUtilsV2.compareTaskData(newTaskData, existingTaskData);
        assertTrue(TaskCompareResults.isOkValue(value));
    }

    public void testCompareTaskSameLengthAndDifferentContent()
    {
        final String newTaskData = "abcd";
        final String existingTaskData = "abcX";

        final TaskCompareResults value = TaskUtilsV2.compareTaskData(newTaskData, existingTaskData);
        assertFalse(TaskCompareResults.isOkValue(value));
    }

    // ...

    // spezielle Tests aus der Realität
    // ################################
    public void testCompareTaskDataWithHistory()
    {
        final String newTaskData = "6#46#0#0#50#996#19#12252011";
        // Optionale History sollen beim Vergleich nicht betrachtet werden 
        final String existingTaskData = "6#46#0#0#50#996#19#12252011#1#490#1";

        final TaskCompareResults value = TaskUtilsV2.compareTaskData(newTaskData, existingTaskData);
        assertTrue(TaskCompareResults.isOkValue(value));
    }
}
