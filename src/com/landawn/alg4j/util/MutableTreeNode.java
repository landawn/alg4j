package com.landawn.alg4j.util;

import java.util.List;

import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Try;

public class MutableTreeNode<T> extends MutableNode<T> {
    private List<MutableTreeNode<T>> children;

    public MutableTreeNode() {
        super();
    }

    public MutableTreeNode(final T value) {
        super(value);
    }

    public MutableTreeNode(final T value, final List<MutableTreeNode<T>> children) {
        super(value);
        this.children = children;
    }

    public List<MutableTreeNode<T>> children() {
        return children;
    }

    public List<MutableTreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(final List<MutableTreeNode<T>> newChildren) {
        this.children = newChildren;
    }

    public <E extends Exception> boolean setChildrenIf(final List<MutableTreeNode<T>> newChildren, final Try.Predicate<? super MutableTreeNode<T>, E> predicate)
            throws E {
        if (predicate.test(this)) {
            this.children = newChildren;
            return true;
        }

        return false;
    }

    public List<MutableTreeNode<T>> getAndSetChildren(final List<MutableTreeNode<T>> newChildren) {
        final List<MutableTreeNode<T>> res = children;
        this.children = newChildren;
        return res;
    }

    public List<MutableTreeNode<T>> setAndGetChildren(final List<MutableTreeNode<T>> newChildren) {
        this.children = newChildren;
        return children;
    }

    @Override
    public int hashCode() {
        int res = N.hashCode(value());
        res = res * 31 + N.hashCode(children);
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof MutableTreeNode) {
            final MutableTreeNode<T> other = (MutableTreeNode<T>) obj;

            return N.equals(other.value(), this.value()) && N.equals(other.children, this.children);
        }

        return false;
    }

    @Override
    public String toString() {
        return N.isNullOrEmpty(children) ? N.toString(this.value()) : N.concat(N.toString(this.value()), N.toString(children));
    }
}
