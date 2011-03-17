package ch06_applikationsbausteine;

import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.prefs.Preferences;

import javax.swing.JFrame;

class AppPrefenceVO
{
    Rectangle bounds = new Rectangle();
}

enum PreferencesKeys
{
    POS_X, POS_Y, WIDTH, HEIGHT;
}

/**
 * Simples Beispiel für den Einsatz von Prefences zur Speicherung der letzten Fensterposition
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class PreferencesExample
{
    public static AppPrefenceVO createAppPrefenceVO()
    {
        final Preferences preferences = Preferences.userRoot().node("/ch06_applikationsbausteine/AppFrame");

        final AppPrefenceVO appPrefenceVO = new AppPrefenceVO();

        appPrefenceVO.bounds.x = preferences.getInt(PreferencesKeys.POS_X.name(), 100);
        appPrefenceVO.bounds.y = preferences.getInt(PreferencesKeys.POS_Y.name(), 100);
        appPrefenceVO.bounds.width = preferences.getInt(PreferencesKeys.WIDTH.name(), 500);
        appPrefenceVO.bounds.height = preferences.getInt(PreferencesKeys.HEIGHT.name(), 500);

        return appPrefenceVO;
    }

    public static void storeInPreferences(final AppPrefenceVO appPrefenceVO)
    {
        final Preferences preferences = Preferences.userRoot().node("/ch06_applikationsbausteine/AppFrame");

        preferences.putInt(PreferencesKeys.POS_X.name(), appPrefenceVO.bounds.x);
        preferences.putInt(PreferencesKeys.POS_Y.name(), appPrefenceVO.bounds.y);
        preferences.putInt(PreferencesKeys.WIDTH.name(), appPrefenceVO.bounds.width);
        preferences.putInt(PreferencesKeys.HEIGHT.name(), appPrefenceVO.bounds.height);
    }

    public static final void main(String[] args)
    {
        final AppPrefenceVO appPrefenceVO = createAppPrefenceVO();
        final JFrame appFrame = new AppFrame("Preference Test", appPrefenceVO);
        appFrame.setVisible(true);
    }
    public static class AppFrame extends JFrame
    {
        AppFrame(final String title, final AppPrefenceVO appPrefenceVO)
        {
            super(title);

            setBounds(appPrefenceVO.bounds);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            // Posiitonsänderungen
            addComponentListener(new ComponentListener()
            {
                @Override
                public void componentHidden(ComponentEvent e)
                {
                }

                @Override
                public void componentMoved(ComponentEvent e)
                {
                    storePosition();
                }

                @Override
                public void componentResized(ComponentEvent e)
                {
                    storePosition();
                }

                @Override
                public void componentShown(ComponentEvent e)
                {
                }

                private void storePosition()
                {
                    final AppPrefenceVO appPrefenceVO = new AppPrefenceVO();
                    appPrefenceVO.bounds = getBounds();

                    storeInPreferences(appPrefenceVO);
                }
            });
        }
    }

}