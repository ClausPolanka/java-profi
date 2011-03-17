package ch06_applikationsbausteine;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Beispiel für das Parsen und Auswerten von Kommandozeilenparametern
 * <br>
 * Es wird ein Applikationsfenster mit den ausgewerteten Kommandozeilenparametern
 * dargestellt.
 * <br>
 * Einsatz der Definitionsklasse AppParameter und von Hilfsmethoden 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ArgsParsingExampleWithEnum
{
    public static void main(final String[] args)
    {
        // Defaultwerte, wenn Wert nicht in der Kommandozeile ï¿½bergeben
        int width = 700;
        int height = 200;
        boolean debug = false;
        boolean showHelp = false;

        final String[] sampleArgs = new String[] { "-h", "-w=550", "-height=99" };

        for (final String cmdArg : sampleArgs)
        {
            if (hasBooleanParam(cmdArg, AppParameter.DEBUG_SHORT))
                debug = true;

            if (hasBooleanParam(cmdArg, AppParameter.HELP_SHORT))
                showHelp = true;

            if (hasValuedParam(cmdArg, AppParameter.WIDTH_SHORT))
                width = extractInt(cmdArg, AppParameter.WIDTH_SHORT);
            if (hasValuedParam(cmdArg, AppParameter.WIDTH))
                width = extractInt(cmdArg, AppParameter.WIDTH);

            if (hasValuedParam(cmdArg, AppParameter.HEIGHT_SHORT))
                height = extractInt(cmdArg, AppParameter.HEIGHT_SHORT);
            if (hasValuedParam(cmdArg, AppParameter.HEIGHT))
                height = extractInt(cmdArg, AppParameter.HEIGHT);
        }

        final JFrame appFrame = new AppFrame("ArgsParsingExampleWithEnum", width, height, debug, showHelp);
        appFrame.setVisible(true);
    }

    private static boolean hasBooleanParam(final String cmdArg, final AppParameter parameter)
    {
        return cmdArg.equals(parameter.paramName);
    }

    private static boolean hasValuedParam(final String cmdArg, final AppParameter parameter)
    {
        return cmdArg.startsWith(parameter.paramName) && cmdArg.length() > parameter.valueOffset;
    }

    private static int extractInt(final String cmdArg, final AppParameter parameter)
    {
        return Integer.parseInt(cmdArg.substring(parameter.valueOffset));
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

    private ArgsParsingExampleWithEnum()
    {
    }
}