package ch04_javagrundlagen;

import java.util.ArrayList;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration von Merkwürdigkeiten beim Auto-Boxing
 * und der Ausführung von Methoden
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class AutoBoxingAndMethodSignatureExample
{
    public static void main(final String[] args)
    {
        final List<Integer> indexList = new ArrayList<Integer>();
        indexList.add(7);
        indexList.add(8);

        // Auto-Boxing => Integer.valueOf(0) => remove(Object) oder remove(int)? 
        final Integer removed = indexList.remove(0);
        // Gemäß Regel 1 => remove(int) 
        System.out.println("removed " + removed);

        final Integer pos = 8;
        // Auto-Unboxing => 8 => remove(int) ? 
        // Subtypbeziehung Integer extends Object => remove(Object) 
        System.out.println(indexList.remove(pos));
    }
    
    private AutoBoxingAndMethodSignatureExample()
    {        
    }
}
