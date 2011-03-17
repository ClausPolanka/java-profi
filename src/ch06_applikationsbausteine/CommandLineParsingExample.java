package ch06_applikationsbausteine;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Beispiel für das Parsen und Auswerten von Kommandozeilenparametern mit dem CLI-Framework
 * <br>
 * Es wird ein Applikationsfenster mit den ausgewerteten Kommandozeilenparametern
 * dargestellt.
 * <br>
 * Einsatz der Definitionsklasse AppParameterCLI, des Value Object
 * AppParameterVO und von Hilfsmethoden 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CommandLineParsingExample
{
    public static void main(String[] args)
    {
        final Options allowedOptions = new Options();

        // Alle Options basierend auf enum AppParameterCLI hinzufügen
        for (final AppParameterCLI parameter : AppParameterCLI.values())
        {
            allowedOptions.addOption(parameter.shortname, parameter.name, parameter.hasArgs, parameter.helptext);
        }
        //...
        // Test mit festdefinierten Werten  
        final String[] sampleArgs = new String[] { "-d", "-?", "-w", "444", 
                                                   "--height", "99" };
        
        final AppParameterVO parameters = new AppParameterVO();
        
        try
        {
            //  Kommandozeilenparameter parsen  
            final CommandLineParser parser = new PosixParser();
            final CommandLine line = parser.parse(allowedOptions, sampleArgs);
        
            // Prüfen der Optionen  
            parameters.debug = hasOption(line, AppParameterCLI.DEBUG);
            parameters.showHelp = hasOption(line, AppParameterCLI.HELP);
        
            if (hasOption(line, AppParameterCLI.WIDTH))
                parameters.width = extractInt(line, AppParameterCLI.WIDTH);
        
            if (hasOption(line, AppParameterCLI.HEIGHT))
                parameters.height = extractInt(line, AppParameterCLI.HEIGHT);
        
            final JFrame appFrame = new AppFrame("CommandLineParsingExample", 
                                                 parameters);
            appFrame.setVisible(true);
        }
        catch (final ParseException exp)
        {
            printHelp(allowedOptions);
        }
    }
    
    private static boolean hasOption(final CommandLine line, 
                                     final AppParameterCLI parameter)
    {
        return line.hasOption(parameter.name);
    }
    
    private static int extractInt(final CommandLine line, 
                                  final AppParameterCLI parameter)
    {
        return Integer.parseInt(line.getOptionValue(parameter.name));
    }

    private static void printHelp(final Options allowedOptions)
    {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("CommandLineParsingExample", allowedOptions);
    }
    // ...
    private CommandLineParsingExample()
    {
    }

    public static class AppFrame extends JFrame
    {
        AppFrame(final String title, final AppParameterVO parameters)
        {
            super(title);

            setSize(parameters.width, parameters.height);

            final String info = parameters.toString();

            add(new JLabel(info));

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}
