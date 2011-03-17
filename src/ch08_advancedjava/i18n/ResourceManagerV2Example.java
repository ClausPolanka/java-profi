package ch08_advancedjava.i18n;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import ch08_advancedjava.i18n.basics.LocaleUtils;
import ch08_advancedjava.i18n.basics.ResourceKeys;

/**
 * Beispielprogramm zur Demonstration der Nutzung der Klasse ResourceManagerV2
 * <br>
 * Die Sprachauswahl erfolgt hier in einem Menü  
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class ResourceManagerV2Example
{
    private static final ResourceManagerV2 resourceManager = ResourceManagerV2.createInstance();

    public static void main(String[] args)
    {
        final JFrame appFrame = new AppFrame("ResourceManagerV2Example");
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
        }

        private void createAndAddMenus(final JComponent comp)
        {
            // Menüeinträge sprachabhängig erzeugen 
            final JMenu fileMenu = new JMenu(new MenuAction(ResourceKeys.txt_file));
            fileMenu.add(new MenuAction(ResourceKeys.txt_new));
            fileMenu.add(new MenuAction(ResourceKeys.txt_open));
            fileMenu.add(new JSeparator());

            final JMenu propertiesMenu = new JMenu(new MenuAction(ResourceKeys.txt_properties));
            fileMenu.add(propertiesMenu);

            // Vorhandene Locales ermitteln, dann sortieren  
            final List<Locale> availableLocales = resourceManager.getAvailableLocales();
            Collections.sort(availableLocales, LocaleUtils.LOCALE_COMPARATOR);

            // entsprechende Menüeinträge bereitstellen 
            for (final Locale currentLocale : availableLocales)
                propertiesMenu.add(new MenuAction(currentLocale));

            fileMenu.add(new JSeparator());
            fileMenu.add(new MenuAction(ResourceKeys.txt_quit));

            comp.add(fileMenu);
        }

        public void handleMenuAction(final Object object)
        {
            // Menüverarbeitung  
            if (object instanceof ResourceKeys)
            {
                final ResourceKeys resourceKey = (ResourceKeys) object;
                switch (resourceKey)
                {
                    case txt_new:
                    case txt_open:
                    case txt_save:
                        JOptionPane.showMessageDialog(null, "Not implemented yet!");
                        break;
                    case txt_quit:
                        System.exit(0);
                }
            }

            // Sprachmenüs bearbeiten 
            if (object instanceof Locale)
            {
                final Locale newLocale = (Locale) object;
                resourceManager.activateLocale(newLocale);
                refreshMenu();
            }
        }

        public final class MenuAction extends AbstractAction
        {
            // Menüeintrag basierend auf ResourceKeys-Konstante erzeugen  
            public MenuAction(final ResourceKeys resourceKey)
            {
                putValue(Action.NAME, resourceManager.getLangString(resourceKey));
                putValue("OBJECT", resourceKey);
            }

            // Sprachmenüs basierend auf Locale erzeugen  
            public MenuAction(final Locale menuLocale)
            {
                final Locale currentLocale = resourceManager.getCurrentLocale();
                final String language = menuLocale.getDisplayLanguage(currentLocale);
                final String country = menuLocale.getDisplayCountry(currentLocale);
                // Kein Querstrich, wenn keine Landesangabe erfolgt  
                final String optionalCountryInfo = (country.isEmpty() ? "" : " / " + country);

                putValue(Action.NAME, language + optionalCountryInfo);
                putValue("OBJECT", menuLocale);
            }

            @Override
            public void actionPerformed(final ActionEvent e)
            {
                handleMenuAction(getValue("OBJECT"));
            }
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
    }

    private ResourceManagerV2Example()
    {
    }
}