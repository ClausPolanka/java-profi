package ch07_multithreading;

import java.util.Timer;

import ch07_multithreading.TimerTaskExample.SampleTimerTask;

/**
 * Beispiel f�r die Nutzung eines Timers und von TimerTasks zur
 * zeitgesteuerten, periodischen Ausf�hrung von Aufgaben 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class TimerTaskPeriodicExample
{
    public static void main(String[] args)
    {
        final Timer timer = new Timer();

        // Eingeplante Ausf�hrung mit INITIAL_DELAY und Verz�gerung DELAY 
        final long INITIAL_DELAY_ONE_SEC = 1000;
        final long DELAY_30_SECS = 30000;
        timer.schedule(new SampleTimerTask("PeriodicRefreshing"), INITIAL_DELAY_ONE_SEC, DELAY_30_SECS);

        // Eingeplante Ausf�hrung mit INITIAL_DELAY und Takt RATE 
        final long RATE_10_SECS = 10000;
        timer.scheduleAtFixedRate(new SampleTimerTask("10s FixedRateExecution"), INITIAL_DELAY_ONE_SEC, RATE_10_SECS);
    }

    private TimerTaskPeriodicExample()
    {
    }
}