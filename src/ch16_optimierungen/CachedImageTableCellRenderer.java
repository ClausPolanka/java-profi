package ch16_optimierungen;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * TableCellRenderer, der die Bilder bei einmalig zu Beginn aus dem Dateisystem lädt
 * und diese dann zwischenspeichert. Folgende Aufrufe werden aus diesem Cache
 * bedient.  
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CachedImageTableCellRenderer extends SimpleImageTableCellRenderer
{
    private final ImageIcon[] tileIcons;

    public CachedImageTableCellRenderer()
    {
        setHorizontalTextPosition(JLabel.RIGHT);
        setHorizontalAlignment(JLabel.LEFT);

        final int numOfBackgrounds = getNumOfTiles();
        tileIcons = new ImageIcon[numOfBackgrounds];

        for (int i = 0; i < numOfBackgrounds; i++)
        {
            tileIcons[i] = loadTileImage(i);
        }
    }

    public ImageIcon getTileImage(final int i)
    {
        final int index = i % getNumOfTiles();
        return tileIcons[index];
    }

    @Override
    public String toString()
    {
        return "CachedImageTableCellRenderer";
    }
}