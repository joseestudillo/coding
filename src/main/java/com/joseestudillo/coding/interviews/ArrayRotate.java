package com.joseestudillo.coding.interviews;

import java.util.Arrays;

/**
 * Array rotation
 *
 * @author Jose Estudillo
 */
public class ArrayRotate {

	public static int[] rotateLeft(int[] a, int times) {
		int[] res = new int[a.length];
		System.out.printf("times: %s\n", times);
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%s <- %s\n", i, (i + a.length + times) % a.length);
			res[i] = a[(i + a.length + times) % a.length];
		}
		return res;
	}

	public static int[] rotateRight(int[] a, int times) {
		int[] res = new int[a.length];
		System.out.printf("times: %s\n", times);
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%s <- %s\n", (i + times) % a.length, i);
			res[(i + times) % a.length] = a[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] a = new int[]{1, 2, 3, 4, 5};
		for (int times = 0; times < a.length; times++) {
			System.out.println(Arrays.toString(rotateLeft(a, times)));
			System.out.println(Arrays.toString(rotateRight(a, times)));
		}
	}
}
