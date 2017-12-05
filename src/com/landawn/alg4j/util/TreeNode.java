package com.landawn.alg4j.util;

import java.util.List;

import com.landawn.abacus.util.ImmutableList;
import com.landawn.abacus.util.N;

public class TreeNode<T> extends Node<T> {
    private final List<TreeNode<T>> children;

    public TreeNode(final T value, final List<TreeNode<T>> children) {
        super(value);
        this.children = ImmutableList.of(children);
    }

    public List<TreeNode<T>> children() {
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

    @Override
    public String toString() {
        return N.isNullOrEmpty(children) ? N.toString(this.value()) : N.concat(N.toString(this.value()), N.toString(children));
    }
}
