package com.landawn.alg4j.util;

import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Try;

public class Node<T> {
    private T value;

    public Node() {
        this(null);
    }

    public Node(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(final T newValue) {
        this.value = newValue;
    }

    public <E extends Exception> boolean setValueIf(final T newValue, final Try.Predicate<? super Node<T>, E> predicate) throws E {
        if (predicate.test(this)) {
            this.value = newValue;
            return true;
        }

        return false;
    }

    public T getAndSetValue(final T newValue) {
        final T res = value;
        this.value = newValue;
        return res;
    }

    public T setAndGetValue(final T newValue) {
        this.value = newValue;
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
