package ch05_collections;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Beispielprogramm zur Demonstration der Speicherung von Schl�ssel-Wert-Abbildungen
 * in einer HashMap.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class NameToImageMapExample
{
    public static void main(String[] args) throws IOException
    {
        // Typsichere Definition 
        final Map<String, Image> nameToImageMap = new HashMap<String, Image>();

        // Speicherung einiger Mappings Name -> Bild 
        nameToImageMap.put("Fu�ball", readImageFile("tile_gras_1.jpg"));
        nameToImageMap.put("Wasserball", readImageFile("tile_water.jpg"));
        nameToImageMap.put("Klettern", readImageFile("tile_rock_2.jpg"));

        // Zugriff liefert: BufferedImage }$
        System.out.println("'Fu�ball' " + nameToImageMap.get("Fu�ball"));

        // Spezialfall: Schl�ssel mit Leerzeichen und null als Wert 
        nameToImageMap.put("Skaten ", null);
        // Zugriff liefert null, obwohl Schl�ssel vorhanden 
        System.out.println("Skaten ' " + nameToImageMap.get("Skaten "));
        // containsKey wertet dies korrekt mit true aus 
        System.out.println("Skaten ' " + nameToImageMap.containsKey("Skaten "));

        // F�ge Abbildung von null auf Bild hinzu          
        nameToImageMap.put(null, readImageFile("tile_gras_2.jpg"));

        // Ausgabe aller Schl�ssel und Wert 
        System.out.println("Keys = " + nameToImageMap.keySet());
        System.out.println("Values = " + nameToImageMap.values());

        final JFrame frame = new AppFrame("NameToImageMap-Demo", nameToImageMap);
        frame.setVisible(true);
    }

    public static Image readImageFile(final String imageName) throws IOException
    {
        return ImageIO.read(new File("../config/tiles/" + imageName));
    }

    public static final class AppFrame extends JFrame
    {
        final Map<String, Image> nameToImageMap;

        AppFrame(final String title, final Map<String, Image> nameToImageMap)
        {
            super(title);
            this.nameToImageMap = nameToImageMap;

            final Set<String> images = nameToImageMap.keySet();
            final List<String> imageList = new ArrayList<String>(images);
            imageList.add(0, "-none-");

            setSize(400, 150);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            final JPanel selectPanel = new JPanel(new BorderLayout());

            final JLabel lbl = new JLabel("Bildname eingeben: ");
            final JTextField txt = new JTextField(30);
            final JComboBox combo = new JComboBox(imageList.toArray(new String[0]));
            selectPanel.add(lbl, BorderLayout.WEST);
            selectPanel.add(txt, BorderLayout.CENTER);
            selectPanel.add(combo, BorderLayout.SOUTH);

            add(selectPanel, BorderLayout.NORTH);

            final JLabel imageLabel = new JLabel("-no image-");
            add(imageLabel, BorderLayout.CENTER);

            txt.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    final String imageName = txt.getText();
                    displayImage(nameToImageMap, imageLabel, imageName);
                }
            });

            combo.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    final String imageName = (String) combo.getSelectedItem();
                    displayImage(nameToImageMap, imageLabel, imageName);
                }
            });
        }

        private void displayImage(final Map<String, Image> nameToImageMap, final JLabel imageLabel,
                                  final String imageName)
        {
            final Image image = nameToImageMap.get(imageName);
            if (image == null)
            {
                imageLabel.setIcon(null);
                imageLabel.setText("no such image: '" + imageName + "'");
            }
            else
            {
                imageLabel.setIcon(new ImageIcon(image));
                imageLabel.setText("image: '" + imageName + "'");
            }
        }
    }
}
