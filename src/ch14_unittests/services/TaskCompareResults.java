package ch14_unittests.services;

/**
 * Definition von Rückgabewerten beim Vergleich von Tasks
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public enum TaskCompareResults 
{
    OK("OK"), 
    OK_EXISTING_TASK("OK_EXISTING_TASK"), 
    FAILURE_DIFFERENT_TASK_CONTENT("FAILURE_DIFFERENT_TASK_CONTENT");

    final String text;

    private TaskCompareResults(final String text)
    {
        this.text = text;
    }

    public static boolean isOkValue(final TaskCompareResults value)
    {
        return value == OK || value == OK_EXISTING_TASK;
    }
}
