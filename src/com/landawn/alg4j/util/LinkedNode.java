package com.landawn.alg4j.util;

import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Try;

public class LinkedNode<T> extends MutableNode<T> {
    private LinkedNode<T> next;

    public LinkedNode(T value) {
        super(value);
    }

    public LinkedNode<T> next() {
        return next;
    }

    public LinkedNode<T> getNext() {
        return next;
    }

    public void setNext(final LinkedNode<T> newNext) {
        this.next = newNext;
    }

    public <E extends Exception> boolean setNextIf(final LinkedNode<T> newNext, final Try.Predicate<? super LinkedNode<T>, E> predicate) throws E {
        if (predicate.test(this)) {
            this.next = newNext;
            return true;
        }

        return false;
    }

    public LinkedNode<T> getAndSetNext(final LinkedNode<T> newNext) {
        final LinkedNode<T> res = next;
        this.next = newNext;
        return res;
    }

    public LinkedNode<T> setAndGetNext(final LinkedNode<T> newNext) {
        this.next = newNext;
        return next;
    }

    @Override
    public int hashCode() {
        int res = N.hashCode(value());
        res = res * 31 + N.hashCode(next);
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof LinkedNode) {
            final LinkedNode<T> other = (LinkedNode<T>) obj;

            return N.equals(other.value(), this.value()) && N.equals(other.next, this.next);
        }

        return false;
    }

    @Override
    public String toString() {
        return next == null ? N.toString(this.value()) : N.concat(N.toString(this.value()), N.toString(next));
    }
}
