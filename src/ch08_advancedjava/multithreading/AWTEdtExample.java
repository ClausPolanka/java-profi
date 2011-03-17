package ch08_advancedjava.multithreading;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

import ch07_multithreading.SleepUtils;

/**
 * Beispielprogramm zur Demonstration der Abarbeitung
 * von Aufgaben synchron im AWT-Event-Thread 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AWTEdtExample
{
    private static final JTextField resultTextfield = new JTextField("?");

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
        fileMenu.add(new LongRunningMenuAction("Start LongRunningAction"));

        menubar.add(fileMenu);
    }

    public static final class LongRunningMenuAction extends AbstractAction
    {
        public LongRunningMenuAction(final String resourceKey)
        {
            super(resourceKey);
        }

        public void actionPerformed(ActionEvent e)
        {
            resultTextfield.setText("Computation started");

            for (int i = 1; i < 4; i++)
            {
                // Verzögerung (stellvertretend für aufwendige Berechnung)  
                SleepUtils.safeSleep(1000);
                // Rückmeldung ausgeben => wirkt nicht  
                resultTextfield.setText("Computation " + i);
            }

            SleepUtils.safeSleep(1000);
            resultTextfield.setText("Computation finished");
        }
    }
}
