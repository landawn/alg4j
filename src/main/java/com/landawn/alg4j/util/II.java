package com.landawn.alg4j.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.LongPredicate;

import com.landawn.abacus.util.Comparators;
import com.landawn.abacus.util.IntPair;
import com.landawn.abacus.util.MutableDouble;
import com.landawn.abacus.util.MutableInt;
import com.landawn.abacus.util.MutableLong;
import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Numbers;
import com.landawn.abacus.util.Pair;
import com.landawn.abacus.util.Strings;
import com.landawn.abacus.util.u.Nullable;
import com.landawn.abacus.util.u.Optional;
import com.landawn.abacus.util.u.OptionalDouble;
import com.landawn.abacus.util.u.OptionalInt;
import com.landawn.abacus.util.u.OptionalLong;
import com.landawn.abacus.util.function.IntBiFunction;
import com.landawn.abacus.util.function.IntBiPredicate;
import com.landawn.abacus.util.function.QuadFunction;
import com.landawn.abacus.util.stream.IntStream;
import com.landawn.abacus.util.stream.LongStream;
import com.landawn.abacus.util.stream.Stream;

public final class II {

    private static final LongPredicate IS_PRIME = new LongPredicate() {
        @Override
        public boolean test(long value) {
            return isPrime(value);
        }
    };

    private II() {
        // utility class.
    }

    public static boolean isPrime(final long n) {
        return Numbers.isPrime(n);
    }

    /**
     * Returns the primes less than the specified long {@code to}.
     *
     * @param exclusiveTo
     * @return
     */
    public static LongStream primes(final long exclusiveTo) {
        return primes(0, exclusiveTo);
    }

    /**
     * Returns the primes in the specified range: {@code [inclusiveFrom, exclusiveTo)}.
     *
     * @param inclusiveFrom
     * @param exclusiveTo
     * @return
     */
    public static LongStream primes(final long inclusiveFrom, final long exclusiveTo) {
        if (exclusiveTo - inclusiveFrom <= 10_000) {
            return LongStream.of(java.util.stream.LongStream.range(inclusiveFrom, exclusiveTo).filter(IS_PRIME));
        } else {
            return LongStream.of(java.util.stream.LongStream.range(inclusiveFrom, exclusiveTo).parallel().filter(IS_PRIME));
        }
    }

    /**
     * Returns a {@code Pair} with the left value is the element at position {@code (sortedA.length + sortedB.length - 1) / 2} if two specified sorted arrays are merged,
     * and the right value is the element at position {@code (sortedA.length + sortedB.length) / 2} if the sum of two arrays' length is even, or empty is the sum is odd.
     *
     * @param sortedA
     * @param sortedB
     * @return
     */
    public static Pair<Integer, OptionalInt> medianOfTwoSortedArrays(final int[] sortedA, final int[] sortedB) {
        N.checkArgument(N.notEmpty(sortedA) || N.notEmpty(sortedB), "The two sorted arrays can't both be null or empty");

        if (N.isEmpty(sortedA)) {
            final int len = sortedB.length;
            return Pair.of(sortedB[(len / 2) - 1], len % 2 == 0 ? OptionalInt.of(sortedB[len / 2]) : OptionalInt.empty());
        } else if (N.isEmpty(sortedB)) {
            final int len = sortedA.length;
            return Pair.of(sortedA[(len / 2) - 1], len % 2 == 0 ? OptionalInt.of(sortedA[len / 2]) : OptionalInt.empty());
        } else if (sortedA.length > sortedB.length) {
            return medianOfTwoSortedArrays(sortedB, sortedA);
        }

        final int m = sortedA.length, n = sortedB.length;
        int k = (m + n - 1) / 2;
        int l = 0, midA = 0, midB = 0, r = Math.min(k, m);

        while (l < r) {
            midA = l + (r - l) / 2;
            midB = k - midA;
            if (sortedA[midA] < sortedB[midB]) {
                l = midA + 1;
            } else {
                r = midA;
            }
        }

        final int a = l - 1 >= 0 ? (k - l >= 0 ? N.max(sortedA[l - 1], sortedB[k - l]) : sortedA[l - 1]) : sortedB[k - l];

        if (m % 2 != n % 2) {
            return Pair.of(a, OptionalInt.empty());
        }

        final int b = l < m ? (k - l + 1 < n ? N.min(sortedA[l], sortedB[k - l + 1]) : sortedA[l]) : sortedB[k - l + 1];

        return a <= b ? Pair.of(a, OptionalInt.of(b)) : Pair.of(b, OptionalInt.of(a));
    }

