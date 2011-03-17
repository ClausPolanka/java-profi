package ch06_applikationsbausteine;

import javax.swing.JFrame;
import javax.swing.JLabel;

public final class PreferencesExampleOrig
{
// linksb�ndig f�r Buch    
public static void main(final String[] args)
{
    // Defaultwerte, wenn nicht in der Kommandozeile �bergeben
    int width = 700;
    int height = 400;
    boolean debug = false;
    boolean showHelp = false;

    // Test mit festdefinierten Werten
    final String[] sampleArgs = new String[] { "-hello", "-d", "-w=550", 
                                               "-height=550" };

    for (final String cmdArg : sampleArgs)
    {
        if (cmdArg.startsWith("-d")) // debug
            debug = true;

        else if (cmdArg.startsWith("-h")) // help
            showHelp = true;

        else if (cmdArg.startsWith("-w=")) // width
            width = Integer.parseInt(cmdArg.substring(3));
        else if (cmdArg.startsWith("-width="))
            width = Integer.parseInt(cmdArg.substring(7));

        // height #### UNREACHABLE #####
        else if (cmdArg.startsWith("-h="))
            height = Integer.parseInt(cmdArg.substring(3));
        else if (cmdArg.startsWith("-height="))
            height = Integer.parseInt(cmdArg.substring(8));
    }

    final JFrame appFrame = new AppFrame("ParameterParsing", width, height, debug, showHelp);
    appFrame.setVisible(true);
}

    private PreferencesExampleOrig()
    {
    }

    public static class AppFrame extends JFrame
    {
        AppFrame(final String title, final int width, final int height, final boolean debug, final boolean showHelp)
        {
            super(title);

            setSize(width, height);

            final String info = "AppParameters [debug=" + debug + ", height=" + height + ", showHelp=" + showHelp + ", width=" + width + "]";

            add(new JLabel(info));

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}