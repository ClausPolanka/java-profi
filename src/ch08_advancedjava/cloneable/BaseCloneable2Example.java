package ch08_advancedjava.cloneable;

public final class BaseCloneable2Example
{
    public static void main(String[] args)
    {
        // Kopieren durch Klonen 
        final BaseCloneable original = new BaseCloneable(47, 11);
        final BaseCloneable clone = original.clone();
        System.out.println("Original: " + original);
        System.out.println("Kopie: " + clone);

        // Verschiedene Prüfungen durchführen 
        checkContract(original, clone);
        modify(original, clone);
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
        // Ä„nderung in der Kopie 
        System.out.println("\nÄ„nderung:");
        clone.value = 13;
        System.out.println("Original: " + original);
        System.out.println("Kopie: " + clone);
    }

    BaseCloneable2Example()
    {
    }

    public static class BaseCloneable implements Cloneable
    {
        private final int  id;

        /* private */long value; // $\mbox{\bfseries Zur Demonstration Zugriff im Package erlauben }$

        public BaseCloneable(final int id, final long value)
        {
            this.id = id;
            this.value = value;
        }

        @Override
        public BaseCloneable clone()
        {
            try
            {
                return (BaseCloneable) super.clone();
            }
            catch (final CloneNotSupportedException ex)
            {
                throw new InternalError(ex.getMessage());
            }
        }

        @Override
        public String toString()
        {
            return "SimpleCloneable [id=" + id + ", value=" + value + "]";
        }
    }
}
