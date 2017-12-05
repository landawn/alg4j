package com.landawn.alg4j.util;

import com.google.common.graph.SuccessorsFunction;
import com.landawn.abacus.util.function.Function;
import com.landawn.abacus.util.stream.Stream;

public final class Traverser<T> {
    private final com.google.common.graph.Traverser<T> gTraverser;

    private Traverser(com.google.common.graph.Traverser<T> gTraverser) {
        this.gTraverser = gTraverser;
    }

    public static <T> Traverser<T> forTree(final Function<T, Iterable<? extends T>> tree) {
        return new Traverser<>(com.google.common.graph.Traverser.forGraph(new SuccessorsFunction<T>() {
            @Override
            public Iterable<? extends T> successors(T node) {
                return tree.apply(node);
            }
        }));
    }

    public static <T> Traverser<T> forGraph(final Function<T, Iterable<? extends T>> graph) {
        return new Traverser<>(com.google.common.graph.Traverser.forGraph(new SuccessorsFunction<T>() {
            @Override
            public Iterable<? extends T> successors(T node) {
                return graph.apply(node);
            }
        }));
    }

    public Stream<T> breadthFirst(T startNode) {
        return Stream.of(this.gTraverser.breadthFirst(startNode).iterator());
    }

    public Stream<T> depthFirstPreOrder(T startNode) {
        return Stream.of(this.gTraverser.depthFirstPreOrder(startNode).iterator());
    }

    public Stream<T> depthFirstPostOrder(T startNode) {
        return Stream.of(this.gTraverser.depthFirstPostOrder(startNode).iterator());
    }
}