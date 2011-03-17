package ch08_advancedjava.i18n;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import ch08_advancedjava.i18n.basics.ResourceKeys;

/**
 * Beispielprogramm zur Demonstration der Nutzung der Klasse ResourceManager, um
 * über Buttons ein Menü sprachabhängig zu gestalten 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ResourceManagerExample
{
    private static final ResourceManager resourceManager = ResourceManager.createInstance();
    
    public static void main(String[] args)
    {
        final JFrame appFrame = new AppFrame("ResourceManagerExample");
        appFrame.setVisible(true);
    }

    public static class AppFrame extends JFrame
    {
        private final JMenuBar menuBar = new JMenuBar();

        AppFrame(final String title)
        {
            super(title);
            setSize(400, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            createAndAddMenus(menuBar);

            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(menuBar, BorderLayout.NORTH);

            final JButton deButton = createButton(Locale.GERMANY);
            final JButton enButton = createButton(Locale.UK);
            final JButton frButton = createButton(Locale.FRANCE);
            final JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(deButton);
            buttonPanel.add(enButton);
            buttonPanel.add(frButton);
            getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        }

        private JButton createButton(final Locale locale)
        {
            final String name = locale.getDisplayLanguage();
            final JButton button = new JButton(new AbstractAction(name)
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    resourceManager.activateLocale(locale);
                    refreshMenu();
                }
            });

            return button;
        }

        private void refreshMenu()
        {
            menuBar.removeAll();
            createAndAddMenus(menuBar);

            // Diese drei Methoden bei GUI-Änderungen immer zusammen aufrufen  
            invalidate();
            validate();
            repaint();
        }
        // ...
        
        private void createAndAddMenus(final JComponent comp)
        {
            final JMenu fileMenu = new JMenu(getLangString(ResourceKeys.txt_file));
            fileMenu.add(new JMenuItem(getLangString(ResourceKeys.txt_new)));
            fileMenu.add(new JMenuItem(getLangString(ResourceKeys.txt_open)));
            fileMenu.add(new JSeparator());
            fileMenu.add(new JMenuItem(getLangString(ResourceKeys.txt_properties)));
            fileMenu.add(new JSeparator());
            fileMenu.add(new JMenuItem(getLangString(ResourceKeys.txt_quit)));

            comp.add(fileMenu);
        }

        private String getLangString(final ResourceKeys key)
        {
            return resourceManager.getLangString(key);
        }
    }

    private ResourceManagerExample()
    {
    }
}