    /**
     * Returns a {@code Pair} with the left value is the element at position {@code (sortedA.length + sortedB.length - 1) / 2} if two specified sorted arrays are merged,
     * and the right value is the element at position {@code (sortedA.length + sortedB.length) / 2} if the sum of two arrays' length is even, or empty is the sum is odd.
     *
     * @param sortedA
     * @param sortedB
     * @return
     */
    public static Pair<Long, OptionalLong> medianOfTwoSortedArrays(final long[] sortedA, final long[] sortedB) {
        N.checkArgument(N.notEmpty(sortedA) || N.notEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isEmpty(sortedA)) {
            final int len = sortedB.length;
            return Pair.of(sortedB[(len / 2) - 1], len % 2 == 0 ? OptionalLong.of(sortedB[len / 2]) : OptionalLong.empty());
        } else if (N.isEmpty(sortedB)) {
            final int len = sortedA.length;
            return Pair.of(sortedA[(len / 2) - 1], len % 2 == 0 ? OptionalLong.of(sortedA[len / 2]) : OptionalLong.empty());
        } else if (sortedA.length > sortedB.length) {
            return medianOfTwoSortedArrays(sortedB, sortedA);
        }

        final int m = sortedA.length, n = sortedB.length;
        int k = (m + n - 1) / 2;
        int l = 0, midA = 0, midB = 0, r = Math.min(k, m);

        while (l < r) {
            midA = l + (r - l) / 2;
            midB = k - midA;
            if (sortedA[midA] < sortedB[midB]) {
                l = midA + 1;
            } else {
                r = midA;
            }
        }

        final long a = l - 1 >= 0 ? (k - l >= 0 ? N.max(sortedA[l - 1], sortedB[k - l]) : sortedA[l - 1]) : sortedB[k - l];

        if (m % 2 != n % 2) {
            return Pair.of(a, OptionalLong.empty());
        }

        final long b = l < m ? (k - l + 1 < n ? N.min(sortedA[l], sortedB[k - l + 1]) : sortedA[l]) : sortedB[k - l + 1];

        return a <= b ? Pair.of(a, OptionalLong.of(b)) : Pair.of(b, OptionalLong.of(a));
    }

    /**
     * Returns a {@code Pair} with the left value is the element at position {@code (sortedA.length + sortedB.length - 1) / 2} if two specified sorted arrays are merged,
     * and the right value is the element at position {@code (sortedA.length + sortedB.length) / 2} if the sum of two arrays' length is even, or empty is the sum is odd.
     *
     * @param sortedA
     * @param sortedB
     * @return
     */
    public static Pair<Double, OptionalDouble> medianOfTwoSortedArrays(final double[] sortedA, final double[] sortedB) {
        N.checkArgument(N.notEmpty(sortedA) || N.notEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isEmpty(sortedA)) {
            final int len = sortedB.length;
            return Pair.of(sortedB[(len / 2) - 1], len % 2 == 0 ? OptionalDouble.of(sortedB[len / 2]) : OptionalDouble.empty());
        } else if (N.isEmpty(sortedB)) {
            final int len = sortedA.length;
            return Pair.of(sortedA[(len / 2) - 1], len % 2 == 0 ? OptionalDouble.of(sortedA[len / 2]) : OptionalDouble.empty());
        } else if (sortedA.length > sortedB.length) {
            return medianOfTwoSortedArrays(sortedB, sortedA);
        }

        if (sortedA.length > sortedB.length) {
            return medianOfTwoSortedArrays(sortedB, sortedA);
        }

        final int m = sortedA.length, n = sortedB.length;
        int k = (m + n - 1) / 2;
        int l = 0, midA = 0, midB = 0, r = Math.min(k, m);

        while (l < r) {
            midA = l + (r - l) / 2;
            midB = k - midA;
            if (Double.compare(sortedA[midA], sortedB[midB]) < 0) {
                l = midA + 1;
            } else {
                r = midA;
            }
        }

        final double a = l - 1 >= 0 ? (k - l >= 0 ? N.max(sortedA[l - 1], sortedB[k - l]) : sortedA[l - 1]) : sortedB[k - l];

        if (m % 2 != n % 2) {
            return Pair.of(a, OptionalDouble.empty());
        }

        final double b = l < m ? (k - l + 1 < n ? N.min(sortedA[l], sortedB[k - l + 1]) : sortedA[l]) : sortedB[k - l + 1];

        return Double.compare(a, b) <= 0 ? Pair.of(a, OptionalDouble.of(b)) : Pair.of(b, OptionalDouble.of(a));
    }

    /**
     * Returns a {@code Pair} with the left value is the element at position {@code (sortedA.length + sortedB.length - 1) / 2} if two specified sorted arrays are merged,
     * and the right value is the element at position {@code (sortedA.length + sortedB.length) / 2} if the sum of two arrays' length is even, or empty is the sum is odd.
     *
     * @param sortedA
     * @param sortedB
     * @return
     */
    public static <T extends Comparable<? super T>> Pair<T, Optional<T>> medianOfTwoSortedArrays(final T[] sortedA, final T[] sortedB) {
        return medianOfTwoSortedArrays(sortedA, sortedB, Comparators.naturalOrder());
    }

