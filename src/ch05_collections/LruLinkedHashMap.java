package ch05_collections;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Realisierung einer größenbeschränkten HashMap mithilfe der Callback-Methode removeEldestEntry(),
 * die das älteste Element basierend auf der Zugriffsreihenfolge bestimmt.
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
        // Unschön: Um die Eigenschaft accessOrder anzugeben, müssen wir Werte  
        // an den Konstruktor übergeben, die wir nicht spezifizieren wollen         
        super(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR, ACCESS_ORDER);
        this.maxEntryCount = maxEntryCount;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> customer)
    {
        return size() > maxEntryCount;
    }
}
