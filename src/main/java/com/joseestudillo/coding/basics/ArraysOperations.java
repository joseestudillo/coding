package com.joseestudillo.coding.basics;

import java.util.Arrays;

public class ArraysOperations {

	public static final int N = 10;

	public static void main(String[] args) {
		int[] array = new int[N];
		String[][] array2d = new String[N][N];
		String[][][] array3d = new String[N][N][N];


		System.out.println("Iterate forward");
		for (int i = 0; i < N; i++) {
			array[i] = i;
		}
		System.out.println(Arrays.toString(array));

		System.out.println("Iterate backward");
		for (int i = N - 1; i >= 0; i--) {
			array[i] = i;
		}
		System.out.println(Arrays.toString(array));

		System.out.println("Iterate forward 2d: initialize");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				array2d[i][j] = String.format("(%s,%s)", i, j);
			}
		}

		System.out.println("Iterate forward 2d 1-liner");
		for (int i = 0, j = 0; j < N; i++, j = j + i / N, i = i % N) {
			System.out.print(String.format(" %s", array2d[i][j]));
			if (i == N - 1) {
				System.out.println();
			}
		}

		System.out.println("Iterate forward 3d: initialize with 1-liner");
		for (int i = 0, j = 0, k = 0; k < N; i++, j = j + i / N, k = k + j / N, i = i % N, j = j % N) {
			array3d[i][j][k] = String.format("(%s, %s, %s)", i, j, k);
		}

		System.out.println("Iterate using for: forward");
		for (String[] arrayVal : array2d) {
			System.out.println(Arrays.toString(arrayVal));
		}

		System.out.println("Iterate 2d diagonal");
		for (int i = 0, j = 0; i < N && j < N; i++, j++) {
			array2d[i][j] = String.format("(%s,%s)", i, j);
		}

		int[] a = new int[]{0,1,2,3,4,5,6,7,8,9};
		System.arraycopy(a, 0, a, 5, 3);
		System.out.println(Arrays.toString(a));
	}
}
