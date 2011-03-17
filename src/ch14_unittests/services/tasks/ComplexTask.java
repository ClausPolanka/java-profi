package ch14_unittests.services.tasks;

/**
 * Beispielklasse für einen komplexeren Task
 * <br>
 * Hier nur als Immutable-Klasse ohne viel Funktionalität
 *   
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ComplexTask implements ITask
{
    private final long id;
    private final int  line;
    private final int  course;
    private final int  route;
    private final long timeStamp;

    public ComplexTask(final long id, final int line, final int course, final int route, final long timeStamp)
    {
        this.id = id;
        this.line = line;
        this.course = course;
        this.route = route;
        this.timeStamp = timeStamp;
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public long getTimeStamp()
    {
        return timeStamp;
    }

    @Override
    public int getLine()
    {
        return line;
    }

    @Override
    public int getCourse()
    {
        return course;
    }

    @Override
    public int getRoute()
    {
        return route;
    }

    @Override
    public boolean execute()
    {
        try
        {
            TaskService.getInstance().doSomeTask();
            return true;
        }
        catch (final TaskException e)
        {
            // Hier simplifiziert: Exception -> Rückgabe von false
            return false;
        }        
    }
}
