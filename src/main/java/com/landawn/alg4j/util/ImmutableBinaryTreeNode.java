package com.landawn.alg4j.util;

import com.landawn.abacus.util.N;

public class ImmutableBinaryTreeNode<T> extends ImmutableNode<T> {
    private final ImmutableBinaryTreeNode<T> left;
    private final ImmutableBinaryTreeNode<T> right;

    public ImmutableBinaryTreeNode(final T value, final ImmutableBinaryTreeNode<T> left, final ImmutableBinaryTreeNode<T> right) {
        super(value);
        this.left = left;
        this.right = right;
    }

    public ImmutableBinaryTreeNode<T> left() {
        return left;
    }

    public ImmutableBinaryTreeNode<T> right() {
        return right;
    }

    @Override
    public int hashCode() {
        int res = N.hashCode(value());
        res = res * 31 + N.hashCode(left);
        res = res * 31 + N.hashCode(right);
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof ImmutableBinaryTreeNode) {
            final ImmutableBinaryTreeNode<T> other = (ImmutableBinaryTreeNode<T>) obj;

            return N.equals(other.value(), this.value()) && N.equals(other.left, this.left) && N.equals(other.right, this.right);
        }

        return false;
    }

    //    @Override
    //    public String toString() {
    //        if (left == null) {
    //            if (right == null) {
    //                return N.toString(this.value());
    //            } else {
    //                return N.concat(N.toString(this.value()), "[, ", N.toString(right), "]");
    //            }
    //        } else {
    //            if (right == null) {
    //                return N.concat(N.toString(this.value()), "[", N.toString(left), ", ]");
    //            } else {
    //                return N.concat(N.toString(this.value()), "[", N.toString(left), ", ", N.toString(right), "]");
    //            }
    //        }
    //    }
}
