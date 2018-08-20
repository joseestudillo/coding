package com.joseestudillo.coding.interviews.ctci;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palin­
 * drome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation
 * is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
 * EXAMPLE
 * Input:
 * Tact Coa
 * Output:
 * True (permutations: "taco cat", "atco eta", etc.)
 */
public class IQ_1_4 {

    private static BiFunction<Integer, Integer, Integer> plusOne =
            (currentKey, currentValueForKey) -> currentValueForKey == null ? 1 : currentValueForKey + 1;

    public static boolean isPalindrome(String s) {
        Map<Character, Integer> count =
                s.replaceAll("\\s", "")
                        .chars().mapToObj(c -> (char) c)
                        .collect(Collectors.toMap(Function.identity(), c -> 1, Integer::sum));
        return count.values().stream().filter(i -> i % 2 == 1).count() <= 1;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome("a"));
        System.out.println(isPalindrome("aa"));
        System.out.println(isPalindrome("aa"));
        System.out.println(isPalindrome("taco cat"));
        System.out.println(isPalindrome("atco eta"));
    }
}
