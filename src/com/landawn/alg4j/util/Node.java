package com.landawn.alg4j.util;

import com.landawn.abacus.util.N;

public class Node<T> {
    private final T value;

    public Node(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    @Override
    public int hashCode() {
        return N.hashCode(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Node) {
            return N.equals(((Node<T>) obj).value, value);
        }

        return false;
    }

    @Override
    public String toString() {
        return N.toString(value);
    }
}
