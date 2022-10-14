package com.landawn.alg4j.util;

import java.util.List;

import com.landawn.abacus.util.ImmutableList;
import com.landawn.abacus.util.N;

public class ImmutableTreeNode<T> extends ImmutableNode<T> {
    private final List<ImmutableTreeNode<T>> children;

    @SuppressWarnings("deprecation")
    public ImmutableTreeNode(final T value, final List<ImmutableTreeNode<T>> children) {
        super(value);
        this.children = ImmutableList.wrap(children);
    }

    public List<ImmutableTreeNode<T>> children() {
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
        if (this == obj) {
            return true;
        }

        if (obj instanceof ImmutableTreeNode) {
            final ImmutableTreeNode<T> other = (ImmutableTreeNode<T>) obj;

            return N.equals(other.value(), this.value()) && N.equals(other.children, this.children);
        }

        return false;
    }

    //    @Override
    //    public String toString() {
    //        return N.isNullOrEmpty(children) ? N.toString(this.value()) : N.concat(N.toString(this.value()), N.toString(children));
    //    }
}
