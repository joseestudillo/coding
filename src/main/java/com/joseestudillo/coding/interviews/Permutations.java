package com.joseestudillo.coding.interviews;

import java.util.*;

/**
 * Created on 13/09/2017.
 *
 * @author Jose Estudillo
 */
public class Permutations {

	// []
	// [a]
	// [a, b] => [a,b] [b,a]
	// [a, b, c] => [a,b,c] [a,c,b]

	private static List<Integer> copyAndRemove(List<Integer> list, int index) {
		ArrayList<Integer> res = new ArrayList<>();
		res.addAll(list);
		res.remove(index);
		return res;
	}

	public static List<List<Integer>> permutations(List<Integer> toPermutate) {
		List<List<Integer>> results = new ArrayList<>();
		if (toPermutate.isEmpty()) {
			results.add(new ArrayList<>());
		} else {
			for (int i = 0; i < toPermutate.size(); i++) {
				int element = toPermutate.get(i);
				List<Integer> remaining = copyAndRemove(toPermutate, i);
				List<List<Integer>> permutations = permutations(remaining);
				for (List<Integer> permutation : permutations) {
					permutation.add(0, element);
					results.add(permutation);
				}
			}
		}
		return results;
	}


	//[] -> []
	//[1] -> [] [1]
	//[1,2] -> [] [1] [2] [1, 2]


	public static List<Set<Integer>> powerSet(Set<Integer> toPowerSet) {
		List<Set<Integer>> sets = new LinkedList<>();
		sets.add(new HashSet<>());
		powerSet(toPowerSet, sets);
		return sets;
	}

	public static Set<Integer> copyAndAdd(Set<Integer> toCopy, Integer toAdd) {
		Set<Integer> set = new HashSet<>();
		set.addAll(toCopy);
		set.add(toAdd);
		return set;
	}

	public static void powerSet(Set<Integer> toPowerSet, List<Set<Integer>> sets) {
		for (Iterator<Integer> iter = toPowerSet.iterator(); iter.hasNext(); ) {
			Integer element = iter.next();
			List<Set<Integer>> newSets = new ArrayList<>();
			for (Set<Integer> set : sets) {
				newSets.add(copyAndAdd(set, element));
			}
			sets.addAll(newSets);
		}
	}

	public static void main(String[] args) {
		System.out.println(permutations(Arrays.asList(1, 2, 3)));

		Set<Integer> set = new HashSet<>();
		set.addAll(Arrays.asList(1, 2, 3));
		System.out.println(powerSet(set));

		List<Integer> l = Arrays.asList(1, 2, 3, 4, 5);
		int i = 0;
		for(; i < 3; i++) {
			System.out.printf("%s: %s\n", i, l.get(i));
		}
		while( i < l.size()) {
			System.out.printf("%s: %s\n", i, l.get(i));
			i++;
		}

		for(int j = l.size() - 1; j >= 0; j--) {
			System.out.printf("%s: %s\n", j, l.get(j));
		}
	}


}
