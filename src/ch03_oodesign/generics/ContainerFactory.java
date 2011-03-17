package ch03_oodesign.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Hilfsklasse zur generischen Objektkonstruktion 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class ContainerFactory
{
    static <K, V> HashMap<K, V> createTypedHashMap()
    {
        return new HashMap<K, V>();
    }

    static <K, V> TreeMap<K, V> createTypedTreeMap()
    {
        return new TreeMap<K, V>();
    }
    
    static <T> ArrayList<T> createTypedArrayList()
    {
        return new ArrayList<T>();
    }
}