package com.landawn.alg4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import com.landawn.abacus.util.Array;
import com.landawn.abacus.util.Fn;
import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Profiler;
import com.landawn.abacus.util.Strings;
import com.landawn.abacus.util.function.QuadFunction;
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

        N.println(Strings.findAllIndicesBetween("adb[12[3]]", "[", "]"));
        N.println(Strings.findAllSubstringsBetween("adb[12[3]", "[", "]"));
    }

    @Test
    public void test_bracketedSubstrings() {
        II.bracketedSubstrings("3[a2[c]]2[a]", '[', ']').forEach(Fn.println());
    }

    @Test
    public void test_isPalindrome() {
        assertTrue(II.isPalindrome(null));
        assertTrue(II.isPalindrome(""));
        assertTrue(II.isPalindrome("a"));
        assertTrue(II.isPalindrome("aa"));
        assertTrue(II.isPalindrome("aaa"));
        assertTrue(II.isPalindrome("aba"));
        assertTrue(II.isPalindrome("abba"));
        assertFalse(II.isPalindrome("abb"));
        assertFalse(II.isPalindrome("ab"));
        assertFalse(II.isPalindrome("abc"));
    }

    @Test
    public void test_longestPalindromeSubstrings() {
        II.longestPalindromeSubstrings("ab").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestPalindromeSubstrings("aa").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestPalindromeSubstrings("aabb").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestPalindromeSubstrings("abba").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestPalindromeSubstrings("bbab").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestPalindromeSubstrings("aaab").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestPalindromeSubstrings("abb").forEach(Fn.println());
        N.println(N.repeat('=', 80));
        II.longestPalindromeSubstrings("aabcc").forEach(Fn.println());
    }

    @Test
    public void test_primes() {
        II.primes(100, 100).forEach(N::println);
        N.println(N.repeat('=', 80));
        II.primes(100, 200).forEach(N::println);
        N.println(N.repeat('=', 80));
        assertEquals(78498, II.primes(1000_000).count());

        //        Profiler.run(1, 1, 1, "1000_000", () -> assertTrue(II.primes(1000_000).count() >= 78498)).printResult();
        //
        //        Profiler.run(1, 1, 1, "10_000_000", () -> assertTrue(II.primes(10_000_000).count() >= 78498)).printResult();
        //
        //        Profiler.run(1, 10, 1, "9_900", () -> assertTrue(II.primes(9_900).count() >= 100)).printResult();
        //        Profiler.run(1, 10, 1, "10_100", () -> assertTrue(II.primes(10_100).count() >= 100)).printResult();
        Profiler.run(1, 1, 1, "10_100", () -> assertTrue(II.primes(10_0000_000_100l).count() >= 100)).printResult();
    }

    @Test
    public void test_uniquePathsOnGrid() {
        II.uniqueGridPaths(1, 1).ifPresent(N::println);
        II.uniqueGridPaths(1, 2).ifPresent(N::println);
        II.uniqueGridPaths(2, 1).ifPresent(N::println);
        II.uniqueGridPaths(2, 2).ifPresent(N::println);
        II.uniqueGridPaths(3, 2).ifPresent(N::println);
        II.uniqueGridPaths(3, 3).ifPresent(N::println);
    }

    @Test
    public void test_minPathSumIntOnGrid() {
        II.minGridPathSumInt(1, 1, (i, j) -> 1).ifPresent(N::println);
        II.minGridPathSumInt(1, 2, (i, j) -> 1).ifPresent(N::println);
        II.minGridPathSumInt(2, 1, (i, j) -> 1).ifPresent(N::println);
        II.minGridPathSumInt(2, 2, (i, j) -> 1).ifPresent(N::println);
        II.minGridPathSumInt(3, 2, (i, j) -> 1).ifPresent(N::println);
        II.minGridPathSumInt(3, 3, (i, j) -> 1).ifPresent(N::println);

        final int[][] a = { { 1, 1, 2 }, { 1, 2, 1 }, { 5, 1, 1 } };
        II.minGridPathSumInt(3, 3, (i, j) -> a[i][j]).ifPresent(N::println);
        II.minGridPathSumInt(3, 3, (i, j) -> a[i][j], (i, j) -> a[i][j] == 2).ifPresent(N::println);
        II.minGridPathSumInt(3, 3, (i, j) -> a[i][j], (i, j) -> a[i][j] == 2 || a[i][j] == 5).ifPresentOrElse(N::println, () -> N.println("NONE"));
    }

    @Test
    public void test_maxPathSumIntOnGrid() {
        II.maxGridPathSumInt(1, 1, (i, j) -> 1).ifPresent(N::println);
        II.maxGridPathSumInt(1, 2, (i, j) -> 1).ifPresent(N::println);
        II.maxGridPathSumInt(2, 1, (i, j) -> 1).ifPresent(N::println);
        II.maxGridPathSumInt(2, 2, (i, j) -> 1).ifPresent(N::println);
        II.maxGridPathSumInt(3, 2, (i, j) -> 1).ifPresent(N::println);
        II.maxGridPathSumInt(3, 3, (i, j) -> 1).ifPresent(N::println);

        final int[][] a = { { 1, 1, 2 }, { 1, 2, 1 }, { 5, 1, 1 } };
        II.maxGridPathSumInt(3, 3, (i, j) -> a[i][j]).ifPresent(N::println);
        II.minGridPathSumInt(3, 3, (i, j) -> a[i][j], (i, j) -> a[i][j] == 5).ifPresent(N::println);
        II.minGridPathSumInt(3, 3, (i, j) -> a[i][j], (i, j) -> a[i][j] == 5 || a[i][j] == 2).ifPresentOrElse(N::println, () -> N.println("NONE"));
    }

    @Test
    public void test_evalRPN() {

        final Set<String> binaryOperators = N.asSet("+", "-", "*", "/");
        final QuadFunction<Integer, Integer, Integer, String, Integer> operation = (a, b, c, t) -> {
            switch (t) {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                case "/":
                    return a / b;
                default:
                    return Integer.valueOf(t);
            }
        };

        II.evalRPN(N.asList("2", "1", "+", "3", "*"), null, binaryOperators, null, operation).ifPresent(Fn.println()); // -> 9
        II.evalRPN(N.asList("4", "13", "5", "/", "+"), null, binaryOperators, null, operation).ifPresent(Fn.println()); // -> 6
    }

}
