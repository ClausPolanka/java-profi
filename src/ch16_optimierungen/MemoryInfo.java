package ch16_optimierungen;

import ch07_multithreading.SleepUtils;

/**
 * Utilityklasse zur Unterstützung bei Speichermessungen
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class MemoryInfo
{
    /**
     *  returns the total amount of free memory
     */
    public static long freeMem()
    {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     *  returns the total available memory 
     */
    public static long totalMem()
    {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     *  returns the total available memory
     */
    public static long availableMem()
    {
        return totalMem() - freeMem();
    }

    /**
     *  Returns memory statics 
     */
    public static String statistics()
    {
        final StringBuffer buffer = new StringBuffer();

        final long freePercentage = (100 * freeMem()) / totalMem();
        final long usedPercentage = 100 - freePercentage;

        buffer.append("USED = (" + usedPercentage + "%) " + (availableMem()) + " bytes \n");
        buffer.append("FREE =  (" + freePercentage + "%) " + freeMem() + " bytes \n");
        buffer.append("HEAP = (100%) " + totalMem() + " bytes ");

        return buffer.toString();
    }

    /**
     *  perform gc and sleep for 5 secs
     */
    public static void gcAndSleep5s()
    {
        System.gc();
        SleepUtils.safeSleep(5000);
    }
    
    /**
     *  just a simple test
     */
    public static void main(String[] args)
    {
        System.out.println("MemoryInfo.statistics():");
        System.out.println(MemoryInfo.statistics());
    }
    
    private MemoryInfo()
    {
    }
}
