package com.joseestudillo.coding.interviews.ctci;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
 * cannot use additional data structures?
 */
public class IQ_1_1 {

    public boolean isUniqueCharaters0(String s) {
        return s.chars().boxed().collect(Collectors.toSet()).size() == s.length();
    }

    public boolean isUniqueCharaters1(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        for (int i = 0; i < chars.length-1; i++) {
            if(chars[i]==chars[i+1]) {
                return false;
            }
        }
        return true;
    }

}
