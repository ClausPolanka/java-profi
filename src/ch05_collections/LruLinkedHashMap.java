package ch05_collections;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Realisierung einer gr��enbeschr�nkten HashMap mithilfe der Callback-Methode removeEldestEntry(),
 * die das �lteste Element basierend auf der Zugriffsreihenfolge bestimmt.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class LruLinkedHashMap<K, V>  extends LinkedHashMap<K, V> 
{
    // Kopie der Package-privaten Definitionen aus der Klasse HashMap     
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    // Definition zur besseren Lesbarkeit
    private static final boolean ACCESS_ORDER = true;
    
    private final int maxEntryCount;

    public LruLinkedHashMap(final int maxEntryCount)
    {
        // Unsch�n: Um die Eigenschaft accessOrder anzugeben, m�ssen wir Werte  
        // an den Konstruktor �bergeben, die wir nicht spezifizieren wollen         
        super(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, ACCESS_ORDER);
        this.maxEntryCount = maxEntryCount;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> customer)
    {
        return size() > maxEntryCount;
    }
}
