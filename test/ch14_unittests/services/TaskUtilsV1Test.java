package ch14_unittests.services;

import ch14_unittests.services.TaskCompareResults;
import junit.framework.TestCase;
import ch14_unittests.services.TaskUtilsV1;
import ch14_unittests.services.tasks.ITask;
import ch14_unittests.services.tasks.SimpleTask;

/**
 * Testklasse für die Klasse TaskUtilsV1, basierend auf JUnit 3
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class TaskUtilsV1Test extends TestCase
{
    public void testCompareTaskDataWithExampleTask()
    {
        final long id = 2L;
        final int line = 50;
        final int course = 26;
        final int route = 996;
        final long timeStamp = 1225112198000L;

        final ITask exampleTask = createExampleTask(id, line, course, route, timeStamp);
        final String existingTaskData = "2#50#26#996#1225112198000";

        final TaskCompareResults value = TaskUtilsV1.compareTaskWithTaskData(exampleTask, existingTaskData);

        assertTrue(TaskCompareResults.isOkValue(value));
    }

    private static ITask createExampleTask(final long id, final int line, final int course, final int route,
                                           final long timeStamp)
    {
        return new SimpleTask(id, line, course, route, timeStamp);
    }
}