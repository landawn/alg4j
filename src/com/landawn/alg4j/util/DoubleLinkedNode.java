package com.landawn.alg4j.util;

import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Try;

public class DoubleLinkedNode<T> extends MutableNode<T> {
    private DoubleLinkedNode<T> previous;
    private DoubleLinkedNode<T> next;

    public DoubleLinkedNode() {
        this(null);
    }

    public DoubleLinkedNode(final T value) {
        this(value, null, null);
    }

    public DoubleLinkedNode(final T value, final DoubleLinkedNode<T> previous, final DoubleLinkedNode<T> next) {
        super(value);
        this.previous = previous;
        this.next = next;
    }

    public DoubleLinkedNode<T> previous() {
        return previous;
    }

    public DoubleLinkedNode<T> next() {
        return next;
    }

    public DoubleLinkedNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(final DoubleLinkedNode<T> newPrevious) {
        this.previous = newPrevious;
    }

    public <E extends Exception> boolean setPreviousIf(final DoubleLinkedNode<T> newPrevious, final Try.Predicate<? super DoubleLinkedNode<T>, E> predicate)
            throws E {
        if (predicate.test(this)) {
            this.previous = newPrevious;
            return true;
        }

        return false;
    }

    public DoubleLinkedNode<T> getAndSetPrevious(final DoubleLinkedNode<T> newPrevious) {
        final DoubleLinkedNode<T> res = previous();
        this.previous = newPrevious;
        return res;
    }

    public DoubleLinkedNode<T> setAndGetPrevious(final DoubleLinkedNode<T> newPrevious) {
        this.previous = newPrevious;
        return previous;
    }

    public DoubleLinkedNode<T> getNext() {
        return previous;
    }

    public void setNext(final DoubleLinkedNode<T> newNext) {
        this.next = newNext;
    }

    public <E extends Exception> boolean setNextIf(final DoubleLinkedNode<T> newNext, final Try.Predicate<? super DoubleLinkedNode<T>, E> predicate) throws E {
        if (predicate.test(this)) {
            this.next = newNext;
            return true;
        }

        return false;
    }

    public DoubleLinkedNode<T> getAndSetNext(final DoubleLinkedNode<T> newNext) {
        final DoubleLinkedNode<T> res = next();
        this.next = newNext;
        return res;
    }

    public DoubleLinkedNode<T> setAndGetNext(final DoubleLinkedNode<T> newNext) {
        this.next = newNext;
        return next;
    }

    @Override
    public int hashCode() {
        int res = N.hashCode(value());
        res = res * 31 + N.hashCode(previous);
        res = res * 31 + N.hashCode(next);
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof DoubleLinkedNode) {
            final DoubleLinkedNode<T> other = (DoubleLinkedNode<T>) obj;

            return N.equals(other.value(), this.value()) && N.equals(other.previous, this.previous) && N.equals(other.next, this.next);
        }

        return false;
    }

    @Override
    public String toString() {
        if (previous == null) {
            if (next == null) {
                return N.toString(this.value());
            } else {
                return N.concat(N.toString(this.value()), "[, ", N.toString(next), "]");
            }
        } else {
            if (next == null) {
                return N.concat(N.toString(this.value()), "[", N.toString(previous), ", ]");
            } else {
                return N.concat(N.toString(this.value()), "[", N.toString(previous), ", ", N.toString(next), "]");
            }
        }
    }
}
