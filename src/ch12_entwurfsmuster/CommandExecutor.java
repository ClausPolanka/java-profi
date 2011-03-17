package ch12_entwurfsmuster;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import ch07_multithreading.SleepUtils;

/**
 * Dieses Klasse
 */
/**
 * Beispielprogramm zur Demonstration des Befehl-Musters
 * <br>
 * Die Klasse <code>CommandExecutor</code> stellt einen Executor für Kommandos dar. 
 * Alle Kommandos, die in  * die Abarbeitungsqueue eingestellt wurden, werden der Reihe nach bearbeitet.
 * <br>
 * Der entscheidende Unterschied zum ersten Beispiel SimpleCommandExample ist,
 * dass hier die Kommandos eingeplant werden und die Ausführung durch eine andere
 * Einheit, den CommandExecutor, erfolgt und nicht dirket per Methodenaufruf.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CommandExecutor implements Runnable
{
    private static final ICommand NULL_COMMAND = new ICommand()
    {
        @Override
        public void execute()
        {
        }

        @Override
        public String toString()
        {
            return "NULL_COMMAND";
        }
    };
    
    private final Deque<ICommand> commands     = new LinkedBlockingDeque<ICommand>();
    
    public static void main(String[] args)
    {
        // Die Abarbeitung der Kommandos erfolgt nebenläufig
        // zu den folgenden Aktionen
        final CommandExecutor executor = new CommandExecutor();
        new Thread(executor).start();

        // Client erzeugt Kommandos
        final ICommand command1 = new PrintTextCommand("Dies ist ein Text");
        final ICommand command2 = new WaitCommand();
        final ICommand command3 = new PrintTextCommand("Der Test ist beendet!");

        // Client übergibt Kommandos an Executor
        executor.registerCommand(command1);
        executor.registerCommand(command2);
        executor.registerCommand(command3);
    }
    
    public void registerCommand(final ICommand commandToExecute)
    {
        if (commandToExecute == null)
            throw new IllegalArgumentException("Passed command must not be null!");

        commands.offer(commandToExecute);
    }

    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            final ICommand commandToExecute = getAndRemoveNextCommand();
            commandToExecute.execute();

            SleepUtils.safeSleep(50); // Vermeide CPU-Belastung
        }
    }

    private ICommand getAndRemoveNextCommand()
    {
        final ICommand commandToExecute = commands.poll();
        if (commandToExecute == null)
            return NULL_COMMAND;

        return commandToExecute;
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

                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    //
                }
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