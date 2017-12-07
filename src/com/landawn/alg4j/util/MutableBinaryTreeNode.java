package com.landawn.alg4j.util;

import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Try;

public class MutableBinaryTreeNode<T> extends MutableNode<T> {
    private MutableBinaryTreeNode<T> left;
    private MutableBinaryTreeNode<T> right;

    public MutableBinaryTreeNode() {
        this(null);
    }

    public MutableBinaryTreeNode(final T value) {
        this(value, null, null);
    }

    public MutableBinaryTreeNode(final T value, final MutableBinaryTreeNode<T> left, final MutableBinaryTreeNode<T> right) {
        super(value);
        this.left = left;
        this.right = right;
    }

    public MutableBinaryTreeNode<T> left() {
        return left;
    }

    public MutableBinaryTreeNode<T> right() {
        return right;
    }

    public MutableBinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(final MutableBinaryTreeNode<T> newLeft) {
        this.left = newLeft;
    }

    public <E extends Exception> boolean setLeftIf(final MutableBinaryTreeNode<T> newLeft, final Try.Predicate<? super MutableBinaryTreeNode<T>, E> predicate)
            throws E {
        if (predicate.test(this)) {
            this.left = newLeft;
            return true;
        }

        return false;
    }

    public MutableBinaryTreeNode<T> getAndSetLeft(final MutableBinaryTreeNode<T> newLeft) {
        final MutableBinaryTreeNode<T> res = left();
        this.left = newLeft;
        return res;
    }

    public MutableBinaryTreeNode<T> setAndGetLeft(final MutableBinaryTreeNode<T> newLeft) {
        this.left = newLeft;
        return left;
    }

    public MutableBinaryTreeNode<T> getRight() {
        return left;
    }

    public void setRight(final MutableBinaryTreeNode<T> newRight) {
        this.right = newRight;
    }

    public <E extends Exception> boolean setRightIf(final MutableBinaryTreeNode<T> newRight, final Try.Predicate<? super MutableBinaryTreeNode<T>, E> predicate)
            throws E {
        if (predicate.test(this)) {
            this.right = newRight;
            return true;
        }

        return false;
    }

    public MutableBinaryTreeNode<T> getAndSetRight(final MutableBinaryTreeNode<T> newRight) {
        final MutableBinaryTreeNode<T> res = right();
        this.right = newRight;
        return res;
    }

    public MutableBinaryTreeNode<T> setAndGetRight(final MutableBinaryTreeNode<T> newRight) {
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
        if (obj == this) {
            return true;
        }

        if (obj instanceof MutableBinaryTreeNode) {
            final MutableBinaryTreeNode<T> other = (MutableBinaryTreeNode<T>) obj;

            return N.equals(other.value(), this.value()) && N.equals(other.left, this.left) && N.equals(other.right, this.right);
        }

        return false;
    }

    @Override
    public String toString() {
        if (left == null) {
            if (right == null) {
                return N.toString(this.value());
            } else {
                return N.concat(N.toString(this.value()), "[, ", N.toString(right), "]");
            }
        } else {
            if (right == null) {
                return N.concat(N.toString(this.value()), "[", N.toString(left), ", ]");
            } else {
                return N.concat(N.toString(this.value()), "[", N.toString(left), ", ", N.toString(right), "]");
            }
        }
    }
}
