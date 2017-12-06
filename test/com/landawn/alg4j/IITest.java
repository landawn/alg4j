package com.landawn.alg4j;

import java.util.Optional;

import org.junit.Test;

import com.landawn.abacus.util.Array;
import com.landawn.abacus.util.Fn;
import com.landawn.abacus.util.N;
import com.landawn.alg4j.util.II;

public class IITest {

    @Test
    public void test_medianOfTwoSortedArrays() {
        int[] a = { 1, 3, 5 };
        int[] b = { 2, 2, 2 };
        II.medianOfTwoSortedArrays(a, b).accept(Fn.println());

        a = Array.of(2);
        b = Array.of(1);
        II.medianOfTwoSortedArrays(a, b).accept(Fn.println());

        Optional.ofNullable("abc").ifPresent(Fn.println());
    }

    @Test
    public void test_longestSubstringsWithoutRepeatingCharacters() {
        II.longestSubstringsWithoutRepeatingCharacters(null).forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestSubstringsWithoutRepeatingCharacters("").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestSubstringsWithoutRepeatingCharacters("abc").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestSubstringsWithoutRepeatingCharacters("abca").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestSubstringsWithoutRepeatingCharacters("abcabc").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestSubstringsWithoutRepeatingCharacters("abcabca").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestSubstringsWithoutRepeatingCharacters("abcabcad").forEach(Fn.println());
    }

}
