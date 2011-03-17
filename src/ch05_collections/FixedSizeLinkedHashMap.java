package ch05_collections;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Realisierung einer gr��enbeschr�nkten HashMap mithilfe der Callback-Methode removeEldestEntry(),
 * die das �lteste Element basierend auf der Einf�gereihenfolge bestimmt.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class FixedSizeLinkedHashMap<K, V> extends LinkedHashMap<K, V>
{
    private final int maxEntryCount;

    public FixedSizeLinkedHashMap(final int maxEntryCount)
    {
        this.maxEntryCount = maxEntryCount;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> customer)
    {
        return size() > maxEntryCount;
    }
}
