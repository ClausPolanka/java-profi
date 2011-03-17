package ch12_entwurfsmuster;

import java.util.List;

/**
 * Observer-Mechanismus, um Änderungen im Modell darzustellen
 *   
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public interface IModelListener
{
    public void imageElementsChanged(final List<AbstractGraphicsElement> images);
    public void pdfElementsChanged(final List<AbstractGraphicsElement> pdfs);
    public void nameChanged(final String newName);
}