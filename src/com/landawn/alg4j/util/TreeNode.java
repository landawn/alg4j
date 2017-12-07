package com.landawn.alg4j.util;

import java.util.List;

import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Try;

public class TreeNode<T> extends Node<T> {
    private List<TreeNode<T>> children;

    public TreeNode() {
        super();
    }

    public TreeNode(final T value) {
        super(value);
    }

    public TreeNode(final T value, final List<TreeNode<T>> children) {
        super(value);
        this.children = children;
    }

    public List<TreeNode<T>> children() {
        return children;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(final List<TreeNode<T>> newChildren) {
        this.children = newChildren;
    }

    public <E extends Exception> boolean setChildrenIf(final List<TreeNode<T>> newChildren, final Try.Predicate<? super TreeNode<T>, E> predicate)
            throws E {
        if (predicate.test(this)) {
            this.children = newChildren;
            return true;
        }

        return false;
    }

    public List<TreeNode<T>> getAndSetChildren(final List<TreeNode<T>> newChildren) {
        final List<TreeNode<T>> res = children;
        this.children = newChildren;
        return res;
    }

    public List<TreeNode<T>> setAndGetChildren(final List<TreeNode<T>> newChildren) {
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

        if (obj instanceof TreeNode) {
            final TreeNode<T> other = (TreeNode<T>) obj;

            return N.equals(other.value(), this.value()) && N.equals(other.children, this.children);
        }

        return false;
    }

    //    @Override
    //    public String toString() {
    //        return N.isNullOrEmpty(children) ? N.toString(this.value()) : N.concat(N.toString(this.value()), N.toString(children));
    //    }
}
