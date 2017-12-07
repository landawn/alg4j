package com.landawn.alg4j;

import org.junit.Test;

import com.landawn.alg4j.util.ImmutableBinaryTreeNode;
import com.landawn.alg4j.util.BinaryTreeTraverser;

public class BinaryTreeTraverserTest {

    @Test
    public void test_depthTraversal() {
        ImmutableBinaryTreeNode<String> a = new ImmutableBinaryTreeNode<>("a", null, null);
        ImmutableBinaryTreeNode<String> b = new ImmutableBinaryTreeNode<>("b", null, null);
        ImmutableBinaryTreeNode<String> c = new ImmutableBinaryTreeNode<>("c", a, b);
        ImmutableBinaryTreeNode<String> f = new ImmutableBinaryTreeNode<>("f", null, null);
        ImmutableBinaryTreeNode<String> e = new ImmutableBinaryTreeNode<>("e", null, f);
        ImmutableBinaryTreeNode<String> d = new ImmutableBinaryTreeNode<>("d", c, e);

        BinaryTreeTraverser<ImmutableBinaryTreeNode<String>> traverser = BinaryTreeTraverser.<ImmutableBinaryTreeNode<String>> using(p -> p.left(), p -> p.right());

        traverser.inOrderTraversal(d).map(p -> p.value()).println();
        traverser.preOrderTraversal(d).map(p -> p.value()).println();
        traverser.postOrderTraversal(d).map(p -> p.value()).println();
    }

}
