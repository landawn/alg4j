package com.landawn.alg4j.util;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

/**
 * 
 *
 * @param <K>
 * @param <V>
 * 
 * @see java.util.TreeSet
 */
public class TreeMap<K, V> extends java.util.TreeMap<K, V> {
    private static final long serialVersionUID = 4263495695123283273L;

    public TreeMap() {
        super();
    }

    public TreeMap(Comparator<? super K> comparator) {
        super(comparator);
    }

    public TreeMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public TreeMap(SortedMap<K, ? extends V> m) {
        super(m);
    }
}
