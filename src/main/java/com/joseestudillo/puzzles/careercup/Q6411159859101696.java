package com.joseestudillo.puzzles.careercup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Q6411159859101696 {

    /*
     * Suppose there were n numbers in an array S1, S2, S3, S4.......SN rearrange
     * them in a such a way that they satisfy bellow property. S1<S2>S3<S4>......
     */

    public static void main(String[] args) {
        int[] input = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<Integer> sol = bruteForceSolution(input);
        if(sol != null) {
            printSolution(sol);
        }

        sol = smartSolution(input);
        if(sol != null) {
            printSolution(sol);
        }

        System.out.println(isBigger.compare(1, 2));
        System.out.println(isSmaller.compare(1, 2));
        System.out.println(isBigger.compare(3, 2));
        System.out.println(isSmaller.compare(3, 2));
    }

    public static void printSolution(List<Integer> input) {
        System.out.println();
        if(input.size() > 0) {
            for(int i = 0; i < input.size() - 1; i++) {
                System.out.print(input.get(i));
                System.out.print((i % 2 == 1) ? "<" : ">");
            }
            System.out.print(input.get(input.size() - 1));
        }
        System.out.println();
    }

    public static List<Integer> smartSolution(int[] input) {
        // the list can not contain repeated numbers
        List<Integer> listInput = new ArrayList<Integer>();
        for(int i = 0; i < input.length; i++) {
            listInput.add(input[i]);
        }

        Set<Integer> tmpSet = new TreeSet<Integer>();
        tmpSet.addAll(listInput);
        if(tmpSet.size() != listInput.size()) {
            return null;
        }

        Collections.sort(listInput);
        Integer element;
        for(int i = 1; i < listInput.size(); i = i + 2) {
            element = listInput.get(i);
            listInput.set(i, listInput.get(i - 1));
            listInput.set(i - 1, element);
        }
        return listInput;
    }

    public static List<Integer> bruteForceSolution(int[] input) {
        List<Integer> listInput = new ArrayList<Integer>();
        for(int i = 0; i < input.length; i++) {
            listInput.add(input[i]);
        }

        List<Integer> start;
        List<Integer> possibleSolution;
        Integer element;
        for(int i = 0; i < listInput.size(); i++) {
            element = listInput.remove(i);
            start = new ArrayList<Integer>();
            start.add(element);
            possibleSolution = bruteForceSolution(start, listInput);
            if(possibleSolution != null) {
                return possibleSolution;
            }
            listInput.add(i, element); // we restore the list
        }
        return null;
    }

    private static Comparator<Integer> isBigger = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };

    private static Comparator<Integer> isSmaller = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return -1 * (o1.compareTo(o2));
        }
    };

    private static List<Integer> bruteForceSolution(List<Integer> currentSolution, List<Integer> input) {
        if(input.size() == 0) {
            return currentSolution;
        }

        Integer element = currentSolution.get(currentSolution.size() - 1);
        Integer candidate;
        List<Integer> solution;
        Comparator<Integer> comparator = (currentSolution.size() % 2 == 1) ? isBigger : isSmaller;
        for(int i = 0; i < input.size(); i++) {
            candidate = input.get(i);
            if(comparator.compare(element, candidate) > 0) {
                currentSolution.add(input.remove(i));
                solution = bruteForceSolution(currentSolution, input);
                if(solution != null) {
                    return solution;
                } else {
                    input.add(i, currentSolution.remove(currentSolution.size() - 1)); // restore values
                }
            }
        }
        return null;
    }
}
