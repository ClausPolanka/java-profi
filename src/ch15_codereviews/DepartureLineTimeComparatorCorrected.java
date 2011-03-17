package ch15_codereviews;

import java.util.Comparator;

/**
 * Beispielklasse eines Komparators, korrigierte Version
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class DepartureLineTimeComparatorCorrected implements Comparator<Departure>
{
    private final Comparator<Departure> lineComparator = new LineComparator();
    private final Comparator<Departure> timeComparator = new TimeComparator();

    public int compare(final Departure departure1, final Departure departure2)
    {
        int result = lineComparator.compare(departure1, departure2);
        if (result == 0)
        {
            // KORREKTUR 
            result = timeComparator.compare(departure1, departure2);
        }
        return result;
    }
}