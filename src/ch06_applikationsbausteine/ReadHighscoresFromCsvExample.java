package ch06_applikationsbausteine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Beispiel zur Verarbeitung von CVS-Dateien
 * <br>
 * Lese aus einer speziellen csv-Datei alle Werte ein und mache daraus eine Liste 
 * von Highscore-Objekten. Dabei sollte jede Zeile der Datei den folgenden Aufbau 
 * haben: "<code>Name , Punkte , Level , Datum</code>"
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ReadHighscoresFromCsvExample
{
    private static final Logger log         = Logger.getLogger(ReadHighscoresFromCsvExample.class);

    private static final int    POS_NAME    = 0;
    private static final int    POS_POINTS  = 1;
    private static final int    POS_LEVEL   = 2;
    private static final int    POS_DATE    = 3;

    private static final int    VALUE_COUNT = 4;

    public static List<Highscore> readHighscoresFromCsv(final String fileName)
    {
        final File inputFile = new File(fileName);

        final List<Highscore> highscores = new LinkedList<Highscore>();

        BufferedReader br = null;
        try
        {
            // Öffnen der Datei zum Lesen 
            br = new BufferedReader(new FileReader(inputFile));

            String line = null;
            while ((line = br.readLine()) != null)
            {
                final Highscore highscore = extractHighscoreFromLine(line);
                if (highscore != null)
                {
                    highscores.add(highscore);
                }
            }
        }
        catch (final FileNotFoundException e)
        {
            log.warn("No input file '" + inputFile + "'", e);
        }
        catch (final IOException e)
        {
            log.warn("processing of file '" + inputFile + "' incomplete!", e);
        }
        finally
        {
            StreamUtils.safeClose(br);
        }

        return highscores;
    }


    private static Highscore extractHighscoreFromLine(final String line)
    {
        // Spalte die Eingabe mit ';' oder ',' auf           
        final String[] values = line.split(";|,");

        // Behandlung von Leerzeilen und Kommentaren            
        if (isEmptyLineOrComment(values))
        {
            return null;
        }
        // Ignoriere fehlertoleranterweise unvollständige Einträge      
        if (values.length != VALUE_COUNT)
        {
            log.warn("Wrong number of values: " + values.length + " expected: " + VALUE_COUNT + "! Skipping invalid value '" + line + "'");
            return null;
        }

        try
        {
            // Auslesen der Werte als String + Typprüfung + Konvertierung 
            final String name = values[POS_NAME].trim();
            final int points = Integer.parseInt(values[POS_POINTS].trim());
            final int level = Integer.parseInt(values[POS_LEVEL].trim());
            final String dateAsString = values[POS_DATE].trim();
            final Date date = DateFormat.getDateInstance().parse(dateAsString);

            return new Highscore(name, points, level, date);
        }
        catch (final NumberFormatException e)
        {
            log.warn("Skipping invalid point or level value '" + line + "'");
        }
        catch (final ParseException e)
        {
            log.warn("Skipping invalid date value '" + line + "'");
        }
        return null;
    }

    private static boolean isEmptyLineOrComment(final String[] values)
    {
        return (values.length == 1 && (values[0].trim().length() == 0) || 
			   // Ignoriere Kommentare, die auch ',' oder ';' enthalten
			   (values.length >= 1 && values[0].trim().startsWith("#")));
    }
    
    public static final void main(String[] args)
    {
        PropertyConfigurator.configureAndWatch("config/log4j.properties", 5000);
        
        final String filePath = "config/Highscores.csv";    
        final List<Highscore> highscores = readHighscoresFromCsv(filePath);
        System.out.println("highscores = '" + highscores + "'");
    
        final AppFrame appFrame = new AppFrame(highscores);
        appFrame.setVisible(true);
    }
    // ...
    
    public static class AppFrame extends JFrame
    {
        AppFrame(final List<Highscore> highscores)
        {
            super("Highscores");
            setSize(900, 200);

            final AbstractTableModel highscroeTableModel = new AbstractTableModel()
            {
                @Override
                public int getColumnCount()
                {
                    return 4;
                }

                @Override
                public int getRowCount()
                {
                    return highscores.size();
                }

                @Override
                public String getColumnName(int column)
                {
                    switch (column)
                    {
                        case POS_NAME:
                            return "Name";
                        case POS_POINTS:
                            return "Punkte";
                        case POS_LEVEL:
                            return "Level";
                        case POS_DATE:
                            return "Datum";
                    }
                    return "n/a";
                }

                @Override
                public Class<?> getColumnClass(int columnIndex)
                {
                    switch (columnIndex)
                    {
                        case POS_NAME:
                            return String.class;
                        case POS_POINTS:
                            return Integer.class;
                        case POS_LEVEL:
                            return Integer.class;
                        case POS_DATE:
                            return Date.class;
                    }
                    return Object.class;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex)
                {
                    final Highscore highscore = highscores.get(rowIndex);
                    switch (columnIndex)
                    {
                        case POS_NAME:
                            return highscore.name;
                        case POS_POINTS:
                            return highscore.points;
                        case POS_LEVEL:
                            return highscore.level;
                        case POS_DATE:
                            return highscore.date;
                    }
                    return null;
                }
            };

            final JTable table = new JTable(highscroeTableModel);
            final JScrollPane scollpane = new JScrollPane(table);
            add(scollpane);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}
