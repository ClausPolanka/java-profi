package ch16_optimierungen;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Utilityklasse zur Unterstützung von Performancemessungen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PerformanceUtils
{
    static
    {
        PropertyConfigurator.configureAndWatch("config/log4j.properties", 5000);
    }
    
    private static final Logger                   log              = Logger.getLogger(PerformanceUtils.class);
    
    private static final long                     MILLI_SEC_FACTOR = 1000 * 1000;

    private static final Map<String, TimingEntry> timingMap        = new HashMap<String, TimingEntry>();

    private static final class TimingEntry
    {
        private final long startTime;
        private long       stopTime;

        public TimingEntry(final long startTime)
        {
            this.startTime = startTime;
        }

        public void setStopTime(final long stopTime)
        {
            this.stopTime = stopTime;
        }
    }

    public static void startMeasure(final String name)
    {
        timingMap.put(name, new TimingEntry(System.nanoTime()));
    }

    public static void stopMeasure(final String name)
    {
        final TimingEntry timingEntry = getTimingEntry(name);
        timingEntry.setStopTime(System.nanoTime());
    }

    public static void printTimingResult(final String name)
    {
        final TimingEntry timingEntry = getTimingEntry(name);
        printTimingResult(name, timingEntry.startTime, timingEntry.stopTime);
    }

    public static void printTimingResult(final String info, final long begin, final long end)
    {
        log.info(info + " took " + (end - begin) / MILLI_SEC_FACTOR + " ms");
    }

    public static void printTimingResultWithAverage(final String name, final long count)
    {
        final TimingEntry timingEntry = getTimingEntry(name);
        printTimingResultWithAverage(name, timingEntry.startTime, timingEntry.stopTime, count);
    }

    public static void printTimingResultWithAverage(final String info, final long begin, final long end,
                                                    final long count)
    {
        printTimingResult(info, begin, end);

        final double avg = ((double) (end - begin)) / MILLI_SEC_FACTOR / (double) count;
        log.info(String.format(info + " avg %f ms", avg));
    }

    private static TimingEntry getTimingEntry(final String name)
    {
        final TimingEntry timingEntry = timingMap.get(name);
        if (timingEntry == null)
            throw new IllegalArgumentException("No data for '" + name + "'");

        return timingEntry;
    }
}
