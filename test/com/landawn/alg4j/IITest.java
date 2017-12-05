package com.landawn.alg4j;

import org.junit.Test;

import com.landawn.abacus.util.Fn;
import com.landawn.alg4j.util.II;

public class IITest {

    @Test
    public void test_medianOfTwoSortedArrays() {
        int[] a = { 1, 3, 5 };
        int[] b = { 2, 2, 2 };
        II.medianOfTwoSortedArrays(a, b).accept(Fn.println());
    }

}