    /**
     * Returns a {@code Pair} with the left value is the element at position {@code (sortedA.length + sortedB.length - 1) / 2} if two specified sorted arrays are merged,
     * and the right value is the element at position {@code (sortedA.length + sortedB.length) / 2} if the sum of two arrays' length is even, or empty is the sum is odd.
     *
     * @param sortedA has to be already sorted with the specified {@code comparator}.
     * @param sortedB has to be already sorted with the specified {@code comparator}.
     * @param comparator
     * @return
     */
    public static <T> Pair<T, Optional<T>> medianOfTwoSortedArrays(final T[] sortedA, final T[] sortedB, final Comparator<T> comparator) {
        N.checkArgNotNull(comparator);

        N.checkArgument(N.notEmpty(sortedA) || N.notEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isEmpty(sortedA)) {
            final int len = sortedB.length;
            return Pair.of(sortedB[(len / 2) - 1], len % 2 == 0 ? Optional.of(sortedB[len / 2]) : Optional.<T> empty());
        } else if (N.isEmpty(sortedB)) {
            final int len = sortedA.length;
            return Pair.of(sortedA[(len / 2) - 1], len % 2 == 0 ? Optional.of(sortedA[len / 2]) : Optional.<T> empty());
        } else if (sortedA.length > sortedB.length) {
            return medianOfTwoSortedArrays(sortedB, sortedA, comparator);
        }

        final int m = sortedA.length, n = sortedB.length;
        int k = (m + n - 1) / 2;
        int l = 0, midA = 0, midB = 0, r = Math.min(k, m);

        while (l < r) {
            midA = l + (r - l) / 2;
            midB = k - midA;
            if (N.compare(sortedA[midA], sortedB[midB], comparator) < 0) {
                l = midA + 1;
            } else {
                r = midA;
            }
        }

        final T a = l - 1 >= 0 ? (k - l >= 0 ? N.max(sortedA[l - 1], sortedB[k - l], comparator) : sortedA[l - 1]) : sortedB[k - l];

        if (m % 2 != n % 2) {
            return Pair.of(a, Optional.<T> empty());
        }

        final T b = l < m ? (k - l + 1 < n ? N.min(sortedA[l], sortedB[k - l + 1], comparator) : sortedA[l]) : sortedB[k - l + 1];

        return N.compare(a, b, comparator) <= 0 ? Pair.of(a, Optional.of(b)) : Pair.of(b, Optional.of(a));
    }

    /**
     * Returns a {@code Pair} with the left value is the element at position {@code (sortedA.length + sortedB.length - 1) / 2} if two specified sorted lists are merged,
     * and the right value is the element at position {@code (sortedA.length + sortedB.length) / 2} if the sum of two lists' length is even, or empty is the sum is odd.
     *
     *
     * @param sortedA
     * @param sortedB
     * @return
     */
    public static <T extends Comparable<? super T>> Pair<T, Optional<T>> medianOfTwoSortedLists(final List<T> sortedA, final List<T> sortedB) {
        return medianOfTwoSortedLists(sortedA, sortedB, Comparators.naturalOrder());
    }

    /**
     * Returns a {@code Pair} with the left value is the element at position {@code (sortedA.length + sortedB.length - 1) / 2} if two specified sorted lists are merged,
     * and the right value is the element at position {@code (sortedA.length + sortedB.length) / 2} if the sum of two lists' length is even, or empty is the sum is odd.
     *
     * @param sortedA has to be already sorted with the specified {@code comparator}.
     * @param sortedB has to be already sorted with the specified {@code comparator}.
     * @param comparator
     * @return
     */
    public static <T> Pair<T, Optional<T>> medianOfTwoSortedLists(final List<T> sortedA, final List<T> sortedB, final Comparator<T> comparator) {
        N.checkArgNotNull(comparator);

        N.checkArgument(N.notEmpty(sortedA) || N.notEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isEmpty(sortedA)) {
            final int len = sortedB.size();
            return Pair.of(sortedB.get((len / 2) - 1), len % 2 == 0 ? Optional.of(sortedB.get(len / 2)) : Optional.<T> empty());
        } else if (N.isEmpty(sortedB)) {
            final int len = sortedA.size();
            return Pair.of(sortedA.get((len / 2) - 1), len % 2 == 0 ? Optional.of(sortedA.get(len / 2)) : Optional.<T> empty());
        } else if (sortedA.size() > sortedB.size()) {
            return medianOfTwoSortedLists(sortedB, sortedA, comparator);
        }

        final int m = sortedA.size(), n = sortedB.size();
        int k = (m + n - 1) / 2;
        int l = 0, midA = 0, midB = 0, r = Math.min(k, m);

        while (l < r) {
            midA = l + (r - l) / 2;
            midB = k - midA;
            if (N.compare(sortedA.get(midA), sortedB.get(midB), comparator) < 0) {
                l = midA + 1;
            } else {
                r = midA;
            }
        }

        final T a = l - 1 >= 0 ? (k - l >= 0 ? N.max(sortedA.get(l - 1), sortedB.get(k - l), comparator) : sortedA.get(l - 1)) : sortedB.get(k - l);

        if (m % 2 != n % 2) {
            return Pair.of(a, Optional.<T> empty());
        }

        final T b = l < m ? (k - l + 1 < n ? N.min(sortedA.get(l), sortedB.get(k - l + 1), comparator) : sortedA.get(l)) : sortedB.get(k - l + 1);

        return N.compare(a, b, comparator) <= 0 ? Pair.of(a, Optional.of(b)) : Pair.of(b, Optional.of(a));
    }

