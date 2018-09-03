package com.joseestudillo.coding.interviews.ctci;

/**
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column are set to 0.
 */
public class IQ_1_8 {

    public static void print(int[][] matrix) {
        for (int y = matrix.length - 1; y >= 0; y--) {
            for (int x = 0; x < matrix.length; x++) {
                System.out.print(String.format("[%s, %s] %s ", x, y, matrix[x][y]));
            }
            System.out.println();
        }
    }


    public static int[][] zeros(int[][] m, int xSize, int ySize) {
        //find zeros
        int[] cols = new int[xSize];
        int[] rows = new int[ySize];
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if(m[i][j] == 0) {
                    cols[i]++;
                    rows[j]++;
                }
            }
        }
        for (int i = 0; i < xSize; i++) {
            if(cols[i] > 0) {
                for (int j = 0; j < ySize; j++) {
                    m[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < ySize; j++) {
            if(rows[j] > 0) {
                for (int i = 0; i < xSize; i++) {
                    m[i][j] = 0;
                }
            }
        }
        return m;
    }

    public static void main(String[] args) {
        int[][] charMatrix1 = {
                {'a'}
        };

        int[][] charMatrix2 = {
                {'a', 'b'},
                {'c', 0}
        };
        int[][] charMatrix3 = {
                {'a', 'b', 'c'},
                {'d', 0, 'f'},
                {'g', 'h', 'i'}
        };
        int[][] charMatrix4 = {
                {'a', 'b', 'c', 'd'},
                {'e',  0, 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };

        print(zeros(charMatrix1, 1,1));
        print(zeros(charMatrix2, 2,2));
        print(zeros(charMatrix3, 3,3));
        print(zeros(charMatrix4, 4,4));
    }




}
