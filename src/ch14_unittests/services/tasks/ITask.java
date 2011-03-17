package ch14_unittests.services.tasks;

/**
 * Beispielinterface für verschiedene Tasks
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public interface ITask
{
    long getId();
    int getLine();
    int getCourse();
    int getRoute();
    long getTimeStamp();
    
    boolean execute();
}