    /**
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringWithoutRepeatingCharacters(String s) {
        if (Strings.isEmpty(s)) {
            return 0;
        }

        Map<Character, Integer> charPositionMap = new HashMap<>();
        int maxLength = 0;
        int cnt = 0;

        for (int i = 0, len = s.length(); i < len; i++) {
            char ch = s.charAt(i);
            Integer idx = charPositionMap.get(ch);

            if (idx == null || i - idx > cnt) {
                cnt++;
            } else {
                maxLength = Math.max(maxLength, cnt);
                cnt = i - idx;
            }

            charPositionMap.put(ch, i);
        }

        maxLength = Math.max(maxLength, cnt);

        return maxLength;
    }

    /**
     *
     * @param str
     * @return an empty {@code Stream} if the specified {@code CharSequence} is {@code null} or empty.
     */
    public static Stream<IntPair> indicesOfLongestSubstringsWithoutRepeatingCharacters(final CharSequence str) {
        if (Strings.isEmpty(str)) {
            return Stream.empty();
        }

        final Map<Character, Integer> charPositionMap = new HashMap<>();
        final List<IntPair> startEndPositionsList = new ArrayList<>();
        int maxLength = 0;
        int cnt = 0;

        for (int i = 0, len = str.length(); i < len; i++) {
            char ch = str.charAt(i);
            Integer idx = charPositionMap.get(ch);

            if (idx == null || i - idx > cnt) {
                cnt++;
            } else {
                if (cnt > maxLength) {
                    startEndPositionsList.clear();
                }

                if (cnt >= maxLength) {
                    startEndPositionsList.add(IntPair.of(i - cnt, i));
                }

                maxLength = Math.max(maxLength, cnt);

                cnt = i - idx;
            }

            charPositionMap.put(ch, i);
        }

        if (cnt > maxLength) {
            startEndPositionsList.clear();
        }

        if (cnt >= maxLength) {
            startEndPositionsList.add(IntPair.of(str.length() - cnt, str.length()));
        }

        return Stream.of(startEndPositionsList);
    }

    /**
     *
     * @param str
     * @return an empty {@code Stream} if the specified {@code CharSequence} is {@code null} or empty.
     */
    public static Stream<String> longestSubstringsWithoutRepeatingCharacters(final CharSequence str) {
        return indicesOfLongestSubstringsWithoutRepeatingCharacters(str).map(p -> str.subSequence(p._1, p._2).toString());
    }

    /**
     * <code>indicesOfBracketedSubstrings("3[a2[c]]2[a]", '[', ']') => [[2, 7], [10, 11]]</code>
     *
     * @param str
     * @param prefix
     * @param postfix
     * @return
     */
    public static Stream<int[]> indicesOfBracketedSubstrings(final CharSequence str, final char prefix, final char postfix) {
        if (Strings.isEmpty(str)) {
            return Stream.empty();
        }

        return Stream.of(Strings.substringIndicesBetween(str.toString(), 0, str.length(), prefix, postfix));
    }

    //    @SuppressWarnings("deprecation")
    //    private static List<IntPair> findAllIndices(final String str, final int fromIndex, final int toIndex, final char prefix, final char postfix) {
    //        N.checkFromToIndex(fromIndex, toIndex, str == null ? 0 : str.length());
    //
    //        final List<IntPair> res = new ArrayList<>();
    //
    //        if (N.isEmpty(str)) {
    //            return res;
    //        }
    //
    //        int idx = str.indexOf(prefix, fromIndex);
    //
    //        if (idx < 0) {
    //            return res;
    //        }
    //
    //        final char[] chs = N.getCharsForReadOnly(str);
    //        final Deque<Integer> queue = new LinkedList<>();
    //
    //        for (int i = idx; i < toIndex; i++) {
    //            if (chs[i] == prefix) {
    //                queue.push(i + 1);
    //            } else if (chs[i] == postfix && queue.size() > 0) {
    //                final int startIndex = queue.pop();
    //
    //                if (res.size() > 0 && startIndex < res.get(res.size() - 1)._1) {
    //                    while (res.size() > 0 && startIndex < res.get(res.size() - 1)._1) {
    //                        res.remove(res.size() - 1);
    //                    }
    //                }
    //
    //                res.add(IntPair.of(startIndex, i));
    //            }
    //        }
    //
    //        return res;
    //    }

    /**
     * <code>indicesOfBracketedSubstrings("3[a2[c]]2[a]", "[", "]") => [[2, 7], [10, 11]]</code>
     *
     * @param str
     * @param prefix
     * @param postfix
     * @return
     */
    public static Stream<int[]> indicesOfBracketedSubstrings(final CharSequence str, final String prefix, final String postfix) {
        if (Strings.isEmpty(str)) {
            return Stream.empty();
        }

        return Stream.of(Strings.substringIndicesBetween(str.toString(), 0, str.length(), prefix, postfix));
    }

