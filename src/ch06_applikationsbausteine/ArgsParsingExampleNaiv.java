package ch06_applikationsbausteine;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Beispiel für das naive Parsen und Auswerten von Kommandozeilenparametern
 * <br>
 * Es wird ein Applikationsfenster mit den ausgewerteten Kommandozeilenparametern
 * dargestellt.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ArgsParsingExampleNaiv
{
    public static void main(final String[] args)
    {
        // Defaultwerte, wenn Wert nicht in der Kommandozeile übergeben  
        int width = 700;
        int height = 200;
        boolean debug = false;
        boolean showHelp = false;

        // Test mit festdefinierten Werten 
        final String[] sampleArgs = new String[] { "-h", "-w=550", "-height=550" };

        for (final String cmdArg : sampleArgs)
        {
            // check debug 
            if (cmdArg.startsWith("-d"))
                debug = true;

            // check help 
            else if (cmdArg.startsWith("-h"))
                showHelp = true;

            // check width  
            else if (cmdArg.startsWith("-w="))
                width = Integer.parseInt(cmdArg.substring(3));
            else if (cmdArg.startsWith("-width="))
                width = Integer.parseInt(cmdArg.substring(7));

            // check height  !!! UNREACHABLE !!!  
            else if (cmdArg.startsWith("-h="))
                height = Integer.parseInt(cmdArg.substring(3));
            else if (cmdArg.startsWith("-height="))
                height = Integer.parseInt(cmdArg.substring(8));
        }
        // ...
        final JFrame appFrame = new AppFrame("ArgsParsingExampleNaiv", width, height, debug, showHelp);
        appFrame.setVisible(true);
    }

    private ArgsParsingExampleNaiv()
    {
    }

    public static class AppFrame extends JFrame
    {
        AppFrame(final String title, final int width, final int height, final boolean debug, final boolean showHelp)
        {
            super(title);

            setSize(width, height);

            final String info = "AppParameters [debug=" + debug + ", height=" + height + ", showHelp=" + showHelp
                                + ", width=" + width + "]";

            add(new JLabel(info));

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}
