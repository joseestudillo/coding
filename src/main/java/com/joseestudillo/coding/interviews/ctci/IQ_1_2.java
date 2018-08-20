package com.joseestudillo.coding.interviews.ctci;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the
 * other.
 */

public class IQ_1_2 {

    public static boolean isPermutation(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }
        char[] s1CharsSorted = s1.toCharArray();
        char[] s2CharsSorted = s2.toCharArray();
        return new String(s1CharsSorted).equals(new String(s2CharsSorted));
    }

    private static BiFunction<Integer, Integer, Integer> plusOne =
            (currentKey, currentValueForKey) -> currentValueForKey == null ? 1 : currentValueForKey + 1;

    private static BiFunction<Integer, Integer, Integer> minusOne =
            (currentKey, currentValueForKey) -> currentValueForKey == null ? 1 : currentValueForKey - 1;

    public static boolean isPermutation1(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        s1.chars().boxed().forEach(ch -> map.compute(ch, plusOne));
        s2.chars().boxed().forEach(ch -> map.compute(ch, minusOne));
        return map.values().stream().allMatch(x -> x == 0);
    }
}
