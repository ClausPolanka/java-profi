package ch05_collections;

import java.util.Collections;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration von möglichen Problemen beim Mix von Generics 
 * und älterem untypisierten Collections 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class CheckedCollectionsExample
{
    public static void main(String[] args)
    {
        final DataModel dataModel = new DataModel();
        final List<ModelElement> elements = dataModel.getAllElements();
        elements.add(new ModelElement("Modelelement"));

        // Hinzufügen auf typisierter Liste -> String wird gespeichert !! 
        OldStyleUtilityClass.oldStyleAddModelElement(elements);
        OldStyleUtilityClass.printListElements("UNCHECKED", elements);

        // Erzeugen einer dynamisch typsicheren Sicht auf die Liste 
        final List<ModelElement> checkedElements = Collections.checkedList(elements, ModelElement.class);

        // Hinzufügen auf typisierter Liste -> Exception !! 
        OldStyleUtilityClass.oldStyleAddModelElement(checkedElements);
        OldStyleUtilityClass.printListElements("TYPE-CHECKED", checkedElements);
    }
}
