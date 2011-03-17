package ch12_entwurfsmuster;

import ch07_multithreading.SleepUtils;

/**
 * Beispielprogramm zur Demonstration des Befehl-Musters
 * <br>
 * Die Klasse <code>SimpleCommandExample</code> erzeugt drei Befehlsobjekte, die das
 * Interface ICommand erfüllen.
 * Zunächst wird durch ein PrintTextCommand eine Startmeldung ausgegeben, dann über
 * ein WaitCommand eine Zeit gewartet und schließlich wiederum mithilfe eines
 * PrintTextCommand eine Endemeldung ausgegeben.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SimpleCommandExample
{
    public static void main(String[] args)
    {
        // Erzeugen der Command-Objekte 
        final ICommand command1 = new PrintTextCommand("Dies ist ein Text");
        final ICommand command2 = new WaitCommand();
        final ICommand command3 = new PrintTextCommand("Der Test ist beendet!");

        // Ausführen der Command-Objekte 
        command1.execute();
        command2.execute();
        command3.execute();
    }

    private SimpleCommandExample()
    {
    }

    interface ICommand
    {
        void execute();
    }

    public static class PrintTextCommand implements ICommand
    {
        final String text;

        public PrintTextCommand(final String text)
        {
            this.text = text;
        }

        @Override
        public void execute()
        {
            System.out.println(text);
        }

        @Override
        public String toString()
        {
            return "PrintTextCommand [text=" + text + "]";
        }
    }

    public static class WaitCommand implements ICommand
    {
        final int WAIT_TIME_IN_SEC = 3;

        @Override
        public void execute()
        {
            for (int i = 0; i < WAIT_TIME_IN_SEC; i++)
            {
                System.out.print(".");

                SleepUtils.safeSleep(1000);
            }
            System.out.println();
        }

        @Override
        public String toString()
        {
            return "WaitCommand [WAIT_TIME_IN_SEC=" + WAIT_TIME_IN_SEC + "]";
        }
    }
}