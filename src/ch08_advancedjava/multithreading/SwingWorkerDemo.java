package ch08_advancedjava.multithreading;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import ch07_multithreading.SleepUtils;

/**
 * Beispielprogramm zur Demonstration der Vereinfachungen der Abarbeitung
 * von Aufgaben parallel zum AWT-Event-Thread mithilfe der Klasse SwingWorker
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class SwingWorkerDemo
{
    private static final JTextField             resultTextfield = new JTextField("?");
    private static final JButton                stopBtn         = new JButton("Stop");
    private static final AbstractAction         startAction     = new LongRunningMenuAction("Start LongRunningAction with SwingWorker");

    private static SwingWorker<Integer, String> currentWorker   = null;

    public static void main(String[] args) throws IOException
    {
        final JFrame jframe = new JFrame("SwingWorkerDemo");
        final JMenuBar menubar = new JMenuBar();
        createAndAddMenus(menubar);

        jframe.getContentPane().setLayout(new BorderLayout());
        jframe.getContentPane().add(menubar, BorderLayout.NORTH);
        jframe.getContentPane().add(resultTextfield);
        jframe.getContentPane().add(stopBtn, BorderLayout.SOUTH);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        stopBtn.setEnabled(false);
        stopBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent actionEvent)
            {
                startAction.setEnabled(true);
                stopBtn.setEnabled(false);

                currentWorker.cancel(false);
            }
        });

        jframe.setSize(400, 250);
        // realize
        jframe.setVisible(true);
    }

    private static void createAndAddMenus(final JMenuBar menubar)
    {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.add(startAction);

        menubar.add(fileMenu);
    }

    public static final class LongRunningMenuAction extends AbstractAction
    {
        public LongRunningMenuAction(final String resourceKey)
        {
            super(resourceKey);
        }

        public void actionPerformed(final ActionEvent e)
        {
            stopBtn.setEnabled(true);
            startAction.setEnabled(false);

            // Start eines SwingWorkers  
            currentWorker = new DemoSwingWorker();
            currentWorker.execute();
        }
    }

    public static class DemoSwingWorker extends SwingWorker<Integer, String>
    {
        // doInBackground() wird in einem eigenen Thread ausgeführt. 
        // Hier dürfen keine Manipulationen an Swing-Komponenten stattfinden!  
        @Override
        protected Integer doInBackground() throws Exception
        {
            publish("Computation started");

            for (int i = 1; i < 4; i++)
            {
                // Abbruch mehrfach testen, um schnell zu reagieren  
                if (isCancelled())
                    break;

                // Verzögerung (stellvertretend für aufwendige Berechnung)  
                SleepUtils.safeSleep(1000);

                // Abbruch mehrfach testen, um schnell zu reagieren  
                if (isCancelled())
                    break;

                // $\mbox{\bfseries Zwischenergebnis ausgeben }$
                publish("Computation " + i);
            }

            // Ergebnis durch Aufruf von get() in der done()-Methode abfragbar  
            return 4711;
        }

        // process() erlaubt es, Zwischenergebnisse darzustellen.  
        // Aufruf im EDT: => Manipulationen an GUI-Elementen sicher möglich  
        @Override
        protected void process(final List<String> info)
        {
            resultTextfield.setText(info.get(0));
        }

        // Worker wurde beendet. done() wird innerhalb des EDTs aufgerufen.  
        // Manipulationen an GUI-Elementen sicher möglich  
        @Override
        protected void done()
        {
            try
            {
                // Ergebnis aus doInBackground() ermitteln  
                final Integer value = get();

                publish("Computation finished: result = " + value);
            }
            catch (final InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            catch (final ExecutionException e)
            {
                throw new RuntimeException(e);
            }
            catch (final CancellationException e)
            {
                publish("Canceled");
            }

            startAction.setEnabled(true);
            stopBtn.setEnabled(false);
        }
    }
}