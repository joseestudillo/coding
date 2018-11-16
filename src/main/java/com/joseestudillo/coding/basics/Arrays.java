package com.joseestudillo.coding.basics;

public class Arrays {

    public static final int N = 10;

    private static void printArray(String[] array) {
        System.out.println(java.util.Arrays.toString(array));
    }

    private static void printArray(int[] array) {
        System.out.println(java.util.Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = new int[N];
        String[][] array2d = new String[N][N];
        String[][][] array3d = new String[N][N][N];


        // Iterate forward
        for (int i = 0; i < N; i++) {
            array[i] = i;
        }
        printArray(array);

        // Iterate backward
        for (int i = N - 1; i >= 0; i--) {
            array[i] = i;
        }
        printArray(array);

        // Iterate forward 2d
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                array2d[i][j] = String.format("(%s,%s)", i, j);
            }
        }

        // Iterate forward 2d 1-liner
        for (int i = 0, j = 0; j < N; i++, j = j + i / N, i = i % N) {
            System.out.print(String.format(" %s", array2d[i][j]));
            if (i == N - 1) {
                System.out.println();
            }
        }

        // Iterate forward 3d 1-liner
        for (int i = 0, j = 0, k = 0; k < N; i++, j = j + i / N, k = k + j / N, i = i % N, j = j % N) {
            array3d[i][j][k] = String.format("(%s, %s, %s)", i, j, k);
        }

        // Iterate using foreach
        for (String[] arrayVal : array2d) {
            printArray(arrayVal);
        }

        // Iterate 2d diagonal
        for (int i = 0, j = 0; i < N && j < N; i++, j++) {
            array2d[i][j] = String.format("(%s,%s)", i, j);
        }

        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.arraycopy(a, 0, a, 5, 3);
        printArray(a);
    }
}
