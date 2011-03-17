package ch06_applikationsbausteine;

import java.util.Date;

/**
 * Diese (vereinfachte) Klasse modelliert einen Highscore-Eintrag
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class Highscore
{
    /*private*/ final String name;
    /*private*/ final int    points;
    /*private*/ final int    level;
    /*private*/ final Date   date;

    public Highscore(final String name, final int points, 
                     final int level, final Date date)
    {
        this.name = name;
        this.points = points;
        this.level = level;
        this.date = date;
    }
    
    // ...
    
    @Override
    public String toString()
    {
        return "Highscore [name=" + name + ", points=" + points + ", level=" + level + ", date=" + date + "]";
    }
}