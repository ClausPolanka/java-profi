package ch07_multithreading;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Beispiel für die Nutzung eines Timers und von TimerTasks zur
 * zeitgesteuerten Ausführung von Aufgaben
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class TimerTaskExample
{
    public static void main(String[] args)
    {
        final Timer timer = new Timer();

        // Sofortige Ausführung 
        final long NO_DELAY = 0;
        timer.schedule(new SampleTimerTask("OnceImmediately"), NO_DELAY);

        // Ausführung nach fünf Sekunden 
        final long INITIAL_DELAY_FIVE_SEC = 5000;
        timer.schedule(new SampleTimerTask("OnceAfter5s"), INITIAL_DELAY_FIVE_SEC);

        // Ausführung nach einer Minute 
        final Date ONE_MINUTE_AS_DATE = oneMinuteFromNow();
        timer.schedule(new SampleTimerTask("OnceAfter1min"), ONE_MINUTE_AS_DATE);
    }

    private static Date oneMinuteFromNow()
    {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);
        return calendar.getTime();
    }

    public static class SampleTimerTask extends TimerTask
    {
        private final String message;

        SampleTimerTask(final String message)
        {
            this.message = message;
        }

        public void run()
        {
            System.out.println(message);
        }
    }

    private TimerTaskExample()
    {
    }
}
