package ch12_entwurfsmuster;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Beispielprogramm für das Schablonenmethode-Muster:
 * <br>
 * Die Basisklasse <code>BaseDrawingComponent</code> definiert den grundsätzlichen Algorithmus und
 * erlaubt es Subklassen an verschiedenen Stellen steuernd einzugreifen.
 * Die abgeleitet Klasse <code>TileImageDrawingComponent</code> realisiert dazu die Methode
 * <code>drawContent()</code>, in der die Landschaft aus zufällig gewählten Kacheln gezeichnet
 * wird.
 * Die Klasse <code>AppFrame</code> visualisiert das Ganze.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
@SuppressWarnings("serial")
public final class TileImageDrawingComponent extends BaseDrawingComponent
{
    private final Image[]    tileImages;

    private static final int TILES_WIDTH  = 64;

    private static final int TILES_HEIGHT = 64;

    public TileImageDrawingComponent(final Image[] tileImages)
    {
        this.tileImages = tileImages;
    }

    @Override
    public void drawContent(final Graphics2D g2d)
    {
        for (int x = 0; x < getSize().width; x += TILES_WIDTH)
        {
            for (int y = 0; y < getSize().height; y += TILES_HEIGHT)
            {
                final int tileIndex = (int) (Math.random() * tileImages.length);

                g2d.drawImage(tileImages[tileIndex], x, y, null);
            }
        }
    }

    public static void main(String[] args)
    {
        try
        {
            final Image[] tileImages = loadTileImages();
            final JFrame frame = new AppFrame("TileImageDrawingComponent", tileImages);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
        catch (final IOException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Initialisierungsfehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Image[] loadTileImages() throws IOException
    {
        final String[] tileFileNames = { "config/tiles/tile_gras_1.jpg", "config/tiles/tile_gras_2.jpg",
                        "config/tiles/tile_rock_1.jpg", "config/tiles/tile_rock_2.jpg", "config/tiles/tile_water.jpg" };

        final int numOfBackgrounds = tileFileNames.length;
        final Image[] tileImages = new Image[numOfBackgrounds];

        for (int i = 0; i < numOfBackgrounds; i++)
        {
            final File backgroundFile = new File(tileFileNames[i]);
            tileImages[i] = ImageIO.read(backgroundFile);
        }

        return tileImages;
    }

    public static class AppFrame extends JFrame
    {
        AppFrame(final String title, final Image[] tileImages)
        {
            super(title);

            add(new TileImageDrawingComponent(tileImages));

            setSize(500, 300);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}
