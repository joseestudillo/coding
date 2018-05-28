package com.joseestudillo.coding.interviews;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 27/07/2017.
 *
 * @author Jose Estudillo
 */
public class Fibonacci {


	public static final int FIB0 = 0;
	public static final int FIB1 = 1;

	public static int fibonnaciRec(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Only positive numbers allowed");
		}
		return _fibonnaciRec(n);
	}

	private static int _fibonnaciRec(int n) {
		if (n <= FIB1) {
			return n;
		}
		return _fibonnaciRec(n - 1) + fibonnaciRec(n - 2);
	}

	public static int fibonnaciIter(int n) {
		Map<Integer, Integer> fibonacis = new HashMap<>();
		fibonacis.put(FIB0, FIB0);
		fibonacis.put(FIB1, FIB1);
		for (int i = 2; i <= n; i++) {
			fibonacis.put(i, fibonacis.get(i - 1) + fibonacis.get(i - 2));
		}
		return fibonacis.get(n);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 9; i++) {
			System.out.println(
					String.format("fibonnaci %s: { iter: %s, rec: %s }",
							i,
							fibonnaciIter(i),
							fibonnaciRec(i)));
		}
	}
}
