package com.landawn.alg4j.util;

import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Throwables;

public class BinaryTreeNode<T> extends Node<T> {
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode() {
        this(null);
    }

    public BinaryTreeNode(final T value) {
        this(value, null, null);
    }

    public BinaryTreeNode(final T value, final BinaryTreeNode<T> left, final BinaryTreeNode<T> right) {
        super(value);
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode<T> left() {
        return left;
    }

    public BinaryTreeNode<T> right() {
        return right;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(final BinaryTreeNode<T> newLeft) {
        this.left = newLeft;
    }

    public <E extends Exception> boolean setLeftIf(final BinaryTreeNode<T> newLeft, final Throwables.Predicate<? super BinaryTreeNode<T>, E> predicate)
            throws E {
        if (predicate.test(this)) {
            this.left = newLeft;
            return true;
        }

        return false;
    }

    public BinaryTreeNode<T> getAndSetLeft(final BinaryTreeNode<T> newLeft) {
        final BinaryTreeNode<T> res = left();
        this.left = newLeft;
        return res;
    }

    public BinaryTreeNode<T> setAndGetLeft(final BinaryTreeNode<T> newLeft) {
        this.left = newLeft;
        return left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(final BinaryTreeNode<T> newRight) {
        this.right = newRight;
    }

    public <E extends Exception> boolean setRightIf(final BinaryTreeNode<T> newRight, final Throwables.Predicate<? super BinaryTreeNode<T>, E> predicate)
            throws E {
        if (predicate.test(this)) {
            this.right = newRight;
            return true;
        }

        return false;
    }

    public BinaryTreeNode<T> getAndSetRight(final BinaryTreeNode<T> newRight) {
        final BinaryTreeNode<T> res = right();
        this.right = newRight;
        return res;
    }

    public BinaryTreeNode<T> setAndGetRight(final BinaryTreeNode<T> newRight) {
        this.right = newRight;
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
        if (this == obj) {
            return true;
        }

        if (obj instanceof BinaryTreeNode) {
            final BinaryTreeNode<T> other = (BinaryTreeNode<T>) obj;

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
