package ch08_advancedjava.multithreading;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ch07_multithreading.SleepUtils;

/**
 * Beispielprogramm zur Demonstration der Abarbeitung
 * von Aufgaben parallel zum AWT-Event-Thread mithilfe eines Worker-Threads
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AWTEdtExample2
{
    private static final JTextField resultTextfield = new JTextField("?");

    public static final class AsyncLongRunningMenuAction extends AbstractAction
    {
        public AsyncLongRunningMenuAction(final String resourceKey)
        {
            super(resourceKey);
        }

        public void actionPerformed(final ActionEvent e)
        {
            resultTextfield.setText("Computation started");

            // Worker-Thread abspalten 
            new Thread()
            {
                @Override
                public void run()
                {
                    for (int i = 1; i < 4; i++)
                    {
                        // Verzögerung (stellvertretend für aufwendige Berechnung)  
                        SleepUtils.safeSleep(1000);
                        // Propagation in den EDT  
                        safeSetText("Computation " + i);
                    }

                    SleepUtils.safeSleep(1000);
                    // Propagation in den EDT  
                    safeSetText("Computation finished");
                }
            }.start();
        }
    }

    private static void safeSetText(final String text)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                resultTextfield.setText(text);
            }
        });
    }

    public static void main(String[] args) throws IOException
    {
        final JFrame jframe = new JFrame("Event-Dispath-Thread-Example");
        final JMenuBar menubar = new JMenuBar();

        createAndAddMenus(menubar);

        jframe.getContentPane().setLayout(new BorderLayout());
        jframe.getContentPane().add(menubar, BorderLayout.NORTH);
        jframe.getContentPane().add(resultTextfield);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jframe.setSize(400, 250);
        // realize
        jframe.setVisible(true);
    }

    private static void createAndAddMenus(final JMenuBar menubar)
    {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.add(new AsyncLongRunningMenuAction("AsyncLongRunningMenuAction"));

        menubar.add(fileMenu);
    }
}
