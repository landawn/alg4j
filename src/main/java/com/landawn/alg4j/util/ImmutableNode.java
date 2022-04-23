package com.landawn.alg4j.util;

import com.landawn.abacus.util.N;

public class ImmutableNode<T> {
    private final T value;

    public ImmutableNode(T value) {
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
        if (this == obj) {
            return true;
        }

        if (obj instanceof ImmutableNode) {
            return N.equals(((ImmutableNode<T>) obj).value, value);
        }

        return false;
    }

    @Override
    public String toString() {
        return N.toString(value);
    }
}
