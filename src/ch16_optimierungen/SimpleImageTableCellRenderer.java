package ch16_optimierungen;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * TableCellRenderer, der die Bilder bei jedem Aufruf neu aus dem Dateisystem lädt 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public class SimpleImageTableCellRenderer extends DefaultTableCellRenderer
{
    private final String   PATH_TO_IMAGES = "config/tiles/";
    private final String[] tileFileNames  = { "tile_gras_1.jpg", "tile_gras_2.jpg", "tile_rock_1.jpg", "tile_rock_2.jpg", "tile_water.jpg" };

    public SimpleImageTableCellRenderer()
    {
        setHorizontalTextPosition(JLabel.RIGHT);
        setHorizontalAlignment(JLabel.LEFT);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        super.getTableCellRendererComponent(table, null, isSelected, false, row, column);

        if (column >= 2)
        {
            // Ermitteln der Bilder 
            final ImageIcon icon = getTileImage(row + column - 2);
            setIcon(icon);
        }
        return this;
    }

    public ImageIcon getTileImage(final int i)
    {
        // Direkte Weiterleitung an loadTileImage()  
        return loadTileImage(i);
    }

    public ImageIcon loadTileImage(final int i)
    {
        final int imageNo = (i % getNumOfTiles());
        final File imageFile = new File(PATH_TO_IMAGES, tileFileNames[imageNo]);
        try
        {
            // Dateisystemzugriff  
            return new ImageIcon(ImageIO.read(imageFile));
        }
        catch (final IOException e)
        {
            // ... FALLBACK: EMPTY IMAGE ...
            return new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_BGR));
        }
    }

    public final int getNumOfTiles()
    {
        return tileFileNames.length;
    }
    
    @Override
    public String toString()
    {
        return "SimpleImageTableCellRenderer";
    }
}