    //    /**
    //     *
    //     * <code>findAllIndices("3[a2[c]]2[a]", '[', ']') = [[2, 7], [10, 11]]</code>
    //     *
    //     * @param str
    //     * @param fromIndex
    //     * @param toIndex
    //     * @param prefix
    //     * @param postfix
    //     * @return
    //     */
    //    private static List<IntPair> findAllIndices(final String str, final int fromIndex, final int toIndex, final String prefix, final String postfix) {
    //        N.checkFromToIndex(fromIndex, toIndex, str == null ? 0 : str.length());
    //
    //        final List<IntPair> res = new ArrayList<>();
    //
    //        if (N.isEmpty(str)) {
    //            return res;
    //        }
    //
    //        int idx = str.indexOf(prefix, fromIndex);
    //
    //        if (idx < 0) {
    //            return res;
    //        }
    //
    //        final Deque<Integer> queue = new LinkedList<>();
    //        queue.add(idx + prefix.length());
    //        int next = -1;
    //
    //        for (int i = idx + prefix.length(), len = toIndex; i < len;) {
    //            if (queue.size() == 0) {
    //                idx = next >= i ? next : str.indexOf(prefix, i);
    //
    //                if (idx < 0) {
    //                    break;
    //                } else {
    //                    queue.add(idx + prefix.length());
    //                    i = idx + prefix.length();
    //                }
    //            }
    //
    //            idx = str.indexOf(postfix, i);
    //
    //            if (idx < 0) {
    //                break;
    //            } else {
    //                final int endIndex = idx;
    //                idx = res.size() > 0 ? Math.max(res.get(res.size() - 1)._2 + postfix.length(), queue.peekLast()) : queue.peekLast();
    //
    //                while ((idx = str.indexOf(prefix, idx)) >= 0 && idx < endIndex) {
    //                    queue.push(idx + prefix.length());
    //                    idx = idx + prefix.length();
    //                }
    //
    //                if (idx > 0) {
    //                    next = idx;
    //                }
    //
    //                final int startIndex = queue.pop();
    //
    //                if (res.size() > 0 && startIndex < res.get(res.size() - 1)._1) {
    //                    while (res.size() > 0 && startIndex < res.get(res.size() - 1)._1) {
    //                        res.remove(res.size() - 1);
    //                    }
    //                }
    //
    //                res.add(IntPair.of(startIndex, endIndex));
    //
    //                i = endIndex + postfix.length();
    //            }
    //        }
    //
    //        return res;
    //    }

    /**
     *
     * <code>bracketedSubstrings("3[a2[c]]2[a]", '[', ']') => ["a2[c]", "a"]</code>
     *
     * @param str
     * @param prefix
     * @param postfix
     * @return
     */
    public static Stream<String> bracketedSubstrings(final CharSequence str, final char prefix, final char postfix) {
        return indicesOfBracketedSubstrings(str, prefix, postfix).map(p -> str.subSequence(p[0], p[1]).toString());
    }

    /**
     *
     * <code>bracketedSubstrings("3[a2[c]]2[a]", "[", "]") => ["a2[c]", "a"]</code>
     *
     * @param str
     * @param prefix
     * @param postfix
     * @return
     */
    public static Stream<String> bracketedSubstrings(final CharSequence str, final String prefix, final String postfix) {
        return indicesOfBracketedSubstrings(str, prefix, postfix).map(p -> str.subSequence(p[0], p[1]).toString());
    }

    /**
     * Two strings are isomorphic if the characters in one can be replaced to get anther.
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean areIsomorphic(final CharSequence a, final CharSequence b) {
        if (a == null || b == null) {
            return a == b;
        } else if (a.equals(b)) {
            return true;
        } else if (a.length() != b.length()) {
            return false;
        }

        final Map<Character, Character> map = new HashMap<>();

        for (int i = 0, len = a.length(); i < len; i++) {
            char ch1 = a.charAt(i);
            char ch2 = b.charAt(i);

            if (map.containsKey(ch1)) {
                if (map.get(ch1) != ch2) {
                    return false;
                }
            } else if (map.containsValue(ch2)) {
                return false;
            } else {
                map.put(ch1, ch2);
            }
        }

        return true;
    }

    public static boolean isPalindrome(final CharSequence str) {
        if (Strings.isEmpty(str)) {
            return true;
        }

        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("null")
    public static boolean isPalindrome(final CharSequence str, final int inclusiveFromIndex, final int exclusiveToIndex) {
        N.checkFromToIndex(inclusiveFromIndex, exclusiveToIndex, str == null ? 0 : str.length());

        if (Strings.isEmpty(str)) {
            return true;
        }

        for (int i = inclusiveFromIndex, j = exclusiveToIndex - 1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static Stream<IntPair> indicesOfLongestPalindromeSubstrings(final CharSequence str) {
        final List<IntPair> startEndPositionsList = new ArrayList<>();
        int maxLength = 0;

        for (int i = 0, len = str.length(); i < len; i++) {
            int tmp = lengthOfLongestPalindromeSubstrings(str, i, 0);

            if (tmp > maxLength) {
                startEndPositionsList.clear();
            }

            if (tmp >= maxLength) {
                startEndPositionsList.add(IntPair.of(i - (tmp - 1) / 2, i + ((tmp - 1) / 2) + 1));
            }

            maxLength = Math.max(maxLength, tmp);

            int tmp2 = lengthOfLongestPalindromeSubstrings(str, i, 1);

            if (tmp2 > maxLength) {
                startEndPositionsList.clear();
            }

            if (tmp2 >= maxLength) {
                startEndPositionsList.add(IntPair.of(i - (tmp2 - 1) / 2, i + (tmp2 / 2) + 1));
            }

            maxLength = Math.max(maxLength, tmp2);
        }

        return Stream.of(startEndPositionsList);
    }

    private static int lengthOfLongestPalindromeSubstrings(CharSequence str, int fromIndex, int shift) {
        int res = shift - 1;

        for (int left = fromIndex, right = fromIndex + shift, len = str.length(); left >= 0 && right < len
                && str.charAt(left) == str.charAt(right); left--, right++) {
            res += 2;
        }

        return res;
    }

    public static Stream<String> longestPalindromeSubstrings(final CharSequence str) {
        return indicesOfLongestPalindromeSubstrings(str).map(p -> str.subSequence(p._1, p._2).toString());
    }

    /**
     *
     * {@code Path is defined as the movements from the most left-top cell to the most right-bottom cell on a grid. And each step only can move to the right or down cell}.
     *
     * @param height
     * @param width
     * @return
     */
    public static OptionalInt uniqueGridPaths(final int height, final int width) {
        return uniqueGridPaths(height, width, IntBiPredicate.ALWAYS_FALSE);
    }

