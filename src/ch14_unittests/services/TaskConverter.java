package ch14_unittests.services;

import ch14_unittests.services.tasks.ITask;

/**
 * Beispielklasse, die Objekte vom Typ ITask in eine #-separierte Darstellung wandeln
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class TaskConverter
{
    public static String asHashCodedString(final ITask task)
    {
        return task.getId() + "#" + task.getLine() + "#" + task.getCourse() + "#" + task.getRoute() + "#" 
               + task.getTimeStamp();
    }

    private TaskConverter()
    {
    }
}
