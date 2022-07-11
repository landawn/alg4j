package com.landawn.alg4j.util;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.function.Function;

import com.landawn.abacus.util.N;
import com.landawn.abacus.util.ObjIterator;
import com.landawn.abacus.util.stream.Stream;

public class BinaryTreeTraverser<T> {
    //    private static final Object NONE = new Object();
    //    @SuppressWarnings("unchecked")
    //    private final T NULL = (T) NONE;

    private final Function<T, T> leftChild;
    private final Function<T, T> rightChild;

    BinaryTreeTraverser(final Function<T, T> leftChild, final Function<T, T> rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public static <T> BinaryTreeTraverser<T> using(final Function<T, T> leftChild, final Function<T, T> rightChild) {
        return new BinaryTreeTraverser<>(leftChild, rightChild);
    }

    public Stream<T> inOrderTraversal(final T startNode) {
        if (startNode == null) {
            return Stream.empty();
        }

        return Stream.of(new InOrderIterator(startNode));
    }

    private final class InOrderIterator extends ObjIterator<T> {
        private final Deque<T> stack;
        private T next = null;

        InOrderIterator(T startNode) {
            N.checkArgNotNull(startNode);

            this.stack = new ArrayDeque<>(8);
            this.next = startNode;
        }

        @Override
        public boolean hasNext() {
            return next != null || stack.size() > 0;
        }

        @Override
        public T next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }

            while (next != null) {
                stack.addLast(next);
                next = leftChild.apply(next);
            }

            final T res = stack.removeLast();
            next = rightChild.apply(res);

            return res;
        }
    }

    public Stream<T> preOrderTraversal(final T startNode) {
        if (startNode == null) {
            return Stream.empty();
        }

        // return Traverser.forTree(newChildGetter()).depthFirstPreOrder(startNode);

        return Stream.of(new PreOrderIterator(startNode));
    }

    private final class PreOrderIterator extends ObjIterator<T> {
        private final Deque<T> stack;

        PreOrderIterator(T startNode) {
            N.checkArgNotNull(startNode);

            this.stack = new ArrayDeque<>(8);
            stack.addLast(startNode);
        }

        @Override
        public boolean hasNext() {
            return stack.size() > 0;
        }

        @Override
        public T next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }

            final T res = stack.removeLast();

            pushIfPresent(stack, rightChild.apply(res));
            pushIfPresent(stack, leftChild.apply(res));

            return res;
        }
    }

    public Stream<T> postOrderTraversal(final T startNode) {
        if (startNode == null) {
            return Stream.empty();
        }

        // return Traverser.forTree(newChildGetter()).depthFirstPreOrder(startNode);

        return Stream.of(new PostOrderIterator(startNode));

    }

    /**
     * Copied from Google Guava under Apache License V2.
     *
     */
    private final class PostOrderIterator extends ObjIterator<T> {
        private final Deque<T> stack;
        private final BitSet hasExpanded;

        PostOrderIterator(T startNode) {
            N.checkArgNotNull(startNode);

            this.stack = new ArrayDeque<>(8);
            stack.addLast(startNode);
            this.hasExpanded = new BitSet();
        }

        @Override
        public boolean hasNext() {
            return stack.size() > 0;
        }

        @Override
        public T next() {
            while (true) {
                final T node = stack.getLast();

                if (hasExpanded.get(stack.size() - 1)) {
                    stack.removeLast();
                    hasExpanded.clear(stack.size());
                    return node;
                } else {
                    hasExpanded.set(stack.size() - 1);
                    pushIfPresent(stack, rightChild.apply(node));
                    pushIfPresent(stack, leftChild.apply(node));
                }
            }
        }
    }

    //    private Function<T, Iterable<? extends T>> newChildGetter() {
    //        return new Function<T, Iterable<? extends T>>() {
    //
    //            @Override
    //            public Iterable<T> apply(T t) {
    //                return new Iterable<T>() {
    //                    @Override
    //                    public Iterator<T> iterator() {
    //                        return new ObjIterator<T>() {
    //                            private T left = null;
    //                            private T right = null;
    //
    //                            @Override
    //                            public boolean hasNext() {
    //                                if (left == null) {
    //                                    left = leftChild.apply(t);
    //
    //                                    if (left == null) {
    //                                        left = NULL;
    //                                    }
    //                                }
    //
    //                                if (left == NULL && right == null) {
    //                                    right = rightChild.apply(t);
    //
    //                                    if (right == null) {
    //                                        right = NULL;
    //                                    }
    //                                }
    //
    //                                return left != NULL || right != NULL;
    //                            }
    //
    //                            @Override
    //                            public T next() {
    //                                if (hasNext() == false) {
    //                                    throw new NoSuchElementException();
    //                                }
    //
    //                                T res = null;
    //
    //                                if (left != NULL) {
    //                                    res = left;
    //                                    left = NULL;
    //                                } else {
    //                                    res = right;
    //                                    right = NULL;
    //                                }
    //
    //                                return res;
    //                            }
    //                        };
    //                    }
    //                };
    //            }
    //        };
    //    }

    private static <T> void pushIfPresent(Deque<T> stack, T node) {
        if (node != null) {
            stack.addLast(node);
        }
    }
}
