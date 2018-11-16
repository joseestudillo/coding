package com.joseestudillo.puzzles.interview;

import java.util.Arrays;

public class ArraysClass {

    public static final int N = 10;

    public static void main(String[] args) {
        int[] array = new int[N];
        String[][] array2d = new String[N][N];
        String[][][] array3d = new String[N][N][N];

        for(int i = 0; i < N; i++) {
            array[i] = i;
        }
        System.out.println(Arrays.toString(array));

        for(int i = N - 1; i >= 0; i--) {
            array[i] = i;
        }
        System.out.println(Arrays.toString(array));

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                array2d[i][j] = String.format("(%s,%s)", i, j);
            }
        }

        for(String[] arrayVal : array2d) {
            System.out.println(Arrays.toString(arrayVal));
        }

        for(int i = 0, j = 0; i < N && j < N; i++, j++) {
            array2d[i][j] = String.format("(%s,%s)", i, j);
        }

        for(String[] arrayVal : array2d) {
            System.out.println(Arrays.toString(arrayVal));
        }

        for(int i = 0, j = 0; i < N && j < N; i++, j++) {
            array2d[i][j] = String.format("(%s,%s)", i, j);
        }
    }
}
