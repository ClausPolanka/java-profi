package ch15_codereviews;

import java.util.Comparator;

/**
 * Beispielklasse eines Komparators
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DepartureLineTimeComparator implements Comparator<Departure>
{
    private final Comparator<Departure> lineComparator = new LineComparator();
    private final Comparator<Departure> timeComparator = new TimeComparator();

    public int compare(final Departure departure1, final Departure departure2)
    {
        int result = lineComparator.compare(departure1, departure2);
        if (result == 0)
        {
            timeComparator.compare(departure1, departure2);
        }
        return result;
    }
}

// just stub implementations returning 0
class LineComparator implements Comparator<Departure>
{
    public int compare(Departure o1, Departure o2)
    {
        return 0;
    }
}

class TimeComparator implements Comparator<Departure>
{
    public int compare(Departure o1, Departure o2)
    {
        return 0;
    }
}