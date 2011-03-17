package ch14_unittests.services.tasks;

/**
 * Beispielimplementierung für einen TaskService
 * <br>
 * Hier als Singleton realisiert
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class TaskService implements ITaskService
{
    private static final ITaskService INSTANCE = new TaskService();
    
    public static final ITaskService getInstance()
    {
        return INSTANCE;
    }
    
    @Override
    public void doSomeTask() throws TaskException
    {
        System.out.println("doSomeTask()");
    }
}
