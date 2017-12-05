package com.landawn.alg4j.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

/**
 * 
 *
 * @param <E>
 * 
 * @see java.util.TreeSet
 */
public class TreeSet<E> extends java.util.TreeSet<E> {
    private static final long serialVersionUID = 9049233081585714599L;

    public TreeSet() {
        super();
    }

    public TreeSet(Comparator<? super E> comparator) {
        super(comparator);
    }

    public TreeSet(Collection<? extends E> c) {
        super(c);
    }

    public TreeSet(SortedSet<E> s) {
        super(s);
    }
}
