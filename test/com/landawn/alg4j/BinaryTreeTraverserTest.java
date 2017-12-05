package com.landawn.alg4j;

import org.junit.Test;

import com.landawn.alg4j.util.BinaryTreeNode;
import com.landawn.alg4j.util.BinaryTreeTraverser;

public class BinaryTreeTraverserTest {

    @Test
    public void test_depthTraversal() {
        BinaryTreeNode<String> a = new BinaryTreeNode<>("a", null, null);
        BinaryTreeNode<String> b = new BinaryTreeNode<>("b", null, null);
        BinaryTreeNode<String> c = new BinaryTreeNode<>("c", a, b);
        BinaryTreeNode<String> f = new BinaryTreeNode<>("f", null, null);
        BinaryTreeNode<String> e = new BinaryTreeNode<>("e", null, f);
        BinaryTreeNode<String> d = new BinaryTreeNode<>("d", c, e);

        BinaryTreeTraverser<BinaryTreeNode<String>> traverser = BinaryTreeTraverser.<BinaryTreeNode<String>> using(p -> p.left(), p -> p.right());

        traverser.inOrderTraversal(d).map(p -> p.value()).println();
        traverser.preOrderTraversal(d).map(p -> p.value()).println();
        traverser.postOrderTraversal(d).map(p -> p.value()).println();
    }

}
