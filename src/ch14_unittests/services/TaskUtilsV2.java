package ch14_unittests.services;

import org.apache.log4j.Logger;

import ch14_unittests.services.tasks.ITask;

/**
 * Beispielklasse, die Objekte vom Typ ITask vergleicht
 * <br>
 * verbesserte Version 2
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class TaskUtilsV2
{
    private static final Logger log = Logger.getLogger(TaskUtilsV2.class);

    public static TaskCompareResults compareTaskWithTaskData(final ITask aTask, final String existingTaskData)
    {
        // Parameter-Pr�fungen wg. �bersichtlichkeit entfernt

        final String newTaskData = TaskConverter.asHashCodedString(aTask);
        return compareTaskData(newTaskData, existingTaskData);
    }

    public static TaskCompareResults compareTaskData(final String newTaskData, final String existingTaskData)
    {
        log.debug("comparing '" + existingTaskData + "' with new '" + newTaskData + "'.");

        if (newTaskData.length() > existingTaskData.length())
        {
            log.warn("new task is different from existing (wrong length).");
            return TaskCompareResults.FAILURE_DIFFERENT_TASK_CONTENT;
        }

        // Gleicher Inhalt? 
        if (newTaskData.equals(existingTaskData.substring(0, newTaskData.length())))
        {
            log.info("task " + existingTaskData + " already exists.");
            return TaskCompareResults.OK_EXISTING_TASK;
        }

        // Inhalt unterschiedlich! 
        log.warn("different content (not equal).");
        return TaskCompareResults.FAILURE_DIFFERENT_TASK_CONTENT;
    }

    // ...

    private TaskUtilsV2()
    {
    }
}
