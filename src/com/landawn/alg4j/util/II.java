package com.landawn.alg4j.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.landawn.abacus.util.Comparators;
import com.landawn.abacus.util.IntPair;
import com.landawn.abacus.util.N;
import com.landawn.abacus.util.Optional;
import com.landawn.abacus.util.OptionalDouble;
import com.landawn.abacus.util.OptionalInt;
import com.landawn.abacus.util.OptionalLong;
import com.landawn.abacus.util.Pair;
import com.landawn.abacus.util.stream.Stream;

public final class II {
    private II() {
        // utility class.
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
        N.checkArgument(N.notNullOrEmpty(sortedA) || N.notNullOrEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isNullOrEmpty(sortedA)) {
            final int len = sortedB.length;
            return Pair.of(sortedB[(len / 2) - 1], len % 2 == 0 ? OptionalInt.of(sortedB[len / 2]) : OptionalInt.empty());
        } else if (N.isNullOrEmpty(sortedB)) {
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
        N.checkArgument(N.notNullOrEmpty(sortedA) || N.notNullOrEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isNullOrEmpty(sortedA)) {
            final int len = sortedB.length;
            return Pair.of(sortedB[(len / 2) - 1], len % 2 == 0 ? OptionalLong.of(sortedB[len / 2]) : OptionalLong.empty());
        } else if (N.isNullOrEmpty(sortedB)) {
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
        N.checkArgument(N.notNullOrEmpty(sortedA) || N.notNullOrEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isNullOrEmpty(sortedA)) {
            final int len = sortedB.length;
            return Pair.of(sortedB[(len / 2) - 1], len % 2 == 0 ? OptionalDouble.of(sortedB[len / 2]) : OptionalDouble.empty());
        } else if (N.isNullOrEmpty(sortedB)) {
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
        N.requireNonNull(comparator);

        N.checkArgument(N.notNullOrEmpty(sortedA) || N.notNullOrEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isNullOrEmpty(sortedA)) {
            final int len = sortedB.length;
            return Pair.of(sortedB[(len / 2) - 1], len % 2 == 0 ? Optional.of(sortedB[len / 2]) : Optional.<T> empty());
        } else if (N.isNullOrEmpty(sortedB)) {
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
        N.requireNonNull(comparator);

        N.checkArgument(N.notNullOrEmpty(sortedA) || N.notNullOrEmpty(sortedB), "The two sorted arrays can't both be null or empty");
        if (N.isNullOrEmpty(sortedA)) {
            final int len = sortedB.size();
            return Pair.of(sortedB.get((len / 2) - 1), len % 2 == 0 ? Optional.of(sortedB.get(len / 2)) : Optional.<T> empty());
        } else if (N.isNullOrEmpty(sortedB)) {
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
        if (N.isNullOrEmpty(s)) {
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
     * @param s
     * @return an empty {@code Stream} if the specified {@code CharSequence} is {@code null} or empty.
     */
    public static Stream<String> longestSubstringsWithoutRepeatingCharacters(final CharSequence s) {
        if (N.isNullOrEmpty(s)) {
            return Stream.empty();
        }

        final Map<Character, Integer> charPositionMap = new HashMap<>();
        final List<IntPair> startEndPositionsList = new ArrayList<>();
        int maxLength = 0;
        int cnt = 0;

        for (int i = 0, len = s.length(); i < len; i++) {
            char ch = s.charAt(i);
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
            startEndPositionsList.add(IntPair.of(s.length() - cnt, s.length()));
        }

        return Stream.of(startEndPositionsList).map(p -> s.subSequence(p._1, p._2).toString());
    }
}