    /**
     *
     * {@code Path is defined as the movements from the most left-top cell to the most right-bottom cell on a grid. And each step only can move to the right or down cell}.
     *
     * @param height
     * @param width
     * @param isObstacle the first parameter is the row index, and the second number is column index. if it returns {@code true}, then no path can pass through the cell.
     * @return
     */
    public static OptionalInt uniqueGridPaths(final int height, final int width, final IntBiPredicate isObstacle) {
        N.checkArgNotNull(isObstacle);

        if (height == 0 || width == 0) {
            return OptionalInt.empty();
        }

        final MutableInt[] dp = new MutableInt[width];
        IntStream.range(0, width).forEach(i -> dp[i] = MutableInt.of(0));
        dp[0] = MutableInt.of(1);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isObstacle.test(i, j)) {
                    dp[j] = null;
                } else if (j > 0 && (dp[j] != null || dp[j - 1] != null)) {
                    if (dp[j] == null) {
                        dp[j] = MutableInt.of(dp[j - 1].value());
                    } else if (dp[j - 1] == null) {
                        // ignore.
                    } else {
                        dp[j].setValue(dp[j].value() + dp[j - 1].value());
                    }
                }
            }
        }

        return dp[width - 1] == null ? OptionalInt.empty() : OptionalInt.of(dp[width - 1].value());
    }

    /**
     *
     * @param height
     * @param width
     * @param toInt the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalInt minGridPathSumInt(final int height, final int width, final IntBiFunction<Integer> toInt) {
        return minGridPathSumInt(height, width, toInt, IntBiPredicate.ALWAYS_FALSE);
    }

    /**
     *
     * @param height
     * @param width
     * @param toInt the first parameter is the row index, and the second number is column index.
     * @param isObstacle the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalInt minGridPathSumInt(final int height, final int width, final IntBiFunction<Integer> toInt, final IntBiPredicate isObstacle) {
        N.checkArgNotNull(toInt);
        N.checkArgNotNull(isObstacle);

        if (height == 0 || width == 0) {
            return OptionalInt.empty();
        }

        final MutableInt[] dp = new MutableInt[width];
        IntStream.range(0, width).forEach(i -> dp[i] = MutableInt.of(Integer.MAX_VALUE));
        dp[0] = MutableInt.of(0);

        for (int i = 0; i < height; i++) {
            if (isObstacle.test(i, 0)) {
                dp[0] = null;
            } else if (dp[0] != null) {
                dp[0].add(toInt.apply(i, 0));
            }

            for (int j = 1; j < width; j++) {
                if (isObstacle.test(i, j)) {
                    dp[j] = null;
                } else if (dp[j - 1] != null || dp[j] != null) {
                    if (dp[j] == null) {
                        dp[j] = MutableInt.of(dp[j - 1].value() + toInt.apply(i, j));
                    } else if (dp[j - 1] == null) {
                        dp[j].add(toInt.apply(i, j));
                    } else {
                        dp[j].setValue(Math.min(dp[j].value(), dp[j - 1].value()) + toInt.apply(i, j));
                    }
                }
            }
        }

        return dp[width - 1] == null ? OptionalInt.empty() : OptionalInt.of(dp[width - 1].value());
    }

    /**
     *
     * @param height
     * @param width
     * @param toLong the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalLong minGridPathSumLong(final int height, final int width, final IntBiFunction<Long> toLong) {
        return minGridPathSumLong(height, width, toLong, IntBiPredicate.ALWAYS_FALSE);
    }

    /**
     *
     * @param height
     * @param width
     * @param toLong the first parameter is the row index, and the second number is column index.
     * @param isObstacle the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalLong minGridPathSumLong(final int height, final int width, final IntBiFunction<Long> toLong, final IntBiPredicate isObstacle) {
        N.checkArgNotNull(toLong);
        N.checkArgNotNull(isObstacle);

        if (height == 0 || width == 0) {
            return OptionalLong.empty();
        }

        final MutableLong[] dp = new MutableLong[width];
        IntStream.range(0, width).forEach(i -> dp[i] = MutableLong.of(Long.MAX_VALUE));
        dp[0] = MutableLong.of(0);

        for (int i = 0; i < height; i++) {
            if (isObstacle.test(i, 0)) {
                dp[0] = null;
            } else if (dp[0] != null) {
                dp[0].add(toLong.apply(i, 0));
            }

            for (int j = 1; j < width; j++) {
                if (isObstacle.test(i, j)) {
                    dp[j] = null;
                } else if (dp[j - 1] != null || dp[j] != null) {
                    if (dp[j] == null) {
                        dp[j] = MutableLong.of(dp[j - 1].value() + toLong.apply(i, j));
                    } else if (dp[j - 1] == null) {
                        dp[j].add(toLong.apply(i, j));
                    } else {
                        dp[j].setValue(Math.min(dp[j].value(), dp[j - 1].value()) + toLong.apply(i, j));
                    }
                }
            }
        }

        return dp[width - 1] == null ? OptionalLong.empty() : OptionalLong.of(dp[width - 1].value());
    }

    /**
     *
     * @param height
     * @param width
     * @param toDouble the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalDouble minGridPathSumDouble(final int height, final int width, final IntBiFunction<Double> toDouble) {
        return minGridPathSumDouble(height, width, toDouble, IntBiPredicate.ALWAYS_FALSE);
    }

    /**
     *
     * @param height
     * @param width
     * @param toDouble the first parameter is the row index, and the second number is column index.
     * @param isObstacle the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalDouble minGridPathSumDouble(final int height, final int width, final IntBiFunction<Double> toDouble,
            final IntBiPredicate isObstacle) {
        N.checkArgNotNull(toDouble);
        N.checkArgNotNull(isObstacle);

        if (height == 0 || width == 0) {
            return OptionalDouble.empty();
        }

        final MutableDouble[] dp = new MutableDouble[width];
        IntStream.range(0, width).forEach(i -> dp[i] = MutableDouble.of(Double.MAX_VALUE));
        dp[0] = MutableDouble.of(0);

        for (int i = 0; i < height; i++) {
            if (isObstacle.test(i, 0)) {
                dp[0] = null;
            } else if (dp[0] != null) {
                dp[0].add(toDouble.apply(i, 0));
            }

            for (int j = 1; j < width; j++) {
                if (isObstacle.test(i, j)) {
                    dp[j] = null;
                } else if (dp[j - 1] != null || dp[j] != null) {
                    if (dp[j] == null) {
                        dp[j] = MutableDouble.of(dp[j - 1].value() + toDouble.apply(i, j));
                    } else if (dp[j - 1] == null) {
                        dp[j].add(toDouble.apply(i, j));
                    } else {
                        dp[j].setValue(Math.min(dp[j].value(), dp[j - 1].value()) + toDouble.apply(i, j));
                    }
                }
            }
        }

        return dp[width - 1] == null ? OptionalDouble.empty() : OptionalDouble.of(dp[width - 1].value());
    }

    /**
     *
     * @param height
     * @param width
     * @param toInt the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalInt maxGridPathSumInt(final int height, final int width, final IntBiFunction<Integer> toInt) {
        return maxGridPathSumInt(height, width, toInt, IntBiPredicate.ALWAYS_FALSE);
    }

    /**
     *
     * @param height
     * @param width
     * @param toInt the first parameter is the row index, and the second number is column index.
     * @param isObstacle the first parameter is the row index, and the second number is column index.
     * @return
     */
    public static OptionalInt maxGridPathSumInt(final int height, final int width, final IntBiFunction<Integer> toInt, final IntBiPredicate isObstacle) {
        N.checkArgNotNull(toInt);
        N.checkArgNotNull(isObstacle);

        if (height == 0 || width == 0) {
            return OptionalInt.empty();
        }

        final MutableInt[] dp = new MutableInt[width];
        IntStream.range(0, width).forEach(i -> dp[i] = MutableInt.of(Integer.MIN_VALUE));
        dp[0] = MutableInt.of(0);

        for (int i = 0; i < height; i++) {
            if (isObstacle.test(i, 0)) {
                dp[0] = null;
            } else if (dp[0] != null) {
                dp[0].add(toInt.apply(i, 0));
            }

            for (int j = 1; j < width; j++) {
                if (isObstacle.test(i, j)) {
                    dp[j] = null;
                } else if (dp[j - 1] != null || dp[j] != null) {
                    if (dp[j] == null) {
                        dp[j] = MutableInt.of(dp[j - 1].value() + toInt.apply(i, j));
                    } else if (dp[j - 1] == null) {
                        dp[j].add(toInt.apply(i, j));
                    } else {
                        dp[j].setValue(Math.max(dp[j].value(), dp[j - 1].value()) + toInt.apply(i, j));
                    }
                }
            }
        }

        return dp[width - 1] == null ? OptionalInt.empty() : OptionalInt.of(dp[width - 1].value());
    }

    /**
     *
     * @param height
     * @param width
     * @param toLong the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalLong maxGridPathSumLong(final int height, final int width, final IntBiFunction<Long> toLong) {
        return maxGridPathSumLong(height, width, toLong, IntBiPredicate.ALWAYS_FALSE);
    }

    /**
     *
     * @param height
     * @param width
     * @param toLong the first parameter is the row index, and the second number is column index.
     * @param isObstacle the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalLong maxGridPathSumLong(final int height, final int width, final IntBiFunction<Long> toLong, final IntBiPredicate isObstacle) {
        N.checkArgNotNull(toLong);
        N.checkArgNotNull(isObstacle);

        if (height == 0 || width == 0) {
            return OptionalLong.empty();
        }

        final MutableLong[] dp = new MutableLong[width];
        IntStream.range(0, width).forEach(i -> dp[i] = MutableLong.of(Long.MIN_VALUE));
        dp[0] = MutableLong.of(0);

        for (int i = 0; i < height; i++) {
            if (isObstacle.test(i, 0)) {
                dp[0] = null;
            } else if (dp[0] != null) {
                dp[0].add(toLong.apply(i, 0));
            }

            for (int j = 1; j < width; j++) {
                if (isObstacle.test(i, j)) {
                    dp[j] = null;
                } else if (dp[j - 1] != null || dp[j] != null) {
                    if (dp[j] == null) {
                        dp[j] = MutableLong.of(dp[j - 1].value() + toLong.apply(i, j));
                    } else if (dp[j - 1] == null) {
                        dp[j].add(toLong.apply(i, j));
                    } else {
                        dp[j].setValue(Math.max(dp[j].value(), dp[j - 1].value()) + toLong.apply(i, j));
                    }
                }
            }
        }

        return dp[width - 1] == null ? OptionalLong.empty() : OptionalLong.of(dp[width - 1].value());
    }

    /**
     *
     * @param height
     * @param width
     * @param toDouble the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalDouble maxGridPathSumDouble(final int height, final int width, final IntBiFunction<Double> toDouble) {
        return maxGridPathSumDouble(height, width, toDouble, IntBiPredicate.ALWAYS_FALSE);
    }

    /**
     *
     * @param height
     * @param width
     * @param toDouble the first parameter is the row index, and the second number is column index.
     * @param isObstacle the first parameter is the row index, and the second number is column index.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static OptionalDouble maxGridPathSumDouble(final int height, final int width, final IntBiFunction<Double> toDouble,
            final IntBiPredicate isObstacle) {
        N.checkArgNotNull(toDouble);
        N.checkArgNotNull(isObstacle);

        if (height == 0 || width == 0) {
            return OptionalDouble.empty();
        }

        final MutableDouble[] dp = new MutableDouble[width];
        IntStream.range(0, width).forEach(i -> dp[i] = MutableDouble.of(Double.MIN_VALUE));
        dp[0] = MutableDouble.of(0);

        for (int i = 0; i < height; i++) {
            if (isObstacle.test(i, 0)) {
                dp[0] = null;
            } else if (dp[0] != null) {
                dp[0].add(toDouble.apply(i, 0));
            }

            for (int j = 1; j < width; j++) {
                if (isObstacle.test(i, j)) {
                    dp[j] = null;
                } else if (dp[j - 1] != null || dp[j] != null) {
                    if (dp[j] == null) {
                        dp[j] = MutableDouble.of(dp[j - 1].value() + toDouble.apply(i, j));
                    } else if (dp[j - 1] == null) {
                        dp[j].add(toDouble.apply(i, j));
                    } else {
                        dp[j].setValue(Math.max(dp[j].value(), dp[j - 1].value()) + toDouble.apply(i, j));
                    }
                }
            }
        }

        return dp[width - 1] == null ? OptionalDouble.empty() : OptionalDouble.of(dp[width - 1].value());
    }

    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation
     *
     * <pre>
     * <code>Set&lt;String&gt; binaryOperators = N.asSet("+", "-", "*", "/");
     * final QuadFunction<Integer, Integer, Integer, String, Integer> operation = (a, b, c, t) -> {
     *     switch (t) {
     *         case "+":
     *             return a + b;
     *         case "-":
     *             return a - b;
     *         case "*":
     *             return a * b;
     *         case "/":
     *             return a / b;
     *         default:
     *             return Integer.valueOf(t);
     *     }
     * };
     *
     * II.evalRPN(N.asList("2", "1", "+", "3", "*"), null, binaryOperators, null, operation).ifPresent(Fn.println()); // -> 9
     * II.evalRPN(N.asList("4", "13", "5", "/", "+"), null, binaryOperators, null, operation).ifPresent(Fn.println()); // -> 6
     * </code>
     * </pre>
     *
     * @param tokens
     * @param unaryOperators it's better defined as {@code constant(static final)}.
     * @param binaryOperators it's better defined as {@code constant(static final)}.
     * @param ternaryOperators it's better defined as {@code constant(static final)}.
     * @param operation it's better defined as {@code constant(static final)}.
     * @return
     * @see #uniqueGridPaths(int, int, IntBiPredicate)
     */
    public static <T, R> Nullable<R> evalRPN(final List<T> tokens, final Set<?> unaryOperators, final Set<?> binaryOperators, final Set<?> ternaryOperators,
            final QuadFunction<? super R, ? super R, ? super R, ? super T, R> operation) {
        N.checkArgNotNull(operation);

        if (N.isEmpty(tokens)) {
            return Nullable.empty();
        }

        final boolean hasUnaryOperators = N.notEmpty(unaryOperators);
        final boolean hasBinaryOperators = N.notEmpty(binaryOperators);
        final boolean hasTernaryOperators = N.notEmpty(ternaryOperators);

        final Deque<R> stack = new LinkedList<>();
        R a = null, b = null, c = null;

        for (T token : tokens) {
            if (hasBinaryOperators && binaryOperators.contains(token)) {
                b = stack.pop();
                a = stack.pop();

                stack.push(operation.apply(a, b, null, token));
            } else if (hasUnaryOperators && unaryOperators.contains(token)) {
                a = stack.pop();

                stack.push(operation.apply(a, null, null, token));
            } else if (hasTernaryOperators && ternaryOperators.contains(token)) {
                c = stack.pop();
                b = stack.pop();
                a = stack.pop();

                stack.push(operation.apply(a, b, c, token));
            } else {
                stack.push(operation.apply(null, null, null, token));
            }
        }

        return Nullable.of(stack.pop());
    }
}
