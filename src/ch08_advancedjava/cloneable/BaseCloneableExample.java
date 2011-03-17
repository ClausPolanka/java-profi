package ch08_advancedjava.cloneable;

/**
 * Beispielklasse zur Demonstration des Klonens
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class BaseCloneableExample
{
    public static void main(String[] args)
    {
        // Kopieren durch Klonen
        final BaseCloneable original = new BaseCloneable(47, 11);
        BaseCloneable typeSafeClone = null;
        try
        {
            // Cast erforderlich, damit man sinnvoll mit der Kopie arbeiten kann
            typeSafeClone = (BaseCloneable) original.clone();
        }
        catch (final CloneNotSupportedException e)
        {
            // Kann nicht auftreten, muss aber abgefangen werden 
        }

        // Verschiedene Prüfungen durchführen 
        if (typeSafeClone != null)
        {
            checkContract(original, typeSafeClone);
            modify(original, typeSafeClone);
        }
    }

    private static void checkContract(final BaseCloneable original, final BaseCloneable clone)
    {
        System.out.println("Original: " + original);
        System.out.println("Kopie: " + clone);

        // Kontraktprüfung 
        System.out.println("\nKontraktprüfung:");
        System.out.println("Objekterzeugung? " + (clone != original));
        System.out.println("Typgleichheit?   " + clone.getClass().equals(original.getClass()));
        System.out.println("Wertgleichheit?  " + clone.equals(original));
    }

    private static void modify(final BaseCloneable original, final BaseCloneable clone)
    {
        // Ä„nderung der Kopie 
        System.out.println("\nÄnderung der Kopie:");
        clone.value = 13;
        System.out.println("Original: " + original);
        System.out.println("Kopie: " + clone);
    }

    BaseCloneableExample()
    {
    }
}
