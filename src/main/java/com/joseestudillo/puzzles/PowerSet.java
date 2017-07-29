package com.joseestudillo.puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerSet {

    public static void main(String[] args) {
        // int[] numbers = { 3, 4, 5 };
        // for(int i = 0; i < numbers.length; i++) {
        // for(char c : Integer.toBinaryString(i).toCharArray()) {
        //
        // }
        // }

        Set<Integer> set = new HashSet<Integer>(Arrays.asList(3, 4, 5));
        System.out.println(powerSetIterative(set));
        System.out.println(powerSetRecursive(set));
        System.out.println(powerSetBinary(set));
    }

    public static List<Set<Integer>> powerSetBinary(Set<Integer> set) {
        List<Integer> setAsList = new ArrayList<Integer>(set);
        List<Set<Integer>> sets = new ArrayList<Set<Integer>>();
        sets.add(new HashSet<Integer>());
        for(int i = 1; i <= Math.pow(2, set.size()) - 1; i++) {
            // reverse to solve the problem with no leading 0's
            char[] binNum = Integer.toBinaryString(i).toCharArray();
            Set<Integer> tmpSet = new HashSet<Integer>();
            int start = binNum.length - 1;
            // we go through in the opposite direction so solve the problem with no leading 0's into BinaryString;
            for(int j = start; j >= 0; j--) {
                if(binNum[j] == '1') {
                    tmpSet.add(setAsList.get(start - j));
                }
            }
            sets.add(tmpSet);
        }
        return sets;
    }

    public static List<Set<Integer>> powerSetRecursive(Set<Integer> set) {
        return powerSetRecursive(new ArrayList<Integer>(set));
    }

    public static List<Set<Integer>> powerSetRecursive(List<Integer> set) {
        if(set.size() == 0) {
            return new ArrayList<Set<Integer>>(Arrays.asList(new HashSet<Integer>()));
        } else {
            Integer elem = set.remove(set.size() - 1);
            List<Set<Integer>> sets = powerSetRecursive(set);
            for(Set<Integer> tmpSet : new ArrayList<Set<Integer>>(sets)) {
                tmpSet = new HashSet<Integer>(tmpSet);
                tmpSet.add(elem);
                sets.add(tmpSet);
            }
            return sets;
        }
    }

    public static List<Set<Integer>> powerSetIterative(Set<Integer> set) {
        List<Integer> elems = new ArrayList<Integer>(set);
        List<Set<Integer>> sets = new ArrayList<Set<Integer>>();
        sets.add(new HashSet<Integer>());
        for(int i = 0; i < elems.size(); i++) {
            Integer elem = elems.get(i);
            List<Integer> subElems = elems.subList(i + 1, elems.size());
            Set<Integer> newSet = new HashSet<Integer>();
            newSet.add(elem);
            sets.add(newSet);
            for(int groupSize = 1; groupSize <= subElems.size(); groupSize++) {
                int lastIndex = subElems.size() - groupSize;
                for(int j = 0; j <= lastIndex; j++) {
                    newSet = new HashSet<Integer>();
                    newSet.add(elem);
                    for(int groupStartIndex = j; groupStartIndex < j + groupSize; groupStartIndex++) {
                        newSet.add(subElems.get(groupStartIndex));
                    }
                    sets.add(newSet);
                }
            }
        }
        return sets;
    }
}
