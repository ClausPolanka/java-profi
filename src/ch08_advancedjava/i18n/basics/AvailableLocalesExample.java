package ch08_advancedjava.i18n.basics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.AbstractTableModel;

/**
 * Beispielklasse zur Demonstration des APIs der Klasse Locale 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AvailableLocalesExample extends JFrame
{
    private final LocaleTableModel localeTableModel;
    private final JTable           localeTable;
    private final JComboBox        localeCombox;

    public static void main(final String[] args)
    {
        final JFrame demoframe = new AvailableLocalesExample();
        demoframe.setSize(700, 500);
        demoframe.setVisible(true);
    }
    
    public AvailableLocalesExample()
    {
        setTitle("AvailableLocalesExample");

        localeTableModel = new LocaleTableModel();
        localeTableModel.setSelectedLocale(Locale.GERMANY);
        localeTable = new JTable(localeTableModel);

        final JScrollPane scrollPane = new JScrollPane(localeTable);
        getContentPane().add(scrollPane);

        localeCombox = new JComboBox(new Vector<Locale>(LocaleUtils.getLanguageOnlyLocales()));
        localeCombox.setRenderer(LANGUAGE_RENDERER);
        localeCombox.setSelectedItem(Locale.GERMAN);
        getContentPane().add(localeCombox, BorderLayout.NORTH);
        localeCombox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    final Locale selectedLocale = (Locale) localeCombox.getSelectedItem();
                    localeTableModel.setSelectedLocale(selectedLocale);
                }
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private static class LocaleTableModel extends AbstractTableModel
    {
        private final String[]     columnNames = { "Language", "Country", "DisplayLanguage", "DisplayCountry",
                                                               "DisplayLanguage(Locale)", "DisplayCountry(Locale)" };

        private final List<Locale> locales;

        private Locale             selectedLocale;

        public LocaleTableModel()
        {
            final Locale[] availableLocale = Locale.getAvailableLocales();
            final List<Locale> localeList = Arrays.asList(availableLocale);

            // unabhängig machen
            locales = new ArrayList<Locale>(localeList);
            Collections.sort(locales, LocaleUtils.LOCALE_COMPARATOR);
        }

        public int getRowCount()
        {
            return locales.size();
        }

        public int getColumnCount()
        {
            return 6;
        }

        public String getColumnName(final int column)
        {
            return columnNames[column];
        }

        public Object getValueAt(final int rowIndex, final int columnIndex)
        {
            final Locale person = locales.get(rowIndex);

            if (columnIndex == 0)
                return person.getLanguage();

            if (columnIndex == 1)
                return person.getCountry();

            if (columnIndex == 2)
                return person.getDisplayLanguage();

            if (columnIndex == 3)
                return person.getDisplayCountry();

            if (columnIndex == 4)
                return person.getDisplayLanguage(selectedLocale);

            if (columnIndex == 5)
                return person.getDisplayCountry(selectedLocale);

            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex)
        {
            return String.class;
        }

        public void setSelectedLocale(final Locale selectedLocale)
        {
            this.selectedLocale = selectedLocale;
            fireTableDataChanged();
        }
    }
    
    public static final ListCellRenderer LANGUAGE_RENDERER = new DefaultListCellRenderer()
    {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            if (value instanceof Locale)
            {
                final Locale locale = (Locale)value;
                setText(locale.getLanguage() + " (" + locale.getDisplayLanguage() + ")");            
            }
            return this;
        }    
    };
}