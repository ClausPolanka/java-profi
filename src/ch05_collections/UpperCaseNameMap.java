package ch05_collections;

import java.util.HashMap;

/**
 * Beispielklasse: 
 * Diese Map bietet Zugriff ohne Beachtung der Klein- oder Großschreibung von 
 * Schlüsseln. Zusätzlich werden noch Blanks vorne und hinten abgeschnitten, 
 * da diese bei Eingaben aus dem GUI eine stïändige Fehlerquelle sind. 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class UpperCaseNameMap<V> extends HashMap<String, V>
{
    @Override
    public V put(final String key, final V value)
    {
        return super.put(normalizeKey(key), value);
    }

    @Override
    public V get(final Object key)
    {
        return super.get(normalizeKey((String) key));
    }

    @Override
    public boolean containsKey(final Object key)
    {
        return super.containsKey(normalizeKey((String) key));
    }

    // ...

    private String normalizeKey(final String key)
    {
        if (key == null)
            return null;

        return key.toUpperCase().trim();
    }